package erik.vm.pushupcounterbackend.service;

import erik.vm.pushupcounterbackend.model.User;
import erik.vm.pushupcounterbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User save(User user) {
        return userRepository.save(user);
    }

    public void recordClick(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LocalDate today = LocalDate.now();

            if (!today.equals(user.getLastClickDate())) {
                user.setTimesClicked(user.getTimesClicked() + 1);
                user.setLastClickDate(today);
                userRepository.save(user);
            }
        } else {
            System.out.println("User not found: " + userId);
        }
    }

    public boolean hasClickedToday(Integer userId) {
        return userRepository.findById(userId)
                .map(user -> LocalDate.now().equals(user.getLastClickDate()))
                .orElse(false);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
