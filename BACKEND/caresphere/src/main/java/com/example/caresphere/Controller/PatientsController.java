package com.example.caresphere.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.caresphere.Model.Patients;
import com.example.caresphere.Service.PatientsService;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/doctor")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @PostMapping("/create")
    public Patients createPatients(@RequestBody Patients patients) {
        return patientsService.addPatients(patients);
    }

    @GetMapping("/ongoing")
    public List<Patients> getOngoingPatients(@RequestParam String doctor) {
        return patientsService.getPatientsByDoctorAndStatus(doctor, "ongoing");
    }

    @GetMapping("/completed")
    public List<Patients> getCompletedPatients(@RequestParam String doctor) {
        return patientsService.getPatientsByDoctorAndStatus(doctor, "completed");
    }

    @PutMapping("/mark-completed/{id}")
    public ResponseEntity<Void> markPatientAsCompleted(@PathVariable Long id) {
        patientsService.updatePatientStatus(id, "completed");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit-patient/{id}")
    public ResponseEntity<Patients> editPatientDetails(@PathVariable Long id, @RequestBody Patients updatedPatient) {
        Patients patient = patientsService.updatePatientDetails(id, updatedPatient);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/deleteByAppointmentId/{appointmentid}")
    public ResponseEntity<String> deletePatientByAppointmentId(@PathVariable String appointmentid) {
        try {
            patientsService.deletePatientByAppointmentId(appointmentid);
            return ResponseEntity.ok("Patient with appointment ID " + appointmentid + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete patient with appointment ID " + appointmentid);
        }
    }
}
