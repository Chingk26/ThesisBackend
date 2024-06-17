package com.example.thesisbackend.service.thesis;

import java.util.Map;

public interface ManageThesisService {
    Map<String, String> passThesisByTeacher(Integer thesisId);

    Map<String, String> refuseThesisByTeacher(Integer thesisId);

    Map<String, String> passThesisByDean(Integer thesisId);

    Map<String, String> refuseThesisByDean(Integer thesisId);

    Map<String, String> updateProgress(Integer thesisId,String progress);

    Map<String, String> updateQuality(Integer thesisId, String quality);

    Map<String, String> updateOpinion(Integer thesisId, String opinion);

    Map<String, String> reactThesisByTeacher(Integer thesisId);
}
