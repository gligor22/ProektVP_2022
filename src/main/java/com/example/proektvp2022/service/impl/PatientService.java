package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.PatientNotFoundException;
import com.example.proektvp2022.repository.JpaPatientRepo;
import com.example.proektvp2022.service.PatientServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    public final JpaPatientRepo patientRepo;

    public PatientService(JpaPatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepo.findById(id).orElseThrow(PatientNotFoundException::new);
    }

    @Override
    public Patient findByName(String name) {
        return patientRepo.findByName(name);
    }

    @Override
    public Patient findByEMBR(String name) {
        if(name!=null && !name.isEmpty()) {
            Patient p = patientRepo.findByEmbr(name);
            if(p!=null)
                return p;
            return null;
        }
        return null;
    }

    @Override
    public Patient save(String name, String surname, String gmail, Integer age, String embr) {
        if (name==null || name.isEmpty() || surname==null || surname.isEmpty() ||
            gmail==null || gmail.isEmpty() || embr==null || embr.isEmpty() || age==null)
        {
            throw new InvalidArgumentsException();
        }
        return patientRepo.save(new Patient(name,surname,gmail,age,embr));
    }

    @Override
    public Patient edit(Long id, String name, String surname, String gmail, Integer age, String embr) {
        Patient p = patientRepo.findById(id).orElseThrow(PatientNotFoundException::new);
        if (name==null || name.isEmpty() || surname==null || surname.isEmpty() ||
                gmail==null || gmail.isEmpty() || embr==null || embr.isEmpty() || age==null)
        {
            throw new InvalidArgumentsException();
        }
        p.setName(name);
        p.setGmail(gmail);
        p.setSurname(surname);
        p.setEmbr(embr);
        p.setAge(age);
        return patientRepo.save(p);
    }

    @Override
    public Patient delete(Long id) {
        Patient p = patientRepo.findById(id).orElseThrow(PatientNotFoundException::new);
        patientRepo.deleteById(id);
        return p;
    }

    @Override
    public List<Patient> filter(String embr) {
        if(embr==null || embr.isEmpty())
            return patientRepo.findAll();
        else
            return patientRepo.findAllByEmbr(embr);
    }

}
