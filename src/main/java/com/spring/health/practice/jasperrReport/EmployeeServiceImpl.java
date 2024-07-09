package com.spring.health.practice.jasperrReport;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmp() {
      List<Employee> employeeList =  employeeRepository.findAll();
      return employeeList;
    }

    @Override
    public Employee empSave(Employee employee) {
        return employeeRepository.save(employee);
    }
}
