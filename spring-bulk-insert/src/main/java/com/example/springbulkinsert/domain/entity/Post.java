package com.example.springbulkinsert.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Post {
    private final Long id;

    private final Long memberId;

    private final String contents;

    private final LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

}

