package com.example.socialMediaApi.controller;

import com.example.socialMediaApi.model.dto.PostDto;
import com.example.socialMediaApi.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Valid
@RestController
@RequestMapping("/api/user/feed")
public class FeedController {

    private final PostService postService;

    @Autowired
    public FeedController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getFeed(@RequestHeader("X-Sharer-User-Id") long userId,
                                 @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                 @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Get feed to user with ID = {}", userId);
        return postService.getFeed(userId, from, size);
    }
}
