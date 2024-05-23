package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;

@Mapper
public interface TeacherMapper extends BaseMapper<User> {

    @Select("select count(*) from user where teacher_id=#{teacherId}")
    int countStuByT(Integer teacherId);

    @Select("select * from user where teacher_id=#{teacherId}")
    List<User> selectStuByT(Integer teacherId);

    @Select("select * from user where authority=1")
    List<User> selectAllTeacher();
}
