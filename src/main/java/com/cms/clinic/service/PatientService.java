package com.cms.clinic.service;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;

public interface PatientService {

    Patient addNewPatient(RegisterRequestDto registerRequest);
    void activateUser(String activationToken);
}
