package com.example.redisstudy.cache.web;

import com.example.redisstudy.cache.dto.UserProfile;
import com.example.redisstudy.cache.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCacheController {

    private final UserService userService;

    public RedisCacheController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable String userId) {
        return userService.getUserProfile(userId);
    }

}
