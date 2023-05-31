package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>  {
    List<Post> findAllById(List<Long> friendsIds, Pageable pageable);
}
