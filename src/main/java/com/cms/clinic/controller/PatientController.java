package com.cms.clinic.controller;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.service.AuthService;
import com.cms.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody RegisterRequestDto request) {
        return new ResponseEntity<>(patientService.addNewPatient(request), HttpStatus.CREATED);
    }
}
