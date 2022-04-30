package com.example.proektvp2022.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private Patient patient;

    private String message;

    public Reviews(Patient patient, String message) {
        this.patient = patient;
        this.message = message;
    }

    public Reviews (){}
}
