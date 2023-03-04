package com.example.redisstudy.ranking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class SimpleRankingTest {

    @Autowired
    private RankingService rankingService;

    @Test
    void insertDummyScore() {
        for (int i = 0; i < 1_000_000; i++) {
            int score = (int) (Math.random() * 1_000_000); // 0 ~ 999,999 랜덤
            String userId = "user_" + i;

            rankingService.setUserScore(userId, score);
        }
    }

    @Test
    void getRanksTest() {
        var stopWatch = new StopWatch();
        stopWatch.start("inMemorySortPerformance");

        Long userRank = rankingService.getUserRanking("user_100");

        stopWatch.stop();
        System.out.println(String.format("Rank(%d) - took %s ms", userRank, stopWatch.getTotalTimeMillis() + "ms"));
    }

    @Test
    void inMemorySortPerformance() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            int score = (int) (Math.random() * 10_000_000); // 0 ~ 999,999 랜덤
            list.add(score);
        }

        var stopWatch = new StopWatch();
        stopWatch.start("inMemorySortPerformance");

        Collections.sort(list); // O(nlogn)

        stopWatch.stop();
        System.out.println("InMemory 정렬 수행시간: " + stopWatch.getTotalTimeMillis() + "ms");
    }

}
