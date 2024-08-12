package com.example.caresphere.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.caresphere.Model.Report;
import com.example.caresphere.Service.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/doctor")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/add-report")
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }

    @GetMapping("/reports/{username}")
    public List<Report> getReportsByUsername(@PathVariable String username) {
        return reportService.getReportsByUsername(username);
    }

}
