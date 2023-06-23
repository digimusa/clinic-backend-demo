package com.cms.clinic.repositories;

import com.cms.clinic.entity.Doctor;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    List<Patient> findByDoctor(Doctor doctor);

    Patient findByActivationToken(String activationToken);
}
