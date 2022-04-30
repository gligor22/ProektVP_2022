package com.example.proektvp2022.web;

import com.example.proektvp2022.model.exceptions.AppointmentException;
import com.example.proektvp2022.model.exceptions.ReviewFailedException;
import com.example.proektvp2022.service.impl.PatientService;
import com.example.proektvp2022.service.impl.ReviewsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    public final ReviewsService reviewsService;
    public final PatientService patientService;

    public ReviewController(ReviewsService reviewsService, PatientService patientService) {
        this.reviewsService = reviewsService;
        this.patientService = patientService;
    }

    @GetMapping
    public String getReviewPAge(Model model, @RequestParam(required = false) Long patientId,
                               @RequestParam(required = false) String error)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("patients",patientService.findAll());
        model.addAttribute("reviews",reviewsService.filter(patientId));
        model.addAttribute("bodyContent","reviews.html");
        return "master-template";
    }

    @PostMapping("/add")
    public String enroll(@RequestParam(required = false) Long patientId,
                                @RequestParam(required = false) String message)
    {
        try {
            reviewsService.create(patientId,message);
            return "redirect:/reviews";
        }catch (ReviewFailedException ex){
            return "redirect:/reviews?error="+ex.getMessage();
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteReview(@PathVariable Long id){
        reviewsService.delete(id);
        return "redirect:/reviews";
    }
}
