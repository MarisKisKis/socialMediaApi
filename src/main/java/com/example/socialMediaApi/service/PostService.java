package com.example.socialMediaApi.service;

import com.example.socialMediaApi.model.Post;
import com.example.socialMediaApi.model.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto, long userId);

    Post updatePost(PostDto postDto, long userId, Long postId);

    void deletePost(Long postId, long userId);
}
