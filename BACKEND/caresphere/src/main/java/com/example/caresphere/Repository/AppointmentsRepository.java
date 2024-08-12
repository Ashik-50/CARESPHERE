package com.example.caresphere.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.caresphere.Model.Appointments;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> findByUsernameAndStatus(String username, String status);
    List<Appointments> findByDoctorusernameAndStatus(String doctorusername, String status);
    List<Appointments> findByUsernameAndStatusIn(String username, List<String> statuses);
}
