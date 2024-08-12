package com.example.caresphere.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.caresphere.Model.Patients;

import jakarta.transaction.Transactional;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Patients p SET p.status = :status WHERE p.id = :id")
    void updatePatientStatus(@Param("id") Long id, @Param("status") String status);

    List<Patients> findByDoctorAndStatus(String doctor, String status);

    void deleteByAppointmentid(String appointmentid);
}
