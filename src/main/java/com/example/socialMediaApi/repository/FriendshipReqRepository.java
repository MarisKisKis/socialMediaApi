package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.FriendshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipReqRepository extends JpaRepository<FriendshipRequest, Long> {
}
