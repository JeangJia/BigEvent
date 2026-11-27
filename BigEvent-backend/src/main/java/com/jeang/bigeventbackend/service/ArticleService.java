package com.jeang.bigeventbackend.service;

import com.jeang.bigeventbackend.pojo.Article;
import com.jeang.bigeventbackend.pojo.PageResult;

public interface ArticleService {
    void add(Article article);

    boolean delete(Integer id);

    boolean update(Article article);

    Article findById(Integer id);

    PageResult<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
