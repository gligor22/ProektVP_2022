package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Employee;
import com.example.proektvp2022.model.EmployeeType;
import com.example.proektvp2022.model.Skill;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.InvalidEmployeeIdException;
import com.example.proektvp2022.model.exceptions.InvalidSkillIdException;
import com.example.proektvp2022.repository.EmployeeRepository;
import com.example.proektvp2022.repository.SkillRepository;
import com.example.proektvp2022.service.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Employee> listAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, EmployeeType type, List<Long> skillId, LocalDate employmentDate,Float salary) {
        if(name==null || name.isEmpty() || email==null || email.isEmpty() || salary==null || employmentDate==null || skillId==null)
            throw new InvalidArgumentsException();
        List<Skill> skills = this.skillRepository.findAllById(skillId);
        Employee employee = new Employee(name,email,type,skills,employmentDate,salary);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, String email, EmployeeType type, List<Long> skillId, LocalDate employmentDate,Float salary) {
        if(name==null || name.isEmpty() || email==null || email.isEmpty() || salary==null || employmentDate==null || skillId==null)
            throw new InvalidArgumentsException();
        Employee employee = this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
        List<Skill> skills = this.skillRepository.findAllById(skillId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setType(type);
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);
        employee.setSalary(salary);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId != null && yearsOfService != null){
            int year = LocalDate.now().getYear() - yearsOfService;
            LocalDate date = LocalDate.of(year,LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            Skill skill = this.skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            return this.employeeRepository
                    .findAllBySkillsContainingAndEmploymentDateBefore(skill,date);
        }
        if (yearsOfService != null){
            int year = LocalDate.now().getYear() - yearsOfService;
            LocalDate date = LocalDate.of(year,LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            return this.employeeRepository
                    .findAllByEmploymentDateBefore(date);
        }
        if (skillId != null){
            Skill skill = this.skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            return this.employeeRepository
                    .findAllBySkillsContaining(skill);
        }
        return this.employeeRepository.findAll();
    }
}
