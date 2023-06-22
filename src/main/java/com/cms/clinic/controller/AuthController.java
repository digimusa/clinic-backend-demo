package com.cms.clinic.controller;

import com.cms.clinic.dto.AuthenticationRequestDto;
import com.cms.clinic.dto.AuthenticationResponse;
import com.cms.clinic.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequestDto request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}
