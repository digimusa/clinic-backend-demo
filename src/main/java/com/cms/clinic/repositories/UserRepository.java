package com.cms.clinic.repositories;

import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Patient> findByEmail(String email);
}
