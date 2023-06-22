package com.cms.clinic.repositories;

import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    Optional<Patient> findByEmail(String email);
}
