package com.jeang.bigeventbackend.exception;

import com.jeang.bigeventbackend.pojo.Resulat;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 参数校验失败（@Valid 校验 @RequestBody 不通过）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Resulat<Void> handleValidationException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return Resulat.error(msg.isEmpty() ? "参数校验失败" : msg);
    }

    @ExceptionHandler(Exception.class)
    public Resulat handleException(Exception e) {
        e.printStackTrace();
        return Resulat.error((e.getMessage()));
    }
}
