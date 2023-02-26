package com.example.springmysqllockjpa.domain;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(Long id);

    Post save(Post entity);

    Post saveAndFlush(Post entity);
}
