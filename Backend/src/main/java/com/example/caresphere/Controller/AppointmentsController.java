package com.example.caresphere.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.caresphere.Model.Appointments;
import com.example.caresphere.Service.AppointmentsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentsController {

    @Autowired
    private AppointmentsService appointmentService;

    @PostMapping("/create")
    public Appointments createAppointment(@RequestBody Appointments appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable Long id) {
        Optional<Appointments> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user")
    public ResponseEntity<List<Appointments>> getAppointmentsByUsernameAndStatuses(
        @RequestParam("username") String username,
        @RequestParam("statuses") List<String> statuses) {
        
        List<Appointments> appointments = appointmentService.getAppointmentsByUsernameAndStatuses(username, statuses);
        return ResponseEntity.ok(appointments);
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<Void> markAsCompleted(@PathVariable Long id) {
        appointmentService.markAsCompleted(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/accepted")
    public ResponseEntity<Void> markAsAccepted(@PathVariable Long id) {
        appointmentService.markAsAccepted(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Appointments>> getAppointmentsByDoctorusernameAndStatus(
        @RequestParam("doctorusername") String doctorusername,
        @RequestParam("status") String status) {
        
        List<Appointments> appointments = appointmentService.getAppointmentsByDoctorusernameAndStatus(doctorusername, status);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/count")
    public long getAppointmentCount() {
        return appointmentService.countAppointments();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
