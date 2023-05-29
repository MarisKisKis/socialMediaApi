package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<FriendshipRequest, Long> {
}
