package com.cms.clinic.service.impl;

import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.entity.Role;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.AppointmentRepository;
import com.cms.clinic.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    //View all appointments
    public List<Appointment> viewAppointments(){
        return appointmentRepository.findAll();
    }

    //View all receptionists
    public List<Receptionist> viewReceptionists(){
        return receptionistRepository.findAll();
    }

    //Add new receptionist
    public Receptionist addReceptionist(Receptionist receptionist){
        log.info("Inside Receptionist new receptionist method {} ", receptionist);

        Receptionist reception = new Receptionist();

        reception.setFirstName(receptionist.getFirstName());
        reception.setLastName(receptionist.getLastName());
        reception.setEmail(receptionist.getEmail());
        reception.setRole(Role.RECEPTION);
        reception.setPassword(passwordEncoder.encode(receptionist.getPassword()));
        reception.setEnabled("false");

        String activationToken = generateActivationToken();
        reception.setActivationToken(activationToken);

        try{
            return receptionistRepository.save(reception);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }


    }

    //Get Receptionist By ID
    public Optional<Receptionist> getReceptionistById(Long id){
        return this.receptionistRepository.findById(id);
    }

    //Update Receptionist
    public Receptionist updateReceptionist(Receptionist receptionist){
        return receptionistRepository.save(receptionist);
    }

    //Update Appointment
    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    //Delete Receptionist
    public String deleteReceptionistById(Long id){
        receptionistRepository.deleteById(id);
        return "Receptionist Deleted";
    }

    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }
}
