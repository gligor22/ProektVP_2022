package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPatientRepo extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
    List<Patient> findAllByEmbr(String embr);
    Patient findByEmbr(String embr);
}
