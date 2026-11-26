package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Article;
import com.jeang.bigeventbackend.pojo.Result;
import com.jeang.bigeventbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<String> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success("添加成功");
    }
}
