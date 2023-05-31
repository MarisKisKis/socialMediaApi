package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friends, Long> {

    void deleteFriendsByFriend1AndFriend2(long userId, long friendId);

    Friends getFriendsByFriend1AndFriend2(long userId, long friendId);

    List<Friends> findAllByFriend1(long userId);
}
