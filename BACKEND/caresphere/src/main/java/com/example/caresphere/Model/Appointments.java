package com.example.caresphere.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;

@Entity
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorname;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorSpecialty;
    private String doctorHospital;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String username;
    private String doctorusername;
    private String status;
    @Lob
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String doctorImageUrl;

    public Appointments() {}

    public Appointments(Long id, String doctorname, String doctorEmail, String doctorPhone, String doctorSpecialty,
            String doctorHospital, LocalDate appointmentDate, LocalTime appointmentTime, String username,
            String doctorusername, String status, String doctorImageUrl) {
        this.id = id;
        this.doctorname = doctorname;
        this.doctorEmail = doctorEmail;
        this.doctorPhone = doctorPhone;
        this.doctorSpecialty = doctorSpecialty;
        this.doctorHospital = doctorHospital;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.username = username;
        this.doctorusername = doctorusername;
        this.status = status;
        this.doctorImageUrl = doctorImageUrl;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorSpecialty() {
        return doctorSpecialty;
    }

    public void setDoctorSpecialty(String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }

    public String getDoctorHospital() {
        return doctorHospital;
    }

    public void setDoctorHospital(String doctorHospital) {
        this.doctorHospital = doctorHospital;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctorusername() {
        return doctorusername;
    }

    public void setDoctorusername(String doctorusername) {
        this.doctorusername = doctorusername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorImageUrl() {
        return doctorImageUrl;
    }

    public void setDoctorImageUrl(String doctorImageUrl) {
        this.doctorImageUrl = doctorImageUrl;
    }
}