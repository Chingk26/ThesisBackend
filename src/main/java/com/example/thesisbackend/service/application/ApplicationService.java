package com.example.thesisbackend.service.application;

import java.util.Map;

public interface ApplicationService {
    Map<String, String> addApplication(Integer studentId, Integer teacherId, String content);
}
