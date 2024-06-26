package com.example.thesisbackend.service.thesis;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ThesisService {
    Map<String, String> addThesis(Integer studentId, Float result, Integer version);

    Map<String, String> uploadPdf(Integer thesisId, MultipartFile file, HttpServletRequest request);

    void read(Integer thesisId, HttpServletResponse response) throws IOException;

    Map<String, String> updateThesis(Integer thesis_id, Float result);
}
