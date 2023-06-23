package com.cms.clinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String email;
    @JsonIgnore
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String enabled;
    @Column(nullable = false, unique = true)
    private String activationToken;

}
