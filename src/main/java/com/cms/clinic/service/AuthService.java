package com.cms.clinic.service;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;

public interface AuthService {

    Patient register(RegisterRequestDto registerRequest);
}