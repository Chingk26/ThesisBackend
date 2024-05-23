package com.example.thesisbackend.controller.student;

import com.example.thesisbackend.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/get_all")
    public Map<String,Object> getAllStudent(){
        return studentService.getAllStudent();
    }
}
