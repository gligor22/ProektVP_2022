package com.example.proektvp2022.service;

import com.example.proektvp2022.model.MonthlyBills;

import java.time.LocalDate;
import java.util.List;

public interface MonthlyBillsServiceInterface {

    List<MonthlyBills> listAll();

    MonthlyBills findByID(Long id);

    MonthlyBills create(LocalDate date, Float electricity, Float water, Float operator, Float heating);

    List<MonthlyBills> filter(LocalDate from, LocalDate to);
}
