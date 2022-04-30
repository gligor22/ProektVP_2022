package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Income;
import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.Product;
import com.example.proektvp2022.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaIncomeRepo extends JpaRepository<Income,Long> {

    List<Income> findAllByProductsContainsAndServicesContains(Product p,Services s);
    List<Income> findAllByProductsContains(Product p);
    List<Income> findAllByServicesContains(Services s);
    List<Income> findAllByPatient(Patient p);
    List<Income> findAllByPatientAndServicesContains(Patient p , Services s);
    List<Income> findAllByPatientAndProductsContains(Patient p , Product k);
    List<Income> findAllByProductsContainsAndServicesContainsAndPatient(Product p,Services s,Patient k);
    List<Income> findAllByPatientAndDoneEndsWith(Patient p,Integer i);


}
