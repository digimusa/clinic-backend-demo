package com.cms.clinic.service.impl;

import com.cms.clinic.dto.RegisterRequest;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Role;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.repositories.RoleRepository;
import com.cms.clinic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

//    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Patient register(RegisterRequest registerRequest) {

        Patient patient = new Patient();

        patient.setFirstName(registerRequest.getFirstName());
        patient.setLastName(registerRequest.getLastName());
        patient.setEmail(registerRequest.getEmail());
        patient.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role role = roleRepository.findByRoleName("User").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        patient.setRoles(roles);

        try {
            return patientRepository.save(patient);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }
    }
}
