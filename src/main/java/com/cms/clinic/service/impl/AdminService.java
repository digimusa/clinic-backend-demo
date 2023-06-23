package com.cms.clinic.service.impl;


import com.cms.clinic.dto.RegisterRequestDto;
import com.cms.clinic.entity.*;
import com.cms.clinic.exception.EmailAlreadyTakenException;
import com.cms.clinic.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ReceptionistRepository receptionistRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;




//  View
    public List<Admin> viewAdmins(){

        return adminRepository.findAll();
    }

    public List<Appointment> viewAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Patient> viewPatients(){
        return patientRepository.findAll();
    }

    public List<Receptionist> viewReceptionists(){
        return receptionistRepository.findAll();
    }


    public List<Doctor> viewDoctors(){
        return doctorRepository.findAll();
    }

//    Registration

    public Receptionist addNewReceptionist(RegisterRequestDto registerRequest) {
        log.info("Inside Register new receptionist method {} ", registerRequest);

        Receptionist receptionist = new Receptionist();

        receptionist.setFirstName(registerRequest.getFirstName());
        receptionist.setLastName(registerRequest.getLastName());
        receptionist.setEmail(registerRequest.getEmail());
        receptionist.setRole(Role.RECEPTION);
        receptionist.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        receptionist.setEnabled("false");

        String activationToken = generateActivationToken();
        receptionist.setActivationToken(activationToken);


        try {
            return receptionistRepository.save(receptionist);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }
    }



    public Doctor addNewDoctor(RegisterRequestDto registerRequest) {
        log.info("Inside Register new doctor method {} ", registerRequest);

        Doctor doctor = new Doctor();

        doctor.setFirstName(registerRequest.getFirstName());
        doctor.setLastName(registerRequest.getLastName());
        doctor.setEmail(registerRequest.getEmail());
        doctor.setRole(Role.DOCTOR);
        doctor.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        doctor.setEnabled("false");

        String activationToken = generateActivationToken();
        doctor.setActivationToken(activationToken);


        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }
    }

    public Admin addNewAdmin(RegisterRequestDto registerRequest) {
        log.info("Inside Register new admin method {} ", registerRequest);

        Admin admin = new Admin();

        admin.setFirstName(registerRequest.getFirstName());
        admin.setLastName(registerRequest.getLastName());
        admin.setEmail(registerRequest.getEmail());
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        admin.setEnabled("false");

        String activationToken = generateActivationToken();
        admin.setActivationToken(activationToken);


        try {
            return adminRepository.save(admin);

        } catch (Exception e) {
            throw new EmailAlreadyTakenException();
        }

    }
        //Delete by id

    public String deletePatient(long id){
        patientRepository.deleteById(id);
        return "Patient deleted";
    }

    public String deleteDoctor(long id){

       doctorRepository.deleteById(id);
        return "Doctor deleted";
    }

    public String deleteReceptionist(long id){
        receptionistRepository.deleteById(id);
        return "Receptionist deleted";
    }
    public String deleteAppointment(long id){
        appointmentRepository.deleteById(id);
        return "Appointment deleted";
    }

    public String deleteAdmin(long id){
        adminRepository.deleteById(id);
        return "Admin deleted";
    }

    //Update

    public Doctor updateDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }
    public Admin updateAdmin(Admin admin){
        return adminRepository.save(admin);
    }
    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Receptionist updateReceptionist(Receptionist receptionist){
        return receptionistRepository.save(receptionist);
    }


    private String generateActivationToken() {
        return UUID.randomUUID().toString();
    }




    }







