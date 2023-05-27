package com.example.socialMediaApi.service;

import com.example.socialMediaApi.exception.NotFoundException;
import com.example.socialMediaApi.model.Post;
import com.example.socialMediaApi.model.User;
import com.example.socialMediaApi.model.dto.PostDto;
import com.example.socialMediaApi.repository.PostRepository;
import com.example.socialMediaApi.repository.UserRepository;
import com.example.socialMediaApi.service.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto, long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("Нет такого пользователя");
        }
        User user = userOpt.get();
        Post post = PostMapper.toPost(postDto, user);
        postRepository.save(post);
        return postDto;
    }

    @Override
    public Post updatePost(PostDto postDto, long userId, Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            throw new NotFoundException("Нет такого поста");
        }
        Post post = postOpt.get();
        if (postDto.getHeader()!= null) {
            post.setHeader(postDto.getHeader());
        }
        if (postDto.getText()!= null) {
            post.setText(postDto.getText());
        }
        if (postDto.getImage()!=null) {
            post.setImage(postDto.getImage());
        }
        if (postDto.getImageContentType()!= null) {
            post.setImageContentType(postDto.getImageContentType());
        }
        postRepository.save(post);
        return post;
    }

    @Override
    public void deletePost(Long postId, long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) {
            throw new NotFoundException("Нет такого поста");
        }
        postRepository.deleteById(postId);
    }
}
