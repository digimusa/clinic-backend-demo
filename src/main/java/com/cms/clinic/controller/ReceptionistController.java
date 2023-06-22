package com.cms.clinic.controller;

import com.cms.clinic.entity.Appointment;
import com.cms.clinic.service.impl.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceptionistController {

    private final ReceptionistService receptionistService;
    @GetMapping("/viewAppointments")
    public List<Appointment> getAllAppointments() {
        return receptionistService.viewAppointments();
    }

}
