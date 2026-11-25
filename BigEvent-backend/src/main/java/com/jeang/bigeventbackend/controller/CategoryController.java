package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Category;
import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Resulat<String> add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Resulat.success("添加成功");
    }
}
