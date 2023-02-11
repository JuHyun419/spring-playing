package com.example.redisstudy;

import com.example.redisstudy.pubsub.ChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringRedisApplication implements CommandLineRunner {

    private final ChatService chatService;

    public SpringRedisApplication(ChatService chatService) {
        this.chatService = chatService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
        System.out.println("Application started...2");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started...");

        chatService.enterChatRoom("chat1");
    }
}
