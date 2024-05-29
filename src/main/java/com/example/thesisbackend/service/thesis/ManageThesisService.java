package com.example.thesisbackend.service.thesis;

import java.util.Map;

public interface ManageThesisService {
    Map<String, String> passThesisByTeacher(Integer thesisId);

    Map<String, String> refuseThesisByTeacher(Integer thesisId);

    Map<String, String> passThesisByDean(Integer thesisId);

    Map<String, String> refuseThesisByDean(Integer thesisId);
}
