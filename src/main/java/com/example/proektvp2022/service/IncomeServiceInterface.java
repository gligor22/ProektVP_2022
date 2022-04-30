package com.example.proektvp2022.service;

import com.example.proektvp2022.model.Income;
import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.Product;
import com.example.proektvp2022.model.Services;

import javax.management.ServiceNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface IncomeServiceInterface {

    List<Income> findAll();

    Income findByID(Long id);

    Income create(List<Long> servicesID, List<Long> productsID, Long patient);

    List<Income> filter(Long s , Long p , Long k);

    List<Income> unpaidFilter(Long k);
}
