package com.cms.clinic.controller;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.service.AuthService;
import com.cms.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody RegisterRequestDto request) {
        return new ResponseEntity<>(patientService.addNewPatient(request), HttpStatus.CREATED);
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestParam("token") String activationToken) {
        try {
            patientService.activateUser(activationToken);
            return ResponseEntity.ok("Account activated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid activation token.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to activate account.");
        }
    }
}
