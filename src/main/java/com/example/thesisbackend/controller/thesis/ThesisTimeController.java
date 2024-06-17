package com.example.thesisbackend.controller.thesis;

import com.example.thesisbackend.service.thesis.ManageThesisService;
import com.example.thesisbackend.service.thesis.ThesisTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/thesis")
public class ThesisTimeController {
    @Autowired
    private ThesisTimeService thesisTimeService;
    @PostMapping("/update_all_start")
    public Map<String,String> updateAllStart(@RequestParam Map<String,String> map){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(map.get("start"),df);
        return thesisTimeService.updateAllStart(start);
    }

    @PostMapping("/update_all_end")
    public Map<String,String> updateAllEnd(@RequestParam Map<String,String> map){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime end = LocalDateTime.parse(map.get("end"),df);
        return thesisTimeService.updateAllEnd(end);
    }
}
