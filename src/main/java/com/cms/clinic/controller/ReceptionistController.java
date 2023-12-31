package com.cms.clinic.controller;

import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.service.impl.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/receptionist")
@CrossOrigin(origins = "http://localhost:4200")
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    //Get all appointments
    @GetMapping("/viewappointments")
    public List<Appointment> getAllAppointments() {
        return receptionistService.viewAppointments();
    }

    //Add/create new receptionist
    @PostMapping("/addreceptionist")
    public ResponseEntity<Receptionist> addReceptionist(@RequestBody RegisterRequestDto receptionist) {
        return new ResponseEntity<>(receptionistService.addNewReceptionist(receptionist), HttpStatus.CREATED);
    }

    //get receptionist by id
    @GetMapping("/getreceptionist/{email}")
    public Optional<Receptionist> getReceptionistById(@PathVariable String email) {
        return this.receptionistService.getReceptionistById(email);
    }

    //update receptionist details
    @PutMapping("/updatereceptionist")
    public Receptionist updateReceptionist(@RequestBody Receptionist receptionist) {
        return receptionistService.updateReceptionist(receptionist);
    }

    //update appointment
    @PutMapping("/updateappointment")
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return receptionistService.updateAppointment(appointment);
    }

    //delete receptionist
    @DeleteMapping("deletereceptionist/{id}")
    public String deleteReceptionist(@PathVariable Long id) {
        return receptionistService.deleteReceptionistById(id);
    }
}
