package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Appointment;
import com.example.proektvp2022.model.exceptions.AppointmentException;
import com.example.proektvp2022.model.exceptions.OneAppointmentAlreadyMade;
import com.example.proektvp2022.repository.JpaAppointmentRepo;
import com.example.proektvp2022.repository.JpaPatientRepo;
import com.example.proektvp2022.service.AppointmentServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AppointmentService implements AppointmentServiceInterface {

    public final JpaAppointmentRepo appointmentRepo;
    public final PatientService patientService;

    public AppointmentService(JpaAppointmentRepo appointmentRepo, PatientService patientService) {
        this.appointmentRepo = appointmentRepo;
        this.patientService = patientService;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> todayList() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minus(Period.ofDays(1));
        LocalDate date = LocalDate.of(yesterday.getYear(),yesterday.getMonth(),yesterday.getDayOfMonth());
        return appointmentRepo.findAllByTerminAfter(date);
    }

    @Override
    public List<Appointment> findByPatient(Long patientId) {
        return appointmentRepo.findByPatient(patientService.findById(patientId));
    }

    @Override
    public Appointment create(Long patientId, LocalDate time) {
        if(patientId!=null && time!=null) {
            if(appointmentRepo.findByPatient(patientService.findById(patientId)).isEmpty())
                return appointmentRepo.save(new Appointment(patientService.findById(patientId), time));
            else throw new OneAppointmentAlreadyMade();
        }
        else throw new AppointmentException();
    }

    @Override
    public Appointment delete(Long id) {
        appointmentRepo.deleteById(id);
        return null;
    }

    @Override
    public List<Appointment> filter(Long patientId) {
        if(patientId==null)
            return appointmentRepo.findAll();
        else
            return appointmentRepo.findByPatient(patientService.findById(patientId));
    }

    @Override
    public void deleteExpiredAppointments() {
        List<Appointment> all = appointmentRepo.findAll();
        for (Appointment a : all) {
            if (a.getTermin().compareTo(LocalDate.now()) < 0) {
                appointmentRepo.delete(a);
            }
        }
    }

}
