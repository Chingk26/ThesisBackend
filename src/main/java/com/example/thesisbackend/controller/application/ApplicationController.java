package com.example.thesisbackend.controller.application;

import com.example.thesisbackend.service.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/application/add")
    public Map<String,String> addApplication(@RequestParam Map<String,String> map){
        Integer student_id = Integer.valueOf(map.get("student_id"));
        Integer teacher_id = Integer.valueOf(map.get("teacher_id"));
        String content=map.get("content");
        return applicationService.addApplication(student_id,teacher_id,content);
    }
}
