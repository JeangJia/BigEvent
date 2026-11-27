/** 后端统一响应体：code 0 成功，1 失败 */
export interface Result<T> {
    code: number
    msg: string
    data: T
}

/** 分页响应体，对应后端的 PageResult */
export interface PageResult<T> {
    total: number
    pages: number
    current: number
    size: number
    records: T[]
}
