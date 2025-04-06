package com.example.dailyclicker;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ReminderScheduler {

    @Autowired
    private ClickService clickService;

    @Autowired
    private JavaMailSender mailSender;

    private final Set<String> userIds = Set.of("user123");

    private void sendReminder(String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com");
        message.setSubject("Don't forget to click today!");
        message.setText("Hey " + userId + "! You haven't clicked the button today. Click now!");

        mailSender.send(message);
        System.out.println("Reminder sent to " + userId);
    }

    @PostConstruct
    public void init() {
        userIds.forEach(id -> System.out.println("Tracking " + id));
    }

    @Scheduled(cron = "0 0 9,12,15,18,21 * * *")
    public void sendReminders() {
        for (String userId : userIds) {
            if (!clickService.hasClickedToday(userId)) {
                sendReminder(userId);
            }
        }
    }
}