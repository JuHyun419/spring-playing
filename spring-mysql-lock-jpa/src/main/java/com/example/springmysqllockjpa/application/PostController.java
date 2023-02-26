package com.example.springmysqllockjpa.application;

import com.example.springmysqllockjpa.domain.Post;
import com.example.springmysqllockjpa.service.PostCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostCommandService commandService;

    public PostController(PostCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<Void> save() {
        commandService.savePost();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Post> like(@PathVariable Long postId) {
        var stopWatch = new StopWatch();
        stopWatch.start("likePost");

        var post = commandService.likePost(postId);

        stopWatch.stop();
        log.info("likePost ... " + stopWatch.getTotalTimeMillis() + "ms");

        return ResponseEntity.ok(post);
    }
}
