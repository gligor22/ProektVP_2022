package com.example.proektvp2022.model.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(long id){
        super("Category not found Exception "+id);
    }
}
