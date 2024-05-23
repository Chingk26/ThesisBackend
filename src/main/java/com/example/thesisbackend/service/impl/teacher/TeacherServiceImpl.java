package com.example.thesisbackend.service.impl.teacher;

import com.example.thesisbackend.mapper.ApplicationMapper;
import com.example.thesisbackend.mapper.TeacherMapper;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Map<String, Object> getAllStudentTeacher(Integer teacherId) {
        Map<String,Object> map = new HashMap<>();
        List<User> userList=teacherMapper.selectStuByT(teacherId);
        map.put("error_message", "success");
        map.put("student_list", userList);
        return map;
    }

    @Override
    public Map<String, Object> getAllTeacher() {
        Map<String,Object> map = new HashMap<>();
        List<User> userList=teacherMapper.selectAllTeacher();
        map.put("error_message", "success");
        map.put("teacher_list", userList);
        return map;
    }
}
