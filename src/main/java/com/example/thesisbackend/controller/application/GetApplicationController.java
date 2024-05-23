package com.example.thesisbackend.controller.application;

import com.example.thesisbackend.service.application.GetApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GetApplicationController {
    @Autowired
    private GetApplicationService getApplicationService;

    @GetMapping("/application/get_by_student")
    public Map<String,Object> GetByStudentApplication(@RequestParam Map<String,String> map){
        Integer student_id = Integer.valueOf(map.get("student_id"));
        return getApplicationService.GetByStudentApplication(student_id);
    }
    @GetMapping("/application/get_by_teacher")
    public Map<String,Object> GetByTeacherApplication(@RequestParam Map<String,String> map){
        Integer teacher_id = Integer.valueOf(map.get("teacher_id"));
        return getApplicationService.GetByTeacherApplication(teacher_id);
    }
}
