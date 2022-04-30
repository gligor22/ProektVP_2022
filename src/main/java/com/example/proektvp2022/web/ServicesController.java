package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Patient;
import com.example.proektvp2022.model.Services;
import com.example.proektvp2022.model.ServicesTypes;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.service.impl.ServicesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServicesController {

    public final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/services")
    public String getServicesPage(Model model,@RequestParam(required = false) ServicesTypes type)
    {
//        if(type==null)
//            model.addAttribute("footerElem", "footerElem.html");
        model.addAttribute("types", ServicesTypes.values());
        model.addAttribute("services",servicesService.filter(type));
        model.addAttribute("bodyContent","services.html");
        return "master-template";
    }

    @DeleteMapping("/services/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePatient(@PathVariable Long id)
    {
        servicesService.delete(id);
        return "redirect:/services";
    }
    @GetMapping("/services/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditPage(@RequestParam(required = false) String error, @PathVariable Long id, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        Services p = servicesService.findById(id);
        model.addAttribute("service",p);
        model.addAttribute("types",ServicesTypes.values());
        model.addAttribute("bodyContent","addServices.html");
        return "master-template";
    }
    @GetMapping("/services/addServices")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddPage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","addServices.html");
        model.addAttribute("types",ServicesTypes.values());
        return "master-template";
    }
    @PostMapping("/services/addServices")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String enrollPatient(@RequestParam(required = false) Long id,@RequestParam(required = false) Float price
            ,@RequestParam ServicesTypes type, @RequestParam(required = false) Integer duration){
        try {
            if(id==null)
                servicesService.create(type,price,duration);
            else
                servicesService.edit(id,type,price,duration);
            return "redirect:/services";
        }catch (InvalidArgumentsException e ){
            if(id!=null)
                return "redirect:/services/edit/"+id+"?error=BadInput";
            return "redirect:/services/addServices?error=BadInput";
        }
    }

}
