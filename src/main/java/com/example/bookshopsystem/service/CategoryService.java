package com.example.bookshopsystem.service;

import com.example.bookshopsystem.entity.Category;

import java.io.IOException;

public interface CategoryService {
    void renderCategories() throws IOException;

    Category getCategory();
}
