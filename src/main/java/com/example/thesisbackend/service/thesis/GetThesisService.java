package com.example.thesisbackend.service.thesis;

import java.util.Map;

public interface GetThesisService {
    Map<String, Object> GetByStudentThesis(Integer studentId);

    Map<String, Object> GetByTeacherThesis(Integer teacherId);

    Map<String, Object> GetByTeacherPassThesis();
}
