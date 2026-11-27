import axios from 'axios'

import type { Result } from '@/api/types'

/**
 * 登录功能还没接入，先用写死的 token 顶一下。
 * 接入登录后删掉这行，统一从 localStorage 的 'token' 取。
 */
const DEV_TOKEN =
    'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjMsInVzZXJuYW1lIjoiamVhbmcifSwiZXhwIjoxNzk1ODM5Njg5fQ.7BBEkiaksipMpblr90Hml9YmNXrjngQnMDmUqOueEyM'

const request = axios.create({
    // 走 vite 代理，浏览器侧同源，不触发 CORS 预检
    baseURL: '/api',
    timeout: 10000,
})

// 请求拦截器：统一带 token
request.interceptors.request.use((config) => {
    const token = localStorage.getItem('token') ?? DEV_TOKEN
    if (token) {
        config.headers.Authorization = token
    }
    return config
})

// 响应拦截器：统一处理业务错误和登录失效
request.interceptors.response.use(
    (response) => {
        const body = response.data as Result<unknown> | undefined
        // HTTP 200 但业务失败（code 非 0），直接转成异常，调用方只需写 try/catch
        if (typeof body?.code === 'number' && body.code !== 0) {
            return Promise.reject(new Error(body.msg || '请求失败'))
        }
        return response
    },
    (error) => {
        if (error.response?.status === 401) {
            localStorage.removeItem('token')
            console.warn('未登录或登录已过期')
        }
        return Promise.reject(error)
    },
)

export default request
