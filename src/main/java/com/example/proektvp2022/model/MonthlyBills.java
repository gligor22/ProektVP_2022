package com.example.proektvp2022.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class MonthlyBills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Float electricity;

    private Float water;

    private Float operator;

    private Float heating;

    private Float DDV;

    public MonthlyBills() {
    }

    public MonthlyBills(LocalDate date, Float electricity, Float water, Float operator, Float heating, Float DDV) {
        this.date = date;
        this.electricity = electricity;
        this.water = water;
        this.operator = operator;
        this.heating = heating;
        this.DDV = DDV;
    }
}
