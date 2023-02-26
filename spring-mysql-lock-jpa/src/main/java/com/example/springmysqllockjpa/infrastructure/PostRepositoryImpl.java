package com.example.springmysqllockjpa.infrastructure;

import com.example.springmysqllockjpa.domain.Post;
import com.example.springmysqllockjpa.domain.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository repository;

    public PostRepositoryImpl(PostJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Post saveAndFlush(Post entity) {
        return repository.saveAndFlush(entity);
    }
}
