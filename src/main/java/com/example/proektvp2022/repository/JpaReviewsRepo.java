package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Appointment;
import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaReviewsRepo extends JpaRepository<Reviews,Long> {
    List<Reviews> findByPatient(Patient p);
}
