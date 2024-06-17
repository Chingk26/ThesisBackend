package com.example.thesisbackend.service.impl.thesis;

import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.mapper.UserMapper;
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
    @Autowired
    private UserMapper userMapper;

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
        thesis.setStart(userMapper.selectByAuthority(2).get(0).getStart().plusMonths(5));
        thesis.setEnd(userMapper.selectByAuthority(2).get(0).getEnd().plusMonths(5));
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> reactThesisByTeacher(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setTeacherPass(3);
        thesis.setEnd(userMapper.selectByAuthority(2).get(0).getEnd().plusDays(7));
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> passThesisByDean(Integer thesisId) {
        Map<String,String> map = new HashMap<>();
        if(thesisMapper.selectById(thesisId)==null) {
            map.put("error_message","没有这个论文");
            return map;
        }
        Thesis thesis=thesisMapper.selectById(thesisId);
        if(thesis.getResult()>=0.3){
            map.put("error_message","论文查重率过高，不能通过");
            return map;
        }
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
        if(thesis.getResult()>=0.3&&thesis.getResult()<0.5){
            thesis.setStart(userMapper.selectByAuthority(2).get(0).getStart().plusMonths(5));
            thesis.setEnd(userMapper.selectByAuthority(2).get(0).getEnd().plusMonths(5));
        }
        if(thesis.getResult()>=0.5){
            thesis.setStart(userMapper.selectByAuthority(2).get(0).getStart().plusMonths(6));
            thesis.setEnd(userMapper.selectByAuthority(2).get(0).getEnd().plusMonths(6));
        }
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> updateProgress(Integer thesisId,String progress) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setProgress(progress);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> updateQuality(Integer thesisId, String quality) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setProgress(quality);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> updateOpinion(Integer thesisId, String opinion) {
        Map<String,String> map = new HashMap<>();
        Thesis thesis=thesisMapper.selectById(thesisId);
        thesis.setProgress(opinion);
        thesisMapper.updateById(thesis);
        map.put("error_message", "success");
        return map;
    }


}
