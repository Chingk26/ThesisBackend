package com.example.thesisbackend.service.impl.thesis;

import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.service.thesis.GetThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetThesisServiceImpl implements GetThesisService {
    @Autowired
    private ThesisMapper thesisMapper;

    @Override
    public Map<String, Object> GetByStudentThesis(Integer studentId) {
        Map<String,Object> map = new HashMap<>();
        List<Thesis> thesisList=thesisMapper.selectByStudent(studentId);
        map.put("error_message", "success");
        map.put("thesis_list", thesisList);
        return map;
    }

    @Override
    public Map<String, Object> GetByTeacherThesis(Integer teacherId) {
        Map<String,Object> map = new HashMap<>();
        List<Thesis> thesisList=thesisMapper.selectByTeacher(teacherId);
        map.put("error_message", "success");
        map.put("thesis_list", thesisList);
        return map;
    }

    @Override
    public Map<String, Object> GetByTeacherPassThesis() {
        Map<String,Object> map = new HashMap<>();
        List<Thesis> thesisList=thesisMapper.selectByTeacherPass(1);
        map.put("error_message", "success");
        map.put("thesis_list", thesisList);
        return map;
    }
}
