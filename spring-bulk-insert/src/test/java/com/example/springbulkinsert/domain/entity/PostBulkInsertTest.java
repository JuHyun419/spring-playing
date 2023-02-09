package com.example.springbulkinsert.domain.entity;

import com.example.springbulkinsert.domain.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.springbulkinsert.domain.entity.PostRandomGenerator.createRandomPost;

@SpringBootTest
class PostBulkInsertTest {

    @Autowired
    private PostRepository repository;

    @Test
    void post_bulkInsert() {
        var posts = IntStream.range(0, 5)
                .mapToObj(i -> createRandomPost())
                .collect(Collectors.toList());

        var stopWatch = new StopWatch();
        stopWatch.start("BulkInsert");

        repository.bulkInsert(posts);

        stopWatch.stop();
        System.out.println("================================");
        System.out.println("DB BulkInsert 수행 시간: " + stopWatch.getTotalTimeSeconds());
    }

}