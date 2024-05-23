package com.example.thesisbackend.service.application;

import java.util.Map;
import java.util.Objects;

public interface GetApplicationService {
    Map<String, Object> GetByStudentApplication(Integer studentId);

    Map<String, Object> GetByTeacherApplication(Integer teacherId);
}
