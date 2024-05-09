package com.example.thesisbackend.service.impl.user;

import com.example.thesisbackend.mapper.UserMapper;
import com.example.thesisbackend.pojo.User;
import com.example.thesisbackend.service.impl.utils.UserDetailsImpl;
import com.example.thesisbackend.service.user.account.LoginService;
import com.example.thesisbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);


        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//登录失败自动处理

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        Map<String,String> map=new HashMap<>();
        User user = loginUser.getUser();
        if (user.getAuthority() == 0) {
            map.put("error_message", "用户已被禁用");
        }
        String jwt = JwtUtil.createJWT(user.getUid().toString());
        map.put("error_message","success");
        map.put("token",jwt);
        map.put("uid", user.getUid().toString());
        map.put("username", user.getUsername());
        map.put("authority", user.getAuthority().toString());
        return map;
    }

}
