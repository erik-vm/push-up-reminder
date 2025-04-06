package com.example.dailyclicker;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClickService {
    private final Map<String, LocalDate> clickMap = new ConcurrentHashMap<>();

    public void recordClick(String userId) {
        clickMap.put(userId, LocalDate.now());
    }

    public boolean hasClickedToday(String userId) {
        return LocalDate.now().equals(clickMap.get(userId));
    }

    public Map<String, LocalDate> getAllClicks() {
        return clickMap;
    }
}