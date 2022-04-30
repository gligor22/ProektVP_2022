package com.example.proektvp2022.service;


import com.example.proektvp2022.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServiceInterface {

    List<Appointment> findAll();
    List<Appointment> todayList();
    List<Appointment> findByPatient(Long patientId);
    Appointment create(Long patientId, LocalDate time);
    Appointment delete(Long id);
    List<Appointment> filter(Long patientId);

    void deleteExpiredAppointments();
}
