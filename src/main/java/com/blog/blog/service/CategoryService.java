package com.blog.blog.service;


import com.blog.blog.model.Category;
import com.blog.blog.model.Post;
import com.blog.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public List<Category> findAll() {

        List<Category> categories = categoryRepository.findAll();
        for (Category c : categories){
            c.setName(c.getName().substring(0, 1).toUpperCase() + c.getName().substring(1).toLowerCase());
        }
        return categories;
    }

    public boolean deleteByName(String name) {
        Category category = categoryRepository.findByName(name).orElse(null);
        if(category == null){
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }
}
