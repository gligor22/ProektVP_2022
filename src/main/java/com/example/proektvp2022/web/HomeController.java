package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.service.impl.AppointmentService;
import com.example.proektvp2022.service.impl.IncomeService;
import com.example.proektvp2022.service.impl.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    public final PatientService patientService;
    public final IncomeService incomeService;
    public final AppointmentService appointmentService;

    public HomeController(PatientService patientService, IncomeService incomeService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.incomeService = incomeService;
        this.appointmentService = appointmentService;
    }


    @GetMapping
    public String getHomePage(Model model, @RequestParam(required = false) String embr){
        if(embr!=null) {
            Patient p = patientService.findByEMBR(embr);
            if(p!=null){
                model.addAttribute("patient",p);
                model.addAttribute("incomes", incomeService.filter(null,null,p.getId()));
            }
        }
        else {
            model.addAttribute("footerElem","footerElem.html");
        }
        model.addAttribute("appointments",appointmentService.todayList());
        model.addAttribute("bodyContent","home.html");
        return "master-template";
    }
    @PostMapping("/pay/{id}")
    public String pay(@PathVariable Long id,@RequestParam String embr)
    {
        incomeService.pay(id);
        return "redirect:/home?embr="+embr;
    }
    @GetMapping("/contact")
    public String getContactPage(Model model){
        model.addAttribute("bodyContent","contact.html");
        return "master-template";
    }
    @GetMapping("/about")
    public String getAbout(Model model)
    {
        return "aboutus";
    }
}
