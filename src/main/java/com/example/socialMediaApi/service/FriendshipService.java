package com.example.socialMediaApi.service;

public interface FriendshipService {
    void createRequest(long userId, long friendId);

    void acceptRequest(long userId, long requestId);

}
