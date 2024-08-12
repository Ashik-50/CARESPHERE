package com.example.caresphere.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String emergencyContact;
    private String address;
    private Date dateOfBirth;
    private Integer age;
    private String gender;
    private Boolean isPregnant;
    private Integer monthOfPregnancy;
    private Date expectedDeliveryDate;
    private String consultingDoctor;
    private Boolean hadDiseaseBefore;
    private String selectedDisease;
    private String diseaseConsultingDoctor;
    private String medicalHistory;
    private String username;

    public UserDetails(){}

    public UserDetails(Long id, String firstName, String lastName, String email, String phone, String emergencyContact,
            String address, Date dateOfBirth, String gender, Boolean isPregnant, Integer monthOfPregnancy,
            Date expectedDeliveryDate, String consultingDoctor, Boolean hadDiseaseBefore, String selectedDisease,
            String diseaseConsultingDoctor, String medicalHistory, String username, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.emergencyContact = emergencyContact;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.isPregnant = isPregnant;
        this.monthOfPregnancy = monthOfPregnancy;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.consultingDoctor = consultingDoctor;
        this.hadDiseaseBefore = hadDiseaseBefore;
        this.selectedDisease = selectedDisease;
        this.diseaseConsultingDoctor = diseaseConsultingDoctor;
        this.medicalHistory = medicalHistory;
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIsPregnant() {
        return isPregnant;
    }

    public void setIsPregnant(Boolean isPregnant) {
        this.isPregnant = isPregnant;
    }

    public Integer getMonthOfPregnancy() {
        return monthOfPregnancy;
    }

    public void setMonthOfPregnancy(Integer monthOfPregnancy) {
        this.monthOfPregnancy = monthOfPregnancy;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getConsultingDoctor() {
        return consultingDoctor;
    }

    public void setConsultingDoctor(String consultingDoctor) {
        this.consultingDoctor = consultingDoctor;
    }

    public Boolean getHadDiseaseBefore() {
        return hadDiseaseBefore;
    }

    public void setHadDiseaseBefore(Boolean hadDiseaseBefore) {
        this.hadDiseaseBefore = hadDiseaseBefore;
    }

    public String getSelectedDisease() {
        return selectedDisease;
    }

    public void setSelectedDisease(String selectedDisease) {
        this.selectedDisease = selectedDisease;
    }

    public String getDiseaseConsultingDoctor() {
        return diseaseConsultingDoctor;
    }

    public void setDiseaseConsultingDoctor(String diseaseConsultingDoctor) {
        this.diseaseConsultingDoctor = diseaseConsultingDoctor;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}