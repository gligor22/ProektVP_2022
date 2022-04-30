package com.example.proektvp2022.web;

import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.service.impl.MonthlyBillsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@Controller
public class BillsController {

    public final MonthlyBillsService billsService;

    public BillsController(MonthlyBillsService billsService) {
        this.billsService = billsService;
    }

    @GetMapping("bills")
    public String getBillsPage(Model model,@RequestParam(required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                               @RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to)
    {
        model.addAttribute("bills",billsService.filter(from,to));
        model.addAttribute("bodyContent","bills.html");
        return "master-template";
    }
    @GetMapping("/bills/addBill")
    public String getAddPage(@RequestParam(required = false) String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","addBill.html");
        return "master-template";
    }
    @PostMapping("/bills/addBill")
    public String enrollPatient(@RequestParam(required = false) Float electricity,
                                @RequestParam(required = false) Float water,
                                @RequestParam(required = false) Float operator,
                                @RequestParam(required = false) Float heating)
    {
        try {
            billsService.create(LocalDate.now(),electricity,water,operator,heating);
            return "redirect:/bills";
        }catch (InvalidArgumentsException e ){
            return "redirect:/bills/addBill?error=BadInput";
        }
    }

}
