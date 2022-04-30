package com.example.proektvp2022.repository;


import com.example.proektvp2022.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCategoryRepo extends JpaRepository<Category,Long> {
    void deleteByName(String name);
    List<Category> findAllByNameLike(String name);
}
