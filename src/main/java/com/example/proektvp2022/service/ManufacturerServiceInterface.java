package com.example.proektvp2022.service;



import com.example.proektvp2022.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerServiceInterface {
    Manufacturer findById(Long id);
    List<Manufacturer> findAll();
    Manufacturer save(String name, String address);
    Manufacturer edit(Long id,String name, String address);
    void deleteById(Long id);

}
