package com.example.proektvp2022.jobs;

import com.example.proektvp2022.service.impl.AppointmentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class Schedule {

    public final AppointmentService appointmentService;

    public Schedule(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Scheduled(fixedDelay = 60000)
        public void refresh()
        {
            appointmentService.deleteExpiredAppointments();
        }

}
