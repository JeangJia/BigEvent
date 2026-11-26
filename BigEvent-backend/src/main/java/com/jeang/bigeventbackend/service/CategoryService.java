package com.jeang.bigeventbackend.service;

import com.jeang.bigeventbackend.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list();

    Category findById(Integer id);

    boolean update(Category category);

    boolean delete(Integer id);
}
