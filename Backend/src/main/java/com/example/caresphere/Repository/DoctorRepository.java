package com.example.caresphere.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caresphere.Model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
