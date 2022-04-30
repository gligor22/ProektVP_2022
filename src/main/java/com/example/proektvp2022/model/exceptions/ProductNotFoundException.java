package com.example.proektvp2022.model.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){
        super("Product not found Exception");
    }
}
