package com.example.bookshopsystem.service.impl;

import com.example.bookshopsystem.entity.Category;
import com.example.bookshopsystem.repository.CategoryRepository;
import com.example.bookshopsystem.service.CategoryService;
import com.example.bookshopsystem.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void renderCategories() throws IOException {
        String[] categoriesInput = fileUtil.readFile("src/main/resources/files/categories.txt");

        Arrays.stream(categoriesInput).forEach(row -> {
            String[] split = row.split("\\s+");
            Category category = new Category();
            category.setName(split[0]);
            this.categoryRepository.saveAndFlush(category);
        });
    }

    @Override
    public Category getCategory() {
        Random random = new Random();
        int id = random.nextInt((int) this.categoryRepository.count()) + 1;
        return this.categoryRepository.findById((long) id).orElse(new Category());
    }
}
