package com.example.proektvp2022.model.exceptions;

public class MonthlyBillNotFoundException extends RuntimeException{
    public MonthlyBillNotFoundException(){
        super("Bill not found exception");
    }
}
