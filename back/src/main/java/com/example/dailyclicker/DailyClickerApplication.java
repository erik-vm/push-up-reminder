package com.example.dailyclicker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyClickerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyClickerApplication.class, args);
    }
}