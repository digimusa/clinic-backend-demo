package com.cms.clinic.controller;


import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Admin;
import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.repositories.ReceptionistRepository;
import com.cms.clinic.service.impl.AdminService;
import com.cms.clinic.service.impl.PatientServiceImpl;
import com.cms.clinic.service.impl.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

  private final AdminService adminService;
  private final PatientServiceImpl patientServiceImp;
  private final ReceptionistService receptionistService;


  @GetMapping("/viewAppointments")
    public List<Appointment> getAllAppointments() {
    return adminService.viewAppointments();
  }

  @GetMapping("/viewAdmins")
  public List<Admin> getAllAdmin(){
    return adminService.viewAdmins();
  }

  @GetMapping("/viewPatients")
  public List<Patient> getAllPatient() {
    return adminService.viewPatients();
  }

    @GetMapping("/viewReceptionists")
    public List<Receptionist> getAllReceptionist()
  {
      return adminService.viewReceptionists();
  }


  @PostMapping("/addNewReceptionist")
  public ResponseEntity<Receptionist> register(@RequestBody RegisterRequestDto request) {
    return new ResponseEntity<>(adminService.addNewReceptionist(request), HttpStatus.CREATED);
  }


//  @PostMapping("/addNewDoctor")
//  public ResponseEntity<Doctor> register(@RequestBody RegisterRequestDto request) {
//    return new ResponseEntity<>(adminService.addNewDoctor(request), HttpStatus.CREATED);
//  }

}
