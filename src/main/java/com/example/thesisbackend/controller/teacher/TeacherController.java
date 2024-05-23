package com.example.thesisbackend.controller.teacher;

import com.example.thesisbackend.service.application.ApplicationService;
import com.example.thesisbackend.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher/get_all")
    public Map<String,Object> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/teacher/get_all_student")
    public Map<String,Object> getAllStudentTeacher(@RequestParam Map<String,String> map){
        Integer teacher_id = Integer.valueOf(map.get("teacher_id"));
        return teacherService.getAllStudentTeacher(teacher_id);
    }

}
