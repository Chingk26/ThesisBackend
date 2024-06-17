package com.example.thesisbackend.service.thesis;

import java.time.LocalDateTime;
import java.util.Map;

public interface ThesisTimeService {
    Map<String, String> updateAllStart(LocalDateTime start);

    Map<String, String> updateAllEnd(LocalDateTime end);
}
