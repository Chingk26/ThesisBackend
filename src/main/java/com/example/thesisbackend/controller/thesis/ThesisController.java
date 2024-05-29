package com.example.thesisbackend.controller.thesis;

import com.example.thesisbackend.service.application.ApplicationService;
import com.example.thesisbackend.service.thesis.ThesisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;

    @PostMapping("/thesis/add")
    public Map<String,String> addThesis(@RequestParam Map<String,String> map){
        Integer student_id = Integer.valueOf(map.get("student_id"));
        Integer teacher_id = Integer.valueOf(map.get("teacher_id"));
        String result=map.get("result");
        return thesisService.addThesis(student_id,teacher_id,result);
    }

    @PostMapping("/thesis/uploadPdf")
    public Map<String, String> uploadPdf(@RequestParam("thesis_id") Integer thesis_id, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return thesisService.uploadPdf(thesis_id,file,request);
    }

    @GetMapping(
            value = "/thesis/getPDF",
            produces = {"application/octet-stream;charset=UTF-8"}
    )
    public void readPDF(@RequestParam Map<String,String> map, HttpServletResponse response) throws IOException {
        Integer thesis_id=Integer.valueOf(map.get("thesis_id"));
        thesisService.read(thesis_id,response);
    }

    @PostMapping("/thesis/pass")
    public Map<String,String> passThesisByTeacher(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return thesisService.passThesisByTeacher(thesis_id);
    }

    @PostMapping("/thesis/refuse")
    public Map<String,String> refuseThesisByTeacher(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return thesisService.refuseThesisByTeacher(thesis_id);
    }
}
