package com.example.proektvp2022.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("Fake Credentials");
    }
}
