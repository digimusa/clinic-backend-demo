package com.cms.clinic.repositories;

import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

    @Query(value = "SELECT * FROM Receptionist WHERE email = ?", nativeQuery = true)
    Optional<Receptionist> findByEmail(String email);
}
git 