package com.cms.clinic.controller;


import com.cms.clinic.entity.Admin;
import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.service.impl.AdminService;
import com.cms.clinic.service.impl.PatientServiceImpl;
import com.cms.clinic.service.impl.ReceptionistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

  private final AdminService adminService;
  private final PatientServiceImpl patientServiceImp;


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

}
