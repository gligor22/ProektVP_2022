package com.example.proektvp2022.service.impl;


import com.example.proektvp2022.model.Category;
import com.example.proektvp2022.model.Manufacturer;
import com.example.proektvp2022.model.Product;
import com.example.proektvp2022.model.exceptions.*;
import com.example.proektvp2022.repository.JpaCategoryRepo;
import com.example.proektvp2022.repository.JpaManufacturerRepo;
import com.example.proektvp2022.repository.JpaProductRepo;
import com.example.proektvp2022.service.ProductServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    public final JpaProductRepo productRepository;
    public final JpaCategoryRepo categoryRepository;
    public final JpaManufacturerRepo manufacturerRepository;

    public ProductService(JpaProductRepo productRepo, JpaCategoryRepo categoryRepository, JpaManufacturerRepo manufacturerRepository) {
        this.productRepository = productRepo;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return Optional.of(this.productRepository.findByName(name));
    }

    @Override
    public Product save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        if (name==null || name.isEmpty() || price==null ||
                quantity==null)
        {
            throw new InvalidArgumentsException();
        }
        Product p = new Product(name, price, quantity, category, manufacturer);
        return productRepository.save(p);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product edit(Long id, String name, Double price, Integer quantity, long category, long manufacturer) {
        Product p = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        p.setManufacturer(manufacturerRepository.findById(manufacturer).orElseThrow(() -> new ManufacturerNotFoundException(manufacturer)));
        p.setCategory(categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category)));
        if (name==null || name.isEmpty() || price==null ||
                quantity==null)
        {
            throw new InvalidArgumentsException();
        }
        p.setName(name);
        p.setQuantity(quantity);
        p.setPrice(price);
        productRepository.save(p);
        return p;
    }

    public List<Product> filter(Long category, Long manufacturer) {
        if (category == null && manufacturer == null){
            return productRepository.findAll();
        }
        else if (category == null){
            Manufacturer m = this.manufacturerRepository.findById(manufacturer).orElseThrow(
                    ()->new ManufacturerNotFoundException(manufacturer));
            return productRepository.findAllByManufacturer(m);
        }
        else if (manufacturer==null){
            Category c = this.categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
            return productRepository.findAllByCategory(c);
        }
        Manufacturer m = this.manufacturerRepository.findById(manufacturer).orElseThrow(
                ()->new ManufacturerNotFoundException(manufacturer));
        Category c = this.categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
        return productRepository.findAllByCategoryAndManufacturer(c,m);
    }



}
