package com.spring.swagger2.controller;

import com.spring.swagger2.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    List<Student> sList = new ArrayList<>();

    @GetMapping()
    public List<Student> getAllStudents() {
        return sList;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        for (Student student : sList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        sList.add(student);
        return student;
    }
}
