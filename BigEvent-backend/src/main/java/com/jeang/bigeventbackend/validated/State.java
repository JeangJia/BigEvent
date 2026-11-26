package com.jeang.bigeventbackend.validated;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文章发布状态校验：只允许「已发布」或「草稿」。
 * <p>
 * 注意：与 {@code @Pattern} 保持一致，本注解认为 {@code null} 是合法值，
 * 是否允许为空交给 {@code @NotBlank}/{@code @NotNull} 判断。
 */
@Documented
@Constraint(validatedBy = StateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

    String message() default "文章状态只能是 已发布 或 草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
