package com.example.thesisbackend.controller.application;

import com.example.thesisbackend.service.application.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return applicationService.addApplication(student_id,teacher_id);
    }

    @PostMapping("/application/pass")
    public Map<String,String> passApplication(@RequestParam Map<String,String> map){
        Integer application_id = Integer.valueOf(map.get("application_id"));
        return applicationService.passApplication(application_id);
    }

    @PostMapping("/application/refuse")
    public Map<String,String> refuseApplication(@RequestParam Map<String,String> map){
        Integer application_id = Integer.valueOf(map.get("application_id"));
        return applicationService.refuseApplication(application_id);
    }

    @PostMapping("/application/uploadPdf")
    public Map<String, String> uploadPdf(@RequestParam("application_id") Integer application_id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return applicationService.uploadPdf(application_id,file,request);
    }

    @GetMapping(
            value = "/application/getPDF",
            produces = {"application/octet-stream;charset=UTF-8"}
    )
    public void readPDF(@RequestParam Map<String,String> map, HttpServletResponse response) throws IOException {
        Integer application_id=Integer.valueOf(map.get("application_id"));
        applicationService.read(application_id,response);
    }

}
