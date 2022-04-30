package com.example.proektvp2022.service;

import com.example.proektvp2022.model.Patient;

import java.util.List;

public interface PatientServiceInterface {
    List<Patient> findAll();

    Patient findById(Long id);

    Patient findByName(String name);

    Patient findByEMBR(String name);

    Patient save( String name, String surname, String gmail, Integer age, String embr);
    Patient edit(Long id, String name, String surname, String gmail, Integer age, String embr);

    Patient delete(Long id);

    List<Patient> filter(String embr);



}
