package com.example.socialMediaApi.service.mapper;

import com.example.socialMediaApi.model.Post;
import com.example.socialMediaApi.model.User;
import com.example.socialMediaApi.model.dto.PostDto;

public class PostMapper {

    public static Post toPost (PostDto postDto, User user) {
        return new Post(postDto.getHeader(), postDto.getText(), postDto.getImage(), postDto.getImageContentType(), user);
    }
}
