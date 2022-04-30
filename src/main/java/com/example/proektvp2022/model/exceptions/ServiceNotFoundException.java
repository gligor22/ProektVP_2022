package com.example.proektvp2022.model.exceptions;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super("Service not found exception");
    }
}
