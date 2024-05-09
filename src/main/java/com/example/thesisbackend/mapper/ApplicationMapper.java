package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.Application;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ApplicationMapper extends BaseMapper<Application> {

    @Select("select * from application where student_id=#{studentId};")
    List<Application> selectByStudent(Integer studentId);

    @Select("select * from application where student_id=#{studentId} and teacher_pass=#{teacherPass}")
    List<Application> selectBySP(Integer studentId, Integer teacherPass);
}
