package com.example.proektvp2022.service;


import com.example.proektvp2022.model.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);

}
