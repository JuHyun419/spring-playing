package com.example.springmysqllockjpa.service;

import com.example.springmysqllockjpa.domain.Post;
import com.example.springmysqllockjpa.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PostCommandService {

    private final PostRepository postRepository;

    public PostCommandService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost() {
        for (int i = 0; i < 10; i++) {
            var post = Post.builder()
                    .contents("content" + i)
                    .likeCount(0L)
                    .build();

            postRepository.save(post);
        }
    }

    @Transactional
    public Post likePost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow();
        post.incrementLikeCount();

        postRepository.saveAndFlush(post);

        return post;
    }
}
