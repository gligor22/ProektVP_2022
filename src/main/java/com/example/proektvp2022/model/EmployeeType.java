package com.example.proektvp2022.model;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeType implements GrantedAuthority {
    OFFICE_MANAGER,
    MEDICAL_NURSE,
    DOCTOR,
    CLEANER,
    SECURITY;

    @Override
    public String getAuthority() {
        return name();
    }
}