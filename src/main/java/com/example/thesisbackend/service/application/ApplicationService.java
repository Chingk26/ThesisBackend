package com.example.thesisbackend.service.application;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ApplicationService {
    Map<String, String> addApplication(Integer studentId, Integer teacherId);

    Map<String, String> uploadPdf(Integer application_id,MultipartFile file);

    void read(Integer applicationId, HttpServletResponse response) throws IOException;
}
