package com.example.thesisbackend.controller.thesis;

import com.example.thesisbackend.service.thesis.ManageThesisService;
import com.example.thesisbackend.service.thesis.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/thesis")
public class ManageThesisController {
    @Autowired
    private ManageThesisService manageThesisService;

    @PostMapping("/pass_by_teacher")
    public Map<String,String> passThesisByTeacher(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return manageThesisService.passThesisByTeacher(thesis_id);
    }

    @PostMapping("/refuse_by_teacher")
    public Map<String,String> refuseThesisByTeacher(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return manageThesisService.refuseThesisByTeacher(thesis_id);
    }

    @PostMapping("/pass_by_dean")
    public Map<String,String> passThesisByDean(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return manageThesisService.passThesisByDean(thesis_id);
    }

    @PostMapping("/refuse_by_dean")
    public Map<String,String> refuseThesisByDean(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return manageThesisService.refuseThesisByDean(thesis_id);
    }
}
