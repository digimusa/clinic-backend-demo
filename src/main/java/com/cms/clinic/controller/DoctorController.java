package com.cms.clinic.controller;

import com.cms.clinic.entity.Patient;
import com.cms.clinic.service.impl.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private  final DoctorService doctorService;


    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<Patient>> getDoctorPatients(@PathVariable Long doctorId){
        List<Patient> patients = doctorService.getDoctorPatients(doctorId);
        return  new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
