package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username=#{username}")
    List<User> selectByName(String username);

    @Select("select * from user where authority=#{authority}")
    List<User> selectByAuthority(Integer authority);
}
