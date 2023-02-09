package com.example.springbulkinsert.domain.entity;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.randomizers.range.LocalDateTimeRangeRandomizer;
import org.jeasy.random.randomizers.range.LongRangeRandomizer;

import java.time.LocalDateTime;

import static org.jeasy.random.FieldPredicates.*;

public class PostRandomGenerator {

    public static Post createRandomPost() {
        var parameters = new EasyRandomParameters()
                .excludeField(named("id"))
                .stringLengthRange(1, 10)
                .randomize(Long.class, new LongRangeRandomizer(1L, 100L))
                .randomize(LocalDateTime.class, new LocalDateTimeRangeRandomizer(
                                LocalDateTime.of(2000, 1, 1, 1, 1),
                                LocalDateTime.of(2023, 12, 31, 23, 59))
                );

        return new EasyRandom(parameters).nextObject(Post.class);
    }

}
