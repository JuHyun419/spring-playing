package com.example.redisstudy.cache.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

//    Map<String, String > localSession = new HashMap<>();

    private final String KEY = "name";

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        session.setAttribute(KEY, name);

        return "redis session saved...";
    }

    @GetMapping("/myName")
    public String myName(HttpSession session) {
        return (String) session.getAttribute(KEY);
    }

}
