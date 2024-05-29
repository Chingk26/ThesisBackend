package com.example.thesisbackend.mapper;

import com.example.thesisbackend.pojo.Proposal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProposalMapper {

    @Select("SELECT * FROM proposal WHERE student_id = #{studentId}")
    List<Proposal> findByStudentId(Integer studentId);

    @Delete("DELETE FROM proposal WHERE id = #{id}")
    void deleteById(Integer id);

    @Update("UPDATE proposal SET student_id = #{studentId}, teacher_id = #{teacherId}, " +
            "submission_date = #{submissionDate}, teacher_pass = #{teacherPass}, " +
            "dean_pass = #{deanPass}, content = #{content}, title = #{title} WHERE id = #{id}")
    void updateById(Proposal proposal);

    @Insert("INSERT INTO proposal (student_id, teacher_id, submission_date, teacher_pass, " +
            "dean_pass, content, title) VALUES (#{studentId}, #{teacherId}, #{submissionDate}, " +
            "#{teacherPass}, #{deanPass}, #{content}, #{title})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Proposal proposal);

    @Select("SELECT * FROM proposal WHERE id = #{id}")
    Proposal selectById(Integer id);
}
