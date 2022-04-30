package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Appointment;
import com.example.proektvp2022.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JpaAppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findAllByTerminAfter(LocalDate date);

    List<Appointment> findByPatient(Patient p);

}
