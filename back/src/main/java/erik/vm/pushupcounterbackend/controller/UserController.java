package erik.vm.pushupcounterbackend.controller;

import erik.vm.pushupcounterbackend.model.User;
import erik.vm.pushupcounterbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/click/{id}")
    public String registerClick(@PathVariable("id") Integer userId) {
        userService.recordClick(userId);
        return "success";
    }
}
