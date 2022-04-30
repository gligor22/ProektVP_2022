package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Income;
import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.Product;
import com.example.proektvp2022.model.Services;
import com.example.proektvp2022.model.exceptions.*;
import com.example.proektvp2022.repository.JpaIncomeRepo;
import com.example.proektvp2022.repository.JpaPatientRepo;
import com.example.proektvp2022.repository.JpaProductRepo;
import com.example.proektvp2022.repository.JpaServicesRepo;
import com.example.proektvp2022.service.IncomeServiceInterface;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class IncomeService implements IncomeServiceInterface {

    public final JpaIncomeRepo incomeRepo;
    public final JpaProductRepo productRepo;
    public final JpaServicesRepo servicesRepo;
    public final JpaPatientRepo patientRepo;

    public IncomeService(JpaIncomeRepo incomeRepo, JpaProductRepo productRepo, JpaServicesRepo servicesRepo, JpaPatientRepo patientRepo) {
        this.incomeRepo = incomeRepo;
        this.productRepo = productRepo;
        this.servicesRepo = servicesRepo;
        this.patientRepo = patientRepo;
    }

    @Override
    public List<Income> findAll(){
        return incomeRepo.findAll();
    }

    @Override
    public Income findByID(Long id) {
        return incomeRepo.findById(id).orElseThrow(IncompatibleClassChangeError::new);
    }

    @Override
    public Income create(List<Long> servicesID, List<Long> productsID, Long patient) {
        if(patient==null || servicesID==null || productsID==null)
            throw new InvalidArgumentsException();
        List<Services> services = servicesRepo.findAllById(servicesID);
        List<Product> products = productRepo.findAllById(productsID);
        Patient p = patientRepo.findById(patient).orElseThrow(PatientNotFoundException::new);
        return incomeRepo.save(new Income(services,products,p));

    }

    @Override
    public List<Income> filter(Long serviceID, Long productID, Long patientID) {
        if(serviceID==null && productID==null && patientID==null)
        {
            return incomeRepo.findAll();
        }
        else if(serviceID==null && productID==null)
        {
            return incomeRepo.findAllByPatient(patientRepo.findById(patientID).orElseThrow(PatientNotFoundException::new));
        }
        else if(serviceID==null && patientID==null)
        {
            return incomeRepo.findAllByProductsContains(productRepo.findById(productID).orElseThrow(ProductNotFoundException::new));
        }
        else if(productID==null && patientID==null)
        {
            return incomeRepo.findAllByServicesContains(servicesRepo.findById(serviceID).orElseThrow(ServiceNotFoundException::new));
        }
        else if(serviceID==null)
            return incomeRepo.findAllByPatientAndProductsContains(
                    patientRepo.findById(patientID).orElseThrow(PatientNotFoundException::new),
                    productRepo.findById(productID).orElseThrow(ProductNotFoundException::new));
        else if(productID ==null)
            return incomeRepo.findAllByPatientAndServicesContains(
                    patientRepo.findById(patientID).orElseThrow(PatientNotFoundException::new),
                    servicesRepo.findById(serviceID).orElseThrow(ServiceNotFoundException::new));
        else if(patientID==null)
            return incomeRepo.findAllByProductsContainsAndServicesContains(
                    productRepo.findById(productID).orElseThrow(ProductNotFoundException::new),
                    servicesRepo.findById(serviceID).orElseThrow(ServiceNotFoundException::new));
        else return incomeRepo.findAllByProductsContainsAndServicesContainsAndPatient(
                    productRepo.findById(productID).orElseThrow(ProductNotFoundException::new),
                    servicesRepo.findById(serviceID).orElseThrow(ServiceNotFoundException::new),
                    patientRepo.findById(patientID).orElseThrow(PatientNotFoundException::new));
    }

    @Override
    public List<Income> unpaidFilter(Long k) {
        return incomeRepo.findAllByPatientAndDoneEndsWith(patientRepo.findById(k).orElseThrow(PatientNotFoundException::new),0);
    }
    public Income pay(Long k)
    {
        Income income=incomeRepo.findById(k).orElseThrow(IncomeNotFoundException::new);
        income.setDone(1);
        return incomeRepo.save(income);
    }

    public float payPrice(Long id)
    {
        Income income=incomeRepo.findById(id).orElseThrow(IncomeNotFoundException::new);
        List<Product> products = income.getProducts();
        float price = 0;
        for (Product r : products) {
            price += r.getPrice();
        }
        List<Services> services = income.getServices();
        for(Services s : services){
            price+=s.getPrice();
        }
        return price;
    }



}
