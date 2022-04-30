package com.example.proektvp2022.model.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(){
        super("Patient not found exception");
    }
}
