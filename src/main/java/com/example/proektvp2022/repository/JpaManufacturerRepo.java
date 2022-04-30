package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaManufacturerRepo extends JpaRepository<Manufacturer,Long> {
}
