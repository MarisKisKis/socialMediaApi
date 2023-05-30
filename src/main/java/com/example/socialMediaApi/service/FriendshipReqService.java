package com.example.socialMediaApi.service;

public interface FriendshipReqService {
    void createRequest(long userId, long friendId);

    void acceptRequest(long userId, long requestId);

    void rejectRequest(long userId, long requestId);
}
