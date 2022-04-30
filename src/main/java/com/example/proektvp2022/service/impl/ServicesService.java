package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Services;
import com.example.proektvp2022.model.ServicesTypes;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.ServiceNotFoundException;
import com.example.proektvp2022.repository.JpaServicesRepo;
import com.example.proektvp2022.service.ServicesServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicesService implements ServicesServiceInterface {

    public final JpaServicesRepo servicesRepo;

    public ServicesService(JpaServicesRepo servicesRepo) {
        this.servicesRepo = servicesRepo;
    }

    @Override
    public Services findById(Long id) {
        return servicesRepo.findById(id).orElseThrow(ServiceNotFoundException::new);
    }

    @Override
    public List<Services> listAll() {
        return servicesRepo.findAll();
    }

    @Override
    public Services create( ServicesTypes type, Float price, Integer duration) {
        if(type==null || price==null || duration==null)
            throw new InvalidArgumentsException();
        return servicesRepo.save(new Services(type,price,duration));
    }

    @Override
    public Services delete(Long id) {
        Services s = servicesRepo.findById(id).orElseThrow(ServiceNotFoundException::new);
        servicesRepo.delete(s);
        return s;
    }

    @Override
    public Services edit(Long id, ServicesTypes type, Float price, Integer duration) {
        Services s = servicesRepo.findById(id).orElseThrow(ServiceNotFoundException::new);
        if(type==null || price==null || duration==null)
            throw new InvalidArgumentsException();
        s.setDuration(duration);
        s.setPrice(price);
        s.setType(type);
        return s;
    }

    @Override
    public List<Services> filter(ServicesTypes t) {
        if(t!=null)
            return servicesRepo.findAllByType(t);
        return servicesRepo.findAll();
    }

}
