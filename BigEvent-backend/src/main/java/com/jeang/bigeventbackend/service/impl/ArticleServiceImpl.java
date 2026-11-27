package com.jeang.bigeventbackend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeang.bigeventbackend.mapper.ArticleMapper;
import com.jeang.bigeventbackend.pojo.Article;
import com.jeang.bigeventbackend.pojo.PageResult;
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

    @Override
    public boolean delete(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");
        return articleMapper.delete(createUser, id);
    }

    @Override
    public boolean update(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        return articleMapper.update(article);
    }

    @Override
    public Article findById(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");
        return articleMapper.findById(id, createUser);
    }

    @Override
    public PageResult<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");
        IPage<Article> result = articleMapper.list(page, createUser, categoryId, state);
        return new PageResult<>(result.getTotal(), result.getPages(), result.getCurrent(), result.getSize(), result.getRecords());
    }
}
