package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Category;
import com.example.proektvp2022.model.Manufacturer;
import com.example.proektvp2022.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findAllByCategoryAndManufacturer(Category c , Manufacturer m );

    List<Product> findAllByCategory(Category c);

    List<Product> findAllByManufacturer(Manufacturer m);
}
