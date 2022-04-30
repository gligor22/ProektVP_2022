package com.example.proektvp2022.model.exceptions;

public class IncomeNotFoundException extends RuntimeException{

    public IncomeNotFoundException()
    {
        super("Income not found exception");
    }
}
