package com.cms.clinic.controller;


import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.Admin;
import com.cms.clinic.entity.Appointment;
import com.cms.clinic.entity.Patient;
import com.cms.clinic.entity.Receptionist;
import com.cms.clinic.entity.Doctor;
import com.cms.clinic.repositories.ReceptionistRepository;
import com.cms.clinic.service.impl.AdminService;
import com.cms.clinic.service.impl.DoctorService;
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
  private final DoctorService doctorService;

//Viewing
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

  @GetMapping("/viewDoctor")
  public List<Doctor> getAllDoctors()
  {
    return adminService.viewDoctors();
  }


  @PostMapping("/addNewReceptionist")
  public ResponseEntity<Receptionist> addReceptionist(@RequestBody RegisterRequestDto request) {
    return new ResponseEntity<>(adminService.addNewReceptionist(request), HttpStatus.CREATED);
  }


  @PostMapping("/addNewDoctor")
  public ResponseEntity<Doctor> addDoctor(@RequestBody RegisterRequestDto request) {
    return new ResponseEntity<>(adminService.addNewDoctor(request), HttpStatus.CREATED);
  }

  @PostMapping("/addNewAdmin")
  public ResponseEntity<Doctor> addAdmin(@RequestBody RegisterRequestDto request) {
    return new ResponseEntity<>(adminService.addNewDoctor(request), HttpStatus.CREATED);
  }


  @DeleteMapping("/deleteDoctor/{id}")
  public String deleteDoctor(@PathVariable long id){
    return  adminService.deleteDoctor(id);
  }

  @DeleteMapping("/deleteReceptionist/{id}")
  public String deleteReceptionist(@PathVariable long id){
    return  adminService.deleteReceptionist(id);
  }

  @DeleteMapping("/deleteAppointment/{id}")
  public String deleteAppointment(@PathVariable long id){
    return  adminService.deleteAppointment(id);
  }

  @DeleteMapping("/deletePatient/{id}")
  public String deletePatient(@PathVariable long id){
    return  adminService.deletePatient(id);
  }
  @DeleteMapping("/deleteAdmin/{id}")
  public String deleteAdmin(@PathVariable long id){
    return  adminService.deleteAdmin(id);
  }

  @PutMapping("/updateDoctor")
  public Doctor updateDoctor(@RequestBody Doctor doctor) {
    return adminService.updateDoctor(doctor);
  }
  @PutMapping("/updatePatient")
  public Patient updatePatient(@RequestBody Patient patient) {
    return adminService.updatePatient(patient);
  }
  @PutMapping("/updateAdmin")
  public Admin updateAdmin(@RequestBody Admin admin) {
    return adminService.updateAdmin(admin);
  }
  @PutMapping("/updateAppointment")
  public Appointment updateAppointment(@RequestBody Appointment appointment) {
    return adminService.updateAppointment(appointment);
  }
  @PutMapping("/updateReceptionist")
  public Receptionist updateReceptionist(@RequestBody Receptionist receptionist) {
    return adminService.updateReceptionist(receptionist);
  }

}
