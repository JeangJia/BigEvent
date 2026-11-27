import request from '@/utils/request'

import type { PageResult, Result } from './types'

/** 文章，对应后端 com.jeang.bigeventbackend.pojo.Article */
export interface Article {
    id: number
    title: string
    content: string
    coverImg: string
    /** 已发布 | 草稿 */
    state: string
    categoryId: number
    createUser: number
    createTime: string
    updateTime: string
}

/** 分页查询参数，注意后端叫 pageNum 不是 page */
export interface ArticleQuery {
    pageNum?: number
    pageSize?: number
    categoryId?: number
    state?: '已发布' | '草稿'
}

/** 分页查询文章列表 */
export async function getArticleList(params: ArticleQuery = {}) {
    const { data } = await request.get<Result<PageResult<Article>>>('/article/list', { params })
    return data.data
}
