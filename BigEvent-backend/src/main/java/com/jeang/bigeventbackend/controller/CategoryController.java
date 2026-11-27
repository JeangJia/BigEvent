package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Category;
import com.jeang.bigeventbackend.pojo.Result;
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
    public Result<String> add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success("添加成功");
    }

    /*
     * 查询所有分类
     * */
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    /*
     * 查看分类
     * */
    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam("id") Integer id) {
        if (id == null) {
            return Result.error("分类id不能为空");
        }
        Category category = categoryService.findById(id);
        if (category == null) {
            return Result.error("分类不存在或无权查看");
        }
        return Result.success(category);
    }

    /*
     * 更新文章分类
     * */
    @PutMapping
    public Result<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
        if (!categoryService.update(category)) {
            return Result.error("分类不存在或无权修改");
        }
        return Result.success("更新成功");
    }

    /*
     * 删除分类
     * */
    @DeleteMapping
    public Result<String> delete(@RequestParam("id") Integer id) {
        if (id == null) {
            return Result.error("分类id不能为空");
        }
        if (!categoryService.delete(id)) {
            return Result.error("分类不存在或无权删除");
        }
        return Result.success("删除成功");
    }

}
