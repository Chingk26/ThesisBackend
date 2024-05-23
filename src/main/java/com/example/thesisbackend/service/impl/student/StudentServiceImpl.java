package com.example.thesisbackend.service.impl.student;

import com.example.thesisbackend.mapper.StudentMapper;
import com.example.thesisbackend.mapper.TeacherMapper;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, Object> getAllStudent() {
        Map<String,Object> map = new HashMap<>();
        List<User> userList=studentMapper.selectAllStudent();
        map.put("error_message", "success");
        map.put("student_list", userList);
        return map;
    }
}
