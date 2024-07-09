package com.spring.health.practice.jasperrReport;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ReportService {

    private final EmployeeService employeeService;

    public ReportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public byte[] generateReport() throws Exception{
        List<Employee> employees = employeeService.getAllEmp();
        InputStream inputStream = getClass().getResourceAsStream("/report/employeeReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,dataSource);
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        return bytes;
    }


}
