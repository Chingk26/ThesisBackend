package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<User> {
    @Select("select * from user where authority=0")
    List<User> selectAllStudent();
}
