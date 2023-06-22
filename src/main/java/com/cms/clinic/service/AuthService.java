package com.cms.clinic.service;

import com.cms.clinic.dto.RegisterRequest;
import com.cms.clinic.entity.Patient;

public interface AuthService {

    Patient register(RegisterRequest registerRequest);
}
