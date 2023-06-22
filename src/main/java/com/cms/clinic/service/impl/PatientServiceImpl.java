package com.cms.clinic.service.impl;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Role;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Patient addNewPatient(RegisterRequestDto registerRequest) {
        log.info("Inside Register new patient method {} ", registerRequest);

        Patient patient = new Patient();

        patient.setFirstName(registerRequest.getFirstName());
        patient.setLastName(registerRequest.getLastName());
        patient.setEmail(registerRequest.getEmail());
        patient.setRole(Role.PATIENT);
        patient.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        patient.setEnabled("false");


        try {
            return patientRepository.save(patient);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }
    }
}
