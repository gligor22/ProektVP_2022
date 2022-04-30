package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Appointment;
import com.example.proektvp2022.model.Reviews;
import com.example.proektvp2022.model.exceptions.AppointmentException;
import com.example.proektvp2022.model.exceptions.OneAppointmentAlreadyMade;
import com.example.proektvp2022.model.exceptions.ReviewFailedException;
import com.example.proektvp2022.repository.JpaReviewsRepo;
import com.example.proektvp2022.service.ReviewsServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements ReviewsServiceInterface {

        public final JpaReviewsRepo reviewsRepo;
        public final PatientService patientService;

        public ReviewsService(JpaReviewsRepo reviewsRepo, PatientService patientService) {
            this.reviewsRepo = reviewsRepo;
            this.patientService = patientService;
        }

        @Override
        public List<Reviews> findAll() {
            return reviewsRepo.findAll();
        }

        @Override
        public List<Reviews> findByPatient(Long patientId) {
            return reviewsRepo.findByPatient(patientService.findById(patientId));
        }

        @Override
        public Reviews create(Long patientId, String message) {
            if(patientId!=null && message!=null && !message.equals("") ) {
                return reviewsRepo.save(new Reviews(patientService.findById(patientId), message));
            }
            else throw new ReviewFailedException();
        }

        @Override
        public Reviews delete(Long id) {
            reviewsRepo.deleteById(id);
            return null;
        }

        @Override
        public List<Reviews> filter(Long patientId) {
            if(patientId==null)
                return reviewsRepo.findAll();
            else
                return reviewsRepo.findByPatient(patientService.findById(patientId));
        }
    }
