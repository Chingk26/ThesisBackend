package com.example.thesisbackend.service.teacher;

import java.util.Map;

public interface TeacherService {
    Map<String, Object> getAllStudentTeacher(Integer teacherId);

    Map<String, Object> getAllTeacher();
}
