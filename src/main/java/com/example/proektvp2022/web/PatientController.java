package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.service.impl.PatientService;
import org.h2.engine.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    public final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public String getPatientPage(@RequestParam(required = false) String embr, Model model){
        model.addAttribute("patients",patientService.filter(embr));
        model.addAttribute("bodyContent","patients.html");
        return "master-template";
    }

    @DeleteMapping("/patients/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePatient(@PathVariable Long id)
    {
        patientService.delete(id);
        return "redirect:/patients";
    }
    @GetMapping("/patients/edit/{id}")
    public String getEditPage(@RequestParam(required = false) String error,@PathVariable Long id, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        Patient p = patientService.findById(id);
        model.addAttribute("patient",p);
        model.addAttribute("bodyContent","addPatient.html");
        return "master-template";
    }
    @GetMapping("/patients/addPatient")
    public String getAddPage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","addPatient.html");
        return "master-template";
    }
    @PostMapping("/patients/addPatient")
    public String enrollPatient(@RequestParam(required = false) Long id,@RequestParam String name, @RequestParam String surname
            ,@RequestParam(required = false) Integer age, @RequestParam String embr,@RequestParam String gmail){
        try {
            if(id==null)
                patientService.save(name,surname,gmail,age,embr);
            else
                patientService.edit(id,name,surname,gmail,age,embr);
            return "redirect:/patients";
        }catch (InvalidArgumentsException e ){
            if(id!=null)
                return "redirect:/patients/edit/"+id+"?error=BadInput";
            return "redirect:/patients/addPatient?error=BadInput";
        }
    }
}
