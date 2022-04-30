package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.MonthlyBills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaMonthlyBillsRepo extends JpaRepository<MonthlyBills,Long> {
    List<MonthlyBills> findAllByDateAfter(LocalDate date);
    List<MonthlyBills> findAllByDateBefore(LocalDate date);
    List<MonthlyBills> findAllByDateAfterAndDateBefore(LocalDate from,LocalDate to);
}
