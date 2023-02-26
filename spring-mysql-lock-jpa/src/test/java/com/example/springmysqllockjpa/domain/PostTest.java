package com.example.springmysqllockjpa.domain;

import com.example.springmysqllockjpa.service.PostCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@SpringBootTest
class PostTest {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostCommandService service;

    @Test
    void 동시에_100개의_요청을_한다() throws InterruptedException {
        var threadCount = 100;
        var id = 22L;
        var executorService = Executors.newFixedThreadPool(8);
        var latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                service.likePost(id);
                latch.countDown();
            });
        }

        latch.await();

        var post = repository.findById(id).get();
        System.out.println("LikeCount: " + post.getLikeCount());
    }

}