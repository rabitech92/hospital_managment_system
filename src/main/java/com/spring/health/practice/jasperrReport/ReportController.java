package com.spring.health.practice.jasperrReport;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;

@RestController
@RequestMapping
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report")
    public void generateReport(HttpServletResponse response) throws Exception {
    byte[] bytes = reportService.generateReport();
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "inline; filename = employeeReport.pdf");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
    }
}
