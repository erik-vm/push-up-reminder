package erik.vm.pushupcounterbackend.scheduler;

import erik.vm.pushupcounterbackend.model.User;
import erik.vm.pushupcounterbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    // This runs at 9am, 12pm, 3pm, 6pm, and 9pm every day
    @Scheduled(cron = "0 0 9,12,15,18,21 * * *")
    public void sendReminders() {
        LocalDate today = LocalDate.now();
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getLastClickDate() == null || !user.getLastClickDate().equals(today)) {
                sendReminder(user);
            }
        }
    }

    private void sendReminder(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Don't forget to click today!");
            message.setText("Hey " + user.getName() + "! You haven't clicked the button today. Click it to stay consistent!");

            mailSender.send(message);
            System.out.println("Reminder sent to " + user.getEmail());
        } catch (Exception e) {
            System.err.println("Failed to send reminder to " + user.getEmail() + ": " + e.getMessage());
        }
    }
}
