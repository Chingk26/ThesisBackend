package com.example.thesisbackend.service.impl.application;

import com.example.thesisbackend.mapper.ApplicationMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.application.GetApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetApplicationServiceImpl implements GetApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Override
    public Map<String, Object> GetByStudentApplication(Integer studentId) {
        Map<String,Object> map = new HashMap<>();
        List<Application> applicationList=applicationMapper.selectByStudent(studentId);
        map.put("error_message", "success");
        map.put("application_list", applicationList);
        return map;
    }

    @Override
    public Map<String, Object> GetByTeacherApplication(Integer teacherId) {
        Map<String,Object> map = new HashMap<>();
        List<Application> applicationList=applicationMapper.selectByTeacher(teacherId);
        map.put("error_message", "success");
        map.put("application_list", applicationList);
        return map;
    }
}
