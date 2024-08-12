package com.example.caresphere.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String patientusername;
    private String email;
    private String phone;
    private String illness;
    private String doctor;
    private String status;
    private String treatment;
    private String appointmentid;


    public Patients(){}


    public Patients(long id, String name, String patientusername, String email, String phone, String illness,
            String doctor, String status, String treatment, String appointmentid) {
        this.id = id;
        this.name = name;
        this.patientusername = patientusername;
        this.email = email;
        this.phone = phone;
        this.illness = illness;
        this.doctor = doctor;
        this.status = status;
        this.treatment = treatment;
        this.appointmentid = appointmentid;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPatientusername() {
        return patientusername;
    }


    public void setPatientusername(String patientusername) {
        this.patientusername = patientusername;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getIllness() {
        return illness;
    }


    public void setIllness(String illness) {
        this.illness = illness;
    }


    public String getDoctor() {
        return doctor;
    }


    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getTreatment() {
        return treatment;
    }


    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public String getAppointmentid() {
        return appointmentid;
    }


    public void setAppointmentid(String appointmentid) {
        this.appointmentid = appointmentid;
    }

}