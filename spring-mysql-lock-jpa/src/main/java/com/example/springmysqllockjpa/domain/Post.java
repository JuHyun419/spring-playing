package com.example.springmysqllockjpa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contents;

    @Column
//    @Convert(converter = AtomicLongConverter.class)
    private Long likeCount;
//    private AtomicLong likeCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Post(String contents, Long likeCount) {
        this(contents, likeCount, null, null);
    }

    @Builder
    public Post(String contents, Long likeCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.contents = Objects.requireNonNull(contents);
        this.likeCount = likeCount == null ? 0 : likeCount;
//        this.likeCount = likeCount == null ? new AtomicLong() : new AtomicLong(likeCount);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;
    }

    public void incrementLikeCount() {
//        likeCount.incrementAndGet();
        likeCount += 1;
        this.updatedAt = LocalDateTime.now();
    }

    public void decreaseLikeCount() {
        likeCount -= 1;
        this.updatedAt = LocalDateTime.now();
    }
}
