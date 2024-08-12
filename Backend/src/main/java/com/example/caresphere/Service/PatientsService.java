package com.example.caresphere.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caresphere.Model.Patients;
import com.example.caresphere.Repository.PatientsRepository;

@Service
public class PatientsService {

    @Autowired
    private final PatientsRepository patientsRepository;

    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }
    
    public Patients addPatients(Patients patients) {
        return patientsRepository.save(patients);
    }

    public List<Patients> getAllPatients() {
        return patientsRepository.findAll();
    }

    public List<Patients> getPatientsByDoctorAndStatus(String doctor, String status) {
        return patientsRepository.findByDoctorAndStatus(doctor, status);
    }

    public void updatePatientStatus(Long id, String status) {
        patientsRepository.updatePatientStatus(id, status);
    }

    public Patients updatePatientDetails(Long id, Patients updatedPatient) {
        Optional<Patients> optionalPatient = patientsRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patients patient = optionalPatient.get();
            patient.setName(updatedPatient.getName());
            patient.setEmail(updatedPatient.getEmail());
            patient.setPhone(updatedPatient.getPhone());
            patient.setIllness(updatedPatient.getIllness());
            patient.setTreatment(updatedPatient.getTreatment());
            return patientsRepository.save(patient);
        } else {
            return null;
        }
    }

    public void deletePatientByAppointmentId(String appointmentid) {
        patientsRepository.deleteByAppointmentid(appointmentid);
    }
}
