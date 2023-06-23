package com.cms.clinic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "patients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {

    private String bloodGroup;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
