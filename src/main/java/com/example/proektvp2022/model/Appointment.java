package com.example.proektvp2022.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private LocalDate termin;

    public Appointment(){}

    public Appointment( Patient patient, LocalDate termin) {
        this.patient = patient;
        this.termin = termin;
    }
}
