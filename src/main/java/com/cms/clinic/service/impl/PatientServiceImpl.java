package com.cms.clinic.service.impl;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Role;
import com.cms.clinic.entity.User;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.repositories.UserRepository;
import com.cms.clinic.service.EmailService;
import com.cms.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private String baseUrl = "http://localhost:8081/api/v1/patient";


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

        String activationToken = generateActivationToken();
        patient.setActivationToken(activationToken);


        try {
            sendActivationEmail(patient);
            return patientRepository.save(patient);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    @Override
    public void activateUser(String activationToken) {
        Patient user = patientRepository.findByActivationToken(activationToken);
        if (user != null) {
            user.setEnabled("true");
            user.setActivationToken(null);
            patientRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid activation token.");
        }
    }

    private void sendActivationEmail(Patient user) {
        emailService.sendSimpleEmail(user.getEmail(),
                "Dear " + user.getFirstName() + "\n\n"
                        + "Please click the following link to activate your account:\n"
                        + baseUrl + "/activate?token=" + user.getActivationToken(),
                "Account Verification");
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }
}
