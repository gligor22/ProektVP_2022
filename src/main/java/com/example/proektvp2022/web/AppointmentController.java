package com.example.proektvp2022.web;

import com.example.proektvp2022.model.exceptions.AppointmentException;
import com.example.proektvp2022.model.exceptions.OneAppointmentAlreadyMade;
import com.example.proektvp2022.service.impl.AppointmentService;
import com.example.proektvp2022.service.impl.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    public final AppointmentService appointmentService;
    public final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }
    @GetMapping
    public String getBillsPage(Model model, @RequestParam(required = false) Long patientId,
                               @RequestParam(required = false) String error)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("patients",patientService.findAll());
        model.addAttribute("appointments",appointmentService.filter(patientId));
        model.addAttribute("bodyContent","appointments.html");
        return "master-template";
    }

    @PostMapping("/add")
    public String enrollPatient(@RequestParam(required = false) Long patientId,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate time)
    {
        try {
            appointmentService.create(patientId,time);
            return "redirect:/appointments";
        }catch (AppointmentException | OneAppointmentAlreadyMade ex){
            return "redirect:/appointments?error="+ex.getMessage();
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteAppointment(@PathVariable Long id){
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
