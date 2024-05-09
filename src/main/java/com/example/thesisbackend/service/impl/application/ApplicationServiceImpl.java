package com.example.thesisbackend.service.impl.application;

import com.example.thesisbackend.mapper.ApplicationMapper;
import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.service.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> addApplication(Integer studentId, Integer teacherId, String content) {
        Map<String,String> map = new HashMap<>();
        if(studentId == null) {
            map.put("error_message","学生不能为空");
            return map;
        }
        if(userMapper.selectById(studentId)==null) {
            map.put("error_message","没有这个学生");
            return map;
        }
        if(userMapper.selectById(studentId).getAuthority()!=0) {
            map.put("error_message","这个用户不是学生");
            return map;
        }
        if(teacherId == null) {
            map.put("error_message","导师不能为空");
            return map;
        }
        if(userMapper.selectById(teacherId)==null) {
            map.put("error_message","没有这个导师");
            return map;
        }
        if(userMapper.selectById(teacherId).getAuthority()!=1) {
            map.put("error_message","这个用户不是导师");
            return map;
        }
        if(applicationMapper.selectBySP(studentId,0)!=null||applicationMapper.selectBySP(studentId,1)!=null){
            map.put("error_message","您已提交过申请,请勿重复提交");
            return map;
        }
        Application application=new Application();
        application.setStudent_id(studentId);
        application.setTeacher_id(teacherId);
        application.setContent(content);
        applicationMapper.insert(application);
        map.put("error_message", "success");
        map.put("cid", String.valueOf(application.getApplication_id()));
        return map;
    }
}
