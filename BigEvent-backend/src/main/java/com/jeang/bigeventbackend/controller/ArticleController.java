package com.jeang.bigeventbackend.controller;

import com.jeang.bigeventbackend.pojo.Resulat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Resulat<String> list() {
        return Resulat.success("AllList...");
    }
}
