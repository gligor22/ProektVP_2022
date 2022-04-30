package com.example.proektvp2022.model.exceptions;

public class ManufacturerNotFoundException extends RuntimeException{
    public ManufacturerNotFoundException(long id)
    {
        super("ManufacturerNotFoundException " + id);
    }
}
