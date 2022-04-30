package com.example.proektvp2022.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PomosenController {

    @GetMapping
    public String getAccessDeniedPage(Model model)
    {
        model.addAttribute("bodyContent","access_denied.html");
        return "master-template";
    }

}
