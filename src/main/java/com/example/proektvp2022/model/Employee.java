package com.example.proektvp2022.model;


import com.example.proektvp2022.model.EmployeeType;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class Employee {

    public Employee() {
    }

    public Employee(String name, String email, EmployeeType type, List<Skill> skills, LocalDate employmentDate,Float salary) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.skills = skills;
        this.employmentDate = employmentDate;
        this.salary=salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate employmentDate;

    private String name;

    private String email;

    private Float salary;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }
}
