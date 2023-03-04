package com.example.redisstudy.cache.service;

import com.example.redisstudy.cache.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String cachedUserName = operations.get("nameKey:" + userId);
        String userName;

        if (cachedUserName != null) {
            userName = cachedUserName;
        } else {
            // Redis Cache
            userName = externalApiService.getUserName(userId);
            operations.set("nameKey:" + userId, userName, 5, TimeUnit.SECONDS); // 만료시간 5초 설정
        }

        // Spring Cache
        int age = externalApiService.getUserAge(userId);

        return new UserProfile(userName, age);
    }
}
