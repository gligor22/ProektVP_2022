package com.example.proektvp2022.repository;

import com.example.proektvp2022.model.Employee;

import com.example.proektvp2022.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllBySkillsContaining(Skill skill);
    List<Employee> findAllByEmploymentDateBefore(LocalDate date);
    List<Employee> findAllBySkillsContainingAndEmploymentDateBefore(Skill skill, LocalDate date);
    Employee findByEmail(String email);
}
