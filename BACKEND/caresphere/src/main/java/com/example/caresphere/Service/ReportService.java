package com.example.caresphere.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caresphere.Model.Report;
import com.example.caresphere.Repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElse(null);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getReportsByUsername(String username) {
        return reportRepository.findByPatientusername(username);
    }
    
}
