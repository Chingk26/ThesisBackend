package com.example.thesisbackend.service.impl.thesis;

import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.service.thesis.ManageThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManageThesisServiceImpl implements ManageThesisService {
    @Autowired
    private ThesisMapper thesisMapper;
    @Override
    public Map<String, String> passThesisByTeacher(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        if(thesisMapper.selectById(thesisId)==null) {
            map.put("error_message","没有这个申请");
            return map;
        }
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setTeacherPass(1);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> refuseThesisByTeacher(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setTeacherPass(2);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> passThesisByDean(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        if(thesisMapper.selectById(thesisId)==null) {
            map.put("error_message","没有这个申请");
            return map;
        }
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setDeanPass(1);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> refuseThesisByDean(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setDeanPass(2);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }
}
