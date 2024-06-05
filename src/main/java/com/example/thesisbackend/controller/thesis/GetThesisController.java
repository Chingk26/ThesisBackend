package com.example.thesisbackend.controller.thesis;

import com.example.thesisbackend.service.application.GetApplicationService;
import com.example.thesisbackend.service.thesis.GetThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/thesis")
public class GetThesisController {
    @Autowired
    private GetThesisService getThesisService;
    @GetMapping("/get_by_student")
    public Map<String,Object> GetByStudentThesis(@RequestParam Map<String,String> map){
        Integer student_id = Integer.valueOf(map.get("student_id"));
        return getThesisService.GetByStudentThesis(student_id);
    }
    @GetMapping("/get_by_teacher")
    public Map<String,Object> GetByTeacherThesis(@RequestParam Map<String,String> map){
        Integer teacher_id = Integer.valueOf(map.get("teacher_id"));
        return getThesisService.GetByTeacherThesis(teacher_id);
    }

    @GetMapping("/get_by_teacher_pass")
    public Map<String,Object> GetByTeacherPassThesis(@RequestParam Map<String,String> map){
        return getThesisService.GetByTeacherPassThesis();
    }
}
