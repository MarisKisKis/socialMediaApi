package com.example.socialMediaApi.service;

public interface FriendshipService {
    void deleteFriend(long userId, long friendId);

    void sendMessage(long userId, long friendId, String text);
}
