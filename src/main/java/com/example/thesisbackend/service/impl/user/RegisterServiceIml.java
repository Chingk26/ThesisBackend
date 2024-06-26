package com.example.thesisbackend.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceIml implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String name, String password) {
        Map<String,String> map = new HashMap<>();
        if(name == null) {
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password == null) {
            map.put("error_message","密码不能为空");
            return map;
        }
        name = name.trim();
        if(name.isEmpty()){
            map.put("error_message","用户名不能为空");
            return map;
        }
        if(password.isEmpty()){
            map.put("error_message","密码不能为空");
            return map;
        }

        if(name.length() > 100){
            map.put("error_message","用户名长度不能大于100");
            return map;
        }

        if(password.length() > 100){
            map.put("error_message","密码长度不能大于100");
            return  map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",name);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message","用户名已存在");
            return map;
        }

        //特判完毕，开始逻辑处理
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(name);
        user.setPassword(encodedPassword);
        user.setAuthority(0);
        user.setAccess(1);
        userMapper.insert(user);
        map.put("error_message","success");
        map.put("uid", user.getUid().toString());

        return map;
    }



}
