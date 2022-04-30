package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Category;
import com.example.proektvp2022.model.Manufacturer;
import com.example.proektvp2022.model.MonthlyBills;
import com.example.proektvp2022.model.exceptions.CategoryNotFoundException;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.ManufacturerNotFoundException;
import com.example.proektvp2022.model.exceptions.MonthlyBillNotFoundException;
import com.example.proektvp2022.repository.JpaMonthlyBillsRepo;
import com.example.proektvp2022.service.MonthlyBillsServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonthlyBillsService implements MonthlyBillsServiceInterface {

    public final JpaMonthlyBillsRepo monthlyBillsRepo;

    public MonthlyBillsService(JpaMonthlyBillsRepo monthlyBillsRepo) {
        this.monthlyBillsRepo = monthlyBillsRepo;
    }

    @Override
    public List<MonthlyBills> listAll() {
        return monthlyBillsRepo.findAll();
    }

    @Override
    public MonthlyBills findByID(Long id) {
        return monthlyBillsRepo.findById(id).orElseThrow(MonthlyBillNotFoundException::new);
    }

    @Override
    public MonthlyBills create(LocalDate date, Float electricity, Float water, Float operator, Float heating) {
        if(electricity==null || water==null || operator==null || heating==null)
            throw new InvalidArgumentsException();
        float ddv = ((electricity+water+operator+heating)*18)/100;
        return monthlyBillsRepo.save(new MonthlyBills(date,electricity,water,operator,heating,ddv));

    }

    @Override
    public List<MonthlyBills> filter(LocalDate from, LocalDate to) {
        if (from == null && to == null){
            return monthlyBillsRepo.findAll();
        }
        else if (to == null){
            return monthlyBillsRepo.findAllByDateAfter(from);
        }
        else if (from==null){
            return monthlyBillsRepo.findAllByDateBefore(to);
        }
        return monthlyBillsRepo.findAllByDateAfterAndDateBefore(from,to);
    }
}
