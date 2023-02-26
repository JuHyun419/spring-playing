package com.example.springmysqllockjpa.infrastructure;

import com.example.springmysqllockjpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
