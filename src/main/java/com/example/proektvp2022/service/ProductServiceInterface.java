package com.example.proektvp2022.service;



import com.example.proektvp2022.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    List<Product> findAll();

    Product findById(Long id);

    Optional<Product> findByName(String name);

    Product save(String name, Double price, Integer quantity, Long category, Long manufacturer);

    //Optional<Product> save(ProductDto productDto);

    //Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer);

    //Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);

    Product edit(Long id, String name, Double price, Integer quantity, long category, long manufacturer);

    List<Product> filter(Long category, Long manufacturer);

}
