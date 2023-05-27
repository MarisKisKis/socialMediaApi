package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>  {
}
