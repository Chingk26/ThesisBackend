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

    @PostMapping("/react_by_teacher")
    public Map<String,String> reactThesisByTeacher(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        return manageThesisService.reactThesisByTeacher(thesis_id);
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

    @PostMapping("/update_progress")
    public Map<String,String> updateProgress(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        String progress=map.get("progress");
        return manageThesisService.updateProgress(thesis_id,progress);
    }

    @PostMapping("/update_quality")
    public Map<String,String> updateQuality(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        String quality=map.get("quality");
        return manageThesisService.updateQuality(thesis_id,quality);
    }

    @PostMapping("/update_opinion")
    public Map<String,String> updateOpinion(@RequestParam Map<String,String> map){
        Integer thesis_id = Integer.valueOf(map.get("thesis_id"));
        String opinion=map.get("opinion");
        return manageThesisService.updateOpinion(thesis_id,opinion);
    }
}
