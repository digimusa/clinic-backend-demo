package com.cms.clinic.service.impl;


import com.cms.clinic.entity.Doctor;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.repositories.DoctorRepository;
import com.cms.clinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DoctorService {
   private final DoctorRepository doctorRepository;
   private  final PatientRepository patientRepository;

   public List<Patient> getDoctorPatients(Long doctotId){
       log.info("Inside getDoctorPatients{} ", doctotId);
       Doctor doctor = doctorRepository.findById(doctotId)
               .orElseThrow(() -> new IllegalArgumentException("Doctor id not found" + doctotId ));
       return  patientRepository.findByDoctor(doctor);

   }


}
