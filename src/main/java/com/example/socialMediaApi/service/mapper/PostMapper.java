package com.example.socialMediaApi.service.mapper;

import com.example.socialMediaApi.model.Post;
import com.example.socialMediaApi.model.User;
import com.example.socialMediaApi.model.dto.PostDto;

public class PostMapper {

    public static Post toPost (PostDto postDto, User user) {
        return new Post(postDto.getHeader(), postDto.getText(), postDto.getImage(), postDto.getImageContentType(),
                postDto.getCreated(), user);
    }

    public static PostDto toPostDto(Post post) {
        return PostDto.builder()
                .header(post.getHeader())
                .text(post.getText())
                .image(post.getImage())
                .imageContentType(post.getImageContentType())
                .author(post.getAuthor().getName())
                .created(post.getCreated())
                .build();
    }
}
