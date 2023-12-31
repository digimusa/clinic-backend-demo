package com.cms.clinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receptionist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receptionist extends User{
    private String username;
}
