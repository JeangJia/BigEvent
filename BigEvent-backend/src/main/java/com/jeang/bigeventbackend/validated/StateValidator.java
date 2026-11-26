package com.jeang.bigeventbackend.validated;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

/**
 * {@link State} 的校验逻辑。
 */
public class StateValidator implements ConstraintValidator<State, String> {

    /** 允许的状态值，集中在这里维护 */
    public static final String PUBLISHED = "已发布";
    public static final String DRAFT = "草稿";

    private static final Set<String> ALLOWED = Set.of(PUBLISHED, DRAFT);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // null 视为合法，是否必填由 @NotBlank 负责
        if (value == null) {
            return true;
        }
        return ALLOWED.contains(value);
    }
}
