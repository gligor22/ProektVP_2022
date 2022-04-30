package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Services;
import com.example.proektvp2022.model.ServicesTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public interface JpaServicesRepo extends JpaRepository<Services,Long> {
    List<Services> findAllByType(ServicesTypes t);
}
