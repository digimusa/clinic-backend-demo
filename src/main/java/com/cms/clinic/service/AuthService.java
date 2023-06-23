package com.cms.clinic.service;

import com.cms.clinic.dto.AuthenticationRequestDto;
import com.cms.clinic.dto.AuthenticationResponse;
import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Patient;

public interface AuthService {

    AuthenticationResponse login(AuthenticationRequestDto authenticationRequestDto);
}
