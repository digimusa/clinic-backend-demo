package com.cms.clinic.service.impl;


import com.cms.clinic.entity.Admin;
import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.repositories.AdminRepository;
import com.cms.clinic.repositories.AppointmentRepository;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ReceptionistRepository receptionistRepository;

//  View
    public List<Admin> viewAdmins(){

        return adminRepository.findAll();
    }

    public List<Appointment> viewAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Patient> viewPatients(){
        return patientRepository.findAll();
    }

    public List<Receptionist> viewReceptionists(){
        return receptionistRepository.findAll();
    }


//    Registration






}
