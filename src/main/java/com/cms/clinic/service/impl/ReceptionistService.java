package com.cms.clinic.service.impl;

import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.repositories.AppointmentRepository;
import com.cms.clinic.repositories.ReceptionistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final AppointmentRepository appointmentRepository;

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
        return receptionistRepository.save(receptionist);
    }

    //Get Receptionist By ID
    public Optional<Receptionist> getReceptionistById(Long id){
        return this.receptionistRepository.findById(id);
    }

    //Update Receptionist
    public Optional<Receptionist> updateReceptionist(Receptionist receptionist){
        Optional<Receptionist> oldRecep = null;

        Optional<Optional<Receptionist>> optionalReceptionist = Optional.ofNullable(receptionistRepository.findById(receptionist.getUserId()));

        return oldRecep;
    }

    //Update Appointment
    public Optional<Appointment> updateAppointment(Appointment appointment){
        Optional<Appointment> oldApp = null;

        Optional<Optional<Appointment>> optionalReceptionist = Optional.ofNullable(appointmentRepository.findById(appointment.getApp_id()));

        return oldApp;
    }

    //Delete Receptionist
    public String deleteReceptionistById(Long id){
        receptionistRepository.deleteById(id);
        return "Receptionist Deleted";
    }
}
