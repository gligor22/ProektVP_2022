package com.example.proektvp2022.service;

import com.example.proektvp2022.model.Services;
import com.example.proektvp2022.model.ServicesTypes;
import com.example.proektvp2022.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ServicesServiceInterface {

    Services findById(Long id);

    List<Services> listAll();

    Services create( ServicesTypes type, Float price, Integer duration);

    Services delete(Long id);

    Services edit(Long id, ServicesTypes type, Float price, Integer duration);

    List<Services> filter(ServicesTypes t);
}
