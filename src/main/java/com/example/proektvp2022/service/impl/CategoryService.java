package com.example.proektvp2022.service.impl;


import com.example.proektvp2022.model.Category;
import com.example.proektvp2022.repository.JpaCategoryRepo;
import com.example.proektvp2022.service.CategoryServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final JpaCategoryRepo categoryRepository;

    public CategoryService(JpaCategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
        public Category create(String name, String description) {
            if (name==null || name.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Category c = new Category(name,description);
            categoryRepository.save(c);
            return c;
        }

        @Override
        public Category update(String name, String description) {
            if (name==null || name.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Category c= new Category(name,description);
            categoryRepository.save(c);
            return c;
        }

        @Override
        public void delete(String name) {
            if (name==null || name.isEmpty()) {
                throw new IllegalArgumentException();
            }
            categoryRepository.deleteByName(name);
        }

        @Override
        public List<Category> listCategories() {
            return categoryRepository.findAll();
        }

        @Override
        public List<Category> searchCategories(String searchText) {
            return categoryRepository.findAllByNameLike(searchText);
        }
    }

