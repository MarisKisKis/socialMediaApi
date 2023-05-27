package com.example.socialMediaApi.controller;

import com.example.socialMediaApi.model.Post;
import com.example.socialMediaApi.model.dto.PostDto;
import com.example.socialMediaApi.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Valid
@RestController
@RequestMapping("/api/user/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController (PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto createPost (@RequestHeader("X-Sharer-User-Id") long userId,
                               @RequestBody PostDto postDto) {
        log.info("Creating a post");
        return postService.createPost(postDto, userId);
    }

    @PatchMapping("/{postId}")
    public Post updatePost (@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable Long postId,
                            @RequestBody PostDto postDto) {
        log.info("Updating a post");
        return postService.updatePost(postDto, userId, postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable Long postId) {
        log.info("Deleting a post with id {}", postId);
        postService.deletePost(postId, userId);
    }
}
