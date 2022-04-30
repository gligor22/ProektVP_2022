package com.example.proektvp2022.web;

import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.service.impl.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IncomeController {

    public final IncomeService incomeService;
    public final ProductService productService;
    public final ServicesService servicesService;
    public final PatientService patientService;

    public IncomeController(IncomeService incomeService, ProductService productService, ServicesService servicesService, PatientService patientService) {
        this.incomeService = incomeService;
        this.productService = productService;
        this.servicesService = servicesService;
        this.patientService = patientService;
    }

    @GetMapping("/incomes")
    public String getIncomePage(Model model,@RequestParam(required = false) Long patientID,
                               @RequestParam(required = false) Long productID,
                               @RequestParam(required = false) Long serviceID)
    {
        model.addAttribute("incomes",incomeService.filter(serviceID,productID,patientID));
        model.addAttribute("services",servicesService.listAll());
        model.addAttribute("products",productService.findAll());
        model.addAttribute("patients",patientService.findAll());
        model.addAttribute("bodyContent","incomes.html");
        return "master-template";
    }
    @GetMapping("/incomes/addIncome")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddPage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("services",servicesService.listAll());
        model.addAttribute("products",productService.findAll());
        model.addAttribute("patients",patientService.findAll());
        model.addAttribute("bodyContent","addIncome.html");
        return "master-template";
    }
    @PostMapping("/incomes/addIncome")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String enrollIncome(@RequestParam(required = false) List<Long> servicesIDs,
                                @RequestParam(required = false) List<Long> productsIDs,
                                @RequestParam(required = false) Long patientID)
    {
        try {
            incomeService.create(servicesIDs,productsIDs,patientID);
            return "redirect:/incomes";
        }catch (InvalidArgumentsException e ){
            return "redirect:/incomes/addIncome?error=BadInput";
        }
    }

}
