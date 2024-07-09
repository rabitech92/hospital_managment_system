package com.spring.health.practice.jasperrReport;

import com.spring.health.model.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee extends BaseClass {

    private String name;
    private String designation;
    private String doj;
    private double salary;

}
