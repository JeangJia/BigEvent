package com.jeang.bigeventbackend.service.impl;

import com.jeang.bigeventbackend.mapper.ArticleMapper;
import com.jeang.bigeventbackend.pojo.Article;
import com.jeang.bigeventbackend.service.ArticleService;
import com.jeang.bigeventbackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        articleMapper.add(article);
    }
}
