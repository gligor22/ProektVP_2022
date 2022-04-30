package com.example.proektvp2022.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String gmail;

    private Integer age;

    private String embr;

    public Patient() {
    }

    public Patient( String name, String surname, String gmail, Integer age, String embr) {
        this.name = name;
        this.surname = surname;
        this.gmail = gmail;
        this.age = age;
        this.embr = embr;
    }
}
