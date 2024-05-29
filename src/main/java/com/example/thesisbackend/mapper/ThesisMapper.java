package com.example.thesisbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thesisbackend.pojo.Thesis;
import com.example.thesisbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThesisMapper extends BaseMapper<Thesis> {
}
