package com.jeang.bigeventbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果，只暴露前端真正需要的字段。
 * <p>
 * 不直接返回 MyBatis-Plus 的 {@code IPage}，是为了避免把
 * {@code optimizeCountSql}、{@code searchCount}、{@code countId} 等内部字段序列化给前端。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    /** 总记录数 */
    private Long total;

    /** 总页数 */
    private Long pages;

    /** 当前页码 */
    private Long current;

    /** 每页条数 */
    private Long size;

    /** 当前页数据 */
    private List<T> records;
}
