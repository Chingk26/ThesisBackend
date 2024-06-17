package com.example.thesisbackend.controller.thesis;

import com.example.thesisbackend.service.application.ApplicationService;
import com.example.thesisbackend.service.thesis.ThesisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ThesisController {
    @Autowired
    private ThesisService thesisService;

    @PostMapping("/thesis/add")
    public Map<String,String> addThesis(@RequestParam Map<String,String> map){
        Integer student_id = Integer.valueOf(map.get("student_id"));
        Float result= Float.valueOf(map.get("result"));
        Integer version=Integer.valueOf(map.get("version"));
        return thesisService.addThesis(student_id,result,version);
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

    @PostMapping("/thesis/update")
    public Map<String,String> updateThesis(@RequestParam Map<String,String> map){
        Integer thesis_id=Integer.valueOf(map.get("thesis_id"));
        Float result= Float.valueOf(map.get("result"));
        return thesisService.updateThesis(thesis_id,result);
    }

}
