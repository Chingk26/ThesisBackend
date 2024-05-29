package com.example.thesisbackend.mapper;

import com.example.thesisbackend.pojo.Proposal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProposalMapper {

    @Select("SELECT * FROM proposal WHERE student_id = #{studentId}")
    List<Proposal> findByStudentId(Integer studentId);

    @Delete("DELETE FROM proposal WHERE proposal_id = #{proposalId}")
    void deleteById(Integer proposalId);

    @Update("UPDATE proposal SET student_id = #{studentId}, teacher_id = #{teacherId}, " +
            "teacher_pass = #{teacherPass}, dean_pass = #{deanPass}, content = #{content}, title = #{title} " +
            "WHERE proposal_id = #{proposalId}")
    void updateById(Proposal proposal);

    @Insert("INSERT INTO proposal (student_id, teacher_id, teacher_pass, dean_pass, content, title) " +
            "VALUES (#{studentId}, #{teacherId}, #{teacherPass}, #{deanPass}, #{content}, #{title})")
    @Options(useGeneratedKeys = true, keyProperty = "proposalId")
    void insert(Proposal proposal);

    @Select("SELECT * FROM proposal WHERE proposal_id = #{proposalId}")
    Proposal selectById(Integer proposalId);
}
