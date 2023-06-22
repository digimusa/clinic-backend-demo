package com.cms.clinic.service.impl;

import com.cms.clinic.dto.AuthenticationRequestDto;
import com.cms.clinic.dto.AuthenticationResponse;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.User;
import com.cms.clinic.exception.AccountNotActivatedException;
import com.cms.clinic.exception.InvalidCredentialsException;
import com.cms.clinic.exception.UserDoesNotExistException;
import com.cms.clinic.jwt.JwtRequestFilter;
import com.cms.clinic.repositories.PatientRepository;
import com.cms.clinic.repositories.UserRepository;
import com.cms.clinic.service.AuthService;
import com.cms.clinic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public AuthenticationResponse login(AuthenticationRequestDto authenticationRequestDto) {
        String email = authenticationRequestDto.getEmail();
        String password = authenticationRequestDto.getPassword();

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            if (auth.isAuthenticated()) {
                if (userDetailsService.getUserDetail().getEnabled().equalsIgnoreCase("true")) {
                    User patient = getUserByEmail(email);
                    String jwtToken = jwtUtil.generateToken(email,patient.getRole().name());
                    return new AuthenticationResponse(patient, jwtToken);
                } else {
                    throw new AccountNotActivatedException();
                }
            }
        } catch (DisabledException e) {
            e.printStackTrace();
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        return new AuthenticationResponse(null, "Bad credentials");
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserDoesNotExistException());
    }
}
