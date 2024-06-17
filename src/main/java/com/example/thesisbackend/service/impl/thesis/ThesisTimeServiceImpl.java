package com.example.thesisbackend.service.impl.thesis;

import com.example.thesisbackend.mapper.ThesisMapper;
import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.thesis.ThesisTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThesisTimeServiceImpl implements ThesisTimeService {
    @Autowired
    private ThesisMapper thesisMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> updateAllStart(LocalDateTime start) {
        Map<String,String> map = new HashMap<>();
        List<Thesis> thesisList=thesisMapper.selectAll();
        List<User> userList=userMapper.selectByAuthority(2);
        for(User user:userList){
            user.setStart(start);
            userMapper.updateById(user);
        }
        for(Thesis thesis:thesisList){
            thesis.setStart(start);
            thesisMapper.updateById(thesis);
        }
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Map<String, String> updateAllEnd(LocalDateTime end) {
        Map<String,String> map = new HashMap<>();
        List<User> userList=userMapper.selectByAuthority(2);
        List<Thesis> thesisList=thesisMapper.selectAll();
        for(User user:userList){
            user.setEnd(end);
            userMapper.updateById(user);
        }
        for(Thesis thesis:thesisList){
            thesis.setEnd(end);
            thesisMapper.updateById(thesis);
        }
        map.put("error_message", "success");
        return map;
    }
}
