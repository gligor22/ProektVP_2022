package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Employee;
import com.example.proektvp2022.model.EmployeeType;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.service.EmployeeService;
import com.example.proektvp2022.service.SkillService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeController {

    public final EmployeeService employeeService;
    public final SkillService skillService;

    public EmployeeController(EmployeeService employeeService, SkillService skillService) {
        this.employeeService = employeeService;
        this.skillService = skillService;
    }

    @GetMapping("/employees")
    public String getEmployeesPage(@RequestParam(required = false) Long skillId,
                                   @RequestParam(required = false) Integer yearsOfService,
                                   Model model) {
        List<Employee> employees;
        if (skillId == null && yearsOfService == null) {
            employees = this.employeeService.listAll();
        } else {
            employees = this.employeeService.filter(skillId, yearsOfService);
        }
        model.addAttribute("employees", employees);
        model.addAttribute("skills", this.skillService.listAll());
        model.addAttribute("bodyContent","employees.html");
        return "master-template";
    }
    @DeleteMapping("/employees/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteEmployee(@PathVariable Long id)
    {
        employeeService.delete(id);
        return "redirect:/employees";
    }
    @GetMapping("/employees/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditPage(@RequestParam(required = false) String error, @PathVariable Long id, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        Employee p = employeeService.findById(id);
        model.addAttribute("employee",p);
        model.addAttribute("bodyContent","addEmployee.html");
        model.addAttribute("types",EmployeeType.values());
        model.addAttribute("skills",skillService.listAll());
        return "master-template";
    }
    @GetMapping("/employees/addEmployee")
    public String getAddPage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","addEmployee.html");
        model.addAttribute("types",EmployeeType.values());
        model.addAttribute("skills",skillService.listAll());
        return "master-template";
    }
    @PostMapping("/employees/addEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String enrollPatient(@RequestParam(required = false) Long id, @RequestParam String name, @RequestParam String gmail,
                                @RequestParam EmployeeType type, @RequestParam(required = false) List<Long> skills,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate employmentDate, @RequestParam(required = false) Float salary){
        try {
            if(id==null)
                employeeService.create(name,gmail,type,skills,employmentDate,salary);
            else
                employeeService.update(id,name,gmail,type,skills,employmentDate,salary);
            return "redirect:/employees";
        }catch (InvalidArgumentsException e ){
            if(id!=null)
                return "redirect:/employees/edit/"+id+"?error=BadInput";
            return "redirect:/employees/addEmployee?error=BadInput";
        }
    }

}
