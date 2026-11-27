package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Article;
import com.jeang.bigeventbackend.pojo.PageResult;
import com.jeang.bigeventbackend.pojo.Result;
import com.jeang.bigeventbackend.service.ArticleService;
import com.jeang.bigeventbackend.validated.StateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {

    // 单页最大条数，防止前端传入过大的 pageSize
    private static final int MAX_PAGE_SIZE = 100;

    @Autowired
    private ArticleService articleService;

    /*
     * 添加文章
     * */
    @PostMapping
    public Result<String> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success("添加成功");
    }

    /*
     * 删除文章
     * */
    @DeleteMapping
    public Result<String> delete(@RequestParam("id") Integer id) {
        if (id == null)
            return Result.error("文章id不能为空");
        if (articleService.delete(id))
            return Result.success("删除成功");
        return Result.error("文章不存在或无权删除");
    }

    /*
     * 修改文章
     * */
    @PutMapping
    public Result<String> update(@RequestBody @Validated Article article) {
        if (article.getId() == null)
            return Result.error("文章id不能为空");
        if (articleService.update(article))
            return Result.success("修改成功");
        return Result.error("文章不存在或无权修改");
    }

    /*
     *查询文章详情
     * */
    @GetMapping
    public Result<Article> detail(@RequestParam("id") Integer id) {
        if (id == null)
            return Result.error("文章id不能为空");
        Article article = articleService.findById(id);
        if (article == null)
            return Result.error("文章不存在或无权查看");
        return Result.success(article);
    }

    /*
     * 分页查询
     * */
    @GetMapping("/list")
    public Result<PageResult<Article>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "id", required = false) Integer categoryId,
                                            @RequestParam(value = "state", required = false) String state) {
        // 页码、每页条数做边界修正，避免 Page 出现 0/负数导致查全表或异常
        pageNum = Math.max(pageNum, 1);
        pageSize = Math.min(Math.max(pageSize, 1), MAX_PAGE_SIZE);
        if (state != null &&
                !state.isBlank() &&
                !StateValidator.PUBLISHED.equals(state) &&
                !StateValidator.DRAFT.equals(state)) {
            return Result.error("文章状态只能是 已发布 或 草稿");
        }
        PageResult<Article> pageResult = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageResult);
    }
}
