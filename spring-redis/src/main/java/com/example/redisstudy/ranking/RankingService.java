package com.example.redisstudy.ranking;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankingService {

    private static final String LEADER_BOARD_KEY = "leaderBoard";

    private final StringRedisTemplate redisTemplate;

    public RankingService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean setUserScore(String userId, int score) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(LEADER_BOARD_KEY, userId, score);

        return true;
    }

    public Long getUserRanking(String userId) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRank(LEADER_BOARD_KEY, userId);
    }

    public List<String> getTopRank(int limit) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> ranges = zSetOperations.reverseRange(LEADER_BOARD_KEY, 0, limit - 1);

        return new ArrayList<>(ranges);
    }

}
