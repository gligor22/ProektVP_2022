package com.example.proektvp2022.model.exceptions;

public class OneAppointmentAlreadyMade extends RuntimeException{
    public OneAppointmentAlreadyMade()
    {
        super("One Appointment Already Made by the Patient.If you want to reschedule it please contact the Admin");
    }
}
