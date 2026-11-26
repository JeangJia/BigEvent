package com.jeang.bigeventbackend.pojo;

import com.jeang.bigeventbackend.validated.Rule;
import com.jeang.bigeventbackend.validated.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;//主键ID

    @NotBlank(message = "文章标题不能为空")
    @Pattern(regexp = Rule.ARTICLE_TITLE_REGEX, message = "文章标题长度为1-20位")
    private String title;//文章标题

    @NotBlank(message = "文章内容不能为空")
    private String content;//文章内容

    @NotBlank(message = "文章封面图像不能为空")
    @URL(message = "文章封面图像格式不正确")
    private String coverImg;//封面图像

    @NotBlank(message = "文章状态不能为空")
    @State
    private String state;//发布状态 已发布|草稿

    @NotNull
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
