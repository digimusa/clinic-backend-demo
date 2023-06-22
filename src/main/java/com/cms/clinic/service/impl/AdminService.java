package com.cms.clinic.service.impl;


import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.*;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.AdminRepository;
import com.cms.clinic.repositories.AppointmentRepository;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ReceptionistRepository receptionistRepository;
    private final PasswordEncoder passwordEncoder;

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

    public Receptionist addNewReceptionist(RegisterRequestDto registerRequest) {
        log.info("Inside Register new patient method {} ", registerRequest);

        Receptionist receptionist = new Receptionist();

        receptionist.setFirstName(registerRequest.getFirstName());
        receptionist.setLastName(registerRequest.getLastName());
        receptionist.setEmail(registerRequest.getEmail());
        receptionist.setRole(Role.RECEPTION);
        receptionist.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        receptionist.setEnabled("false");


        try {
            return receptionistRepository.save(receptionist);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }
    }



//    public Doctor addNewDoctor(RegisterRequestDto registerRequest) {
//        log.info("Inside Register new patient method {} ", registerRequest);
//
//        Doctor Doctor = new Doctor();
//
//        Doctor.setFirstName(registerRequest.getFirstName());
//        Doctor.setLastName(registerRequest.getLastName());
//        Doctor.setEmail(registerRequest.getEmail());
//        Doctor.setRole(Role.DOCTOR);
//        Doctor.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        Doctor.setEnabled("false");
//
//
//        try {
//            return doctorRepository.save(Doctor);
//        } catch (Exception e){
//            throw new EmailAlreadyTakenException();
//        }
    }







