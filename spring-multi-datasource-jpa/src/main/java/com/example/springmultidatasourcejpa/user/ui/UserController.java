package com.example.springmultidatasourcejpa.user.ui;

import com.example.springmultidatasourcejpa.user.entity.User;
import com.example.springmultidatasourcejpa.user.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public void test() {
        userRepository.save(
                new User(null, "User1", "user@naver.com")
        );
    }
}
