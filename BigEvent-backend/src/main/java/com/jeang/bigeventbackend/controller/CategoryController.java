package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Category;
import com.jeang.bigeventbackend.pojo.Resulat;
import com.jeang.bigeventbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /*
     * 添加分类
     * */
    @PostMapping
    public Resulat<String> add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Resulat.success("添加成功");
    }

    /*
     * 查询所有分类
     * */
    @GetMapping
    public Resulat<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Resulat.success(categories);
    }

    /*
     * 查看分类
     * */
    @GetMapping("/detail")
    public Resulat<Category> detail(Integer id) {
        Category category = categoryService.findById(id);
        return Resulat.success(category);
    }

    /*
     * 更新文章分类
     * */
    @PutMapping
    public Resulat<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Resulat.success("更新成功");
    }

}
