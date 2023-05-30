package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friends, Long> {

    void deleteFriendsByFriend1AndFriend2(long userId, long friendId);

    Friends getFriendsByFriend1AndFriend2(long userId, long friendId);
}
