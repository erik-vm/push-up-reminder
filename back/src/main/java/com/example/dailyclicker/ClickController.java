package com.example.dailyclicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClickController {

    @Autowired
    private ClickService clickService;

    @PostMapping("/click")
    public String registerClick(@RequestBody UserClick userClick) {
        clickService.recordClick(userClick.userId());
        return "Click recorded for " + userClick.userId();
    }

    public record UserClick(String userId) {}
}