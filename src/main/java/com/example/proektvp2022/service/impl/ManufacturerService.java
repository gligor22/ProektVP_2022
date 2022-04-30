package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Manufacturer;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.ManufacturerNotFoundException;
import com.example.proektvp2022.repository.JpaManufacturerRepo;
import com.example.proektvp2022.service.ManufacturerServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService implements ManufacturerServiceInterface {
    private final JpaManufacturerRepo manufacturerRepository;

    public ManufacturerService(JpaManufacturerRepo manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(()-> new ManufacturerNotFoundException(1));
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer save(String name, String address) {
        if(name==null || name.isEmpty() || address==null || address.isEmpty())
            throw new InvalidArgumentsException();
        Manufacturer m = new Manufacturer(name,address);
        return manufacturerRepository.save(m);
    }

    @Override
    public Manufacturer edit(Long id, String name, String address) {
        if(name==null || name.isEmpty() || address==null || address.isEmpty())
            throw new InvalidArgumentsException();
        Manufacturer m = manufacturerRepository.findById(id).orElseThrow(()-> new ManufacturerNotFoundException(id));
        m.setName(name);
        m.setAddress(address);
        return manufacturerRepository.save(m);
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }

}
