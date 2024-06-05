package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.Application;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ThesisMapper extends BaseMapper<Thesis> {
    @Select("select * from thesis where teacher_id=#{teacherId};")
    List<Thesis> selectByTeacher(Integer teacherId);

    @Select("select * from thesis where student_id=#{studentId};")
    List<Thesis> selectByStudent(Integer studentId);

    @Select("select * from thesis where teacher_pass=#{teacherPass};")
    List<Thesis> selectByTeacherPass(Integer teacherPass);
}
