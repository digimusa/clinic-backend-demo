package com.cms.clinic.service.impl;

import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.repositories.AppointmentRepository;
import com.cms.clinic.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final AppointmentRepository appointmentRepository;

    public List<Appointment> viewAppointments(){
        return appointmentRepository.findAll();
    }
}
