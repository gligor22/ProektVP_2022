package com.example.proektvp2022.service;

import com.example.proektvp2022.model.Appointment;
import com.example.proektvp2022.model.Reviews;

import java.time.LocalDate;
import java.util.List;

public interface ReviewsServiceInterface {
    List<Reviews> findAll();
    List<Reviews> findByPatient(Long patientId);
    Reviews create(Long patientId, String message);
    Reviews delete(Long id);
    List<Reviews> filter(Long patientId);
}
