package com.example.caresphere.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caresphere.Model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByPatientusername(String username);
}

