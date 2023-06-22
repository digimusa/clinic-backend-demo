package com.cms.clinic.service.impl;

import com.cms.clinic.entity.User;
import com.cms.clinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    public User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username).get();
        User user = patientRepository.findByEmail(username).get();

        if (!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }else {
            throw new UsernameNotFoundException("The user does not exists");
        }
    }

    private Set getAuthorities(User user) {
        Set authorities = new HashSet();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });

        return authorities;
    }
}
