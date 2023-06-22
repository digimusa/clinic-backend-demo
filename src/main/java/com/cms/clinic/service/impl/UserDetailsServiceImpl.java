package com.cms.clinic.service.impl;

import com.cms.clinic.exception.UserDoesNotExistException;
import com.cms.clinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatientRepository patientRepository;
    private com.cms.clinic.entity.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = patientRepository.findByEmail(username).get();
        userDetail = patientRepository.findByEmail(username).get();

        if (!Objects.isNull(userDetail)) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public com.cms.clinic.entity.User getUserDetail() {
        return userDetail;
    }

    public Optional<User> getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
