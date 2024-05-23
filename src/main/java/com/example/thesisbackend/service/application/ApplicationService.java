package com.example.thesisbackend.service.application;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ApplicationService {
    Map<String, String> addApplication(Integer studentId, Integer teacherId);

    Map<String, String> uploadPdf(Integer application_id,MultipartFile file, HttpServletRequest request);


    void read(Integer applicationId, HttpServletResponse response) throws IOException;

    Map<String, String> passApplication(Integer applicationId);

    Map<String, String> refuseApplication(Integer applicationId);

}
