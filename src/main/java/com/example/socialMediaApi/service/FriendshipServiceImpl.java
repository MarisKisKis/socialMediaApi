package com.example.socialMediaApi.service;

import com.example.socialMediaApi.exception.IllegalArgumentException;
import com.example.socialMediaApi.exception.NotFoundException;
import com.example.socialMediaApi.model.FriendshipRequest;
import com.example.socialMediaApi.model.FriendshipStatus;
import com.example.socialMediaApi.model.User;
import com.example.socialMediaApi.model.dto.FriendshipRequestDto;
import com.example.socialMediaApi.repository.FriendshipRepository;
import com.example.socialMediaApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRequest(long userId, long friendId) {
        User initiator = getUser(userId);
        User friend = getUser(friendId);
        FriendshipRequest request = new FriendshipRequest();
        request.setInitiator(initiator);
        request.setFriend(friend);
        request.setStatus(FriendshipStatus.SUBSCRIBER);
        friendshipRepository.save(request);
    }

    @Override
    public void acceptRequest(long userId, long requestId) {
        Optional<FriendshipRequest> reqOpt = friendshipRepository.findById(requestId);
        if (reqOpt.isEmpty()) {
            throw new NotFoundException("Could not find FriendshipRequest with id = " + requestId);
        }
        FriendshipRequest addRequest = reqOpt.get();
        if (userId != addRequest.getFriend().getId()) {
            throw new IllegalArgumentException("The request is addressed to the wrong user!");
        }
        addRequest.setStatus(FriendshipStatus.FRIEND);
        friendshipRepository.save(addRequest);
    }

    private User getUser(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("Could not find User with id = " + userId);
        }
        return userOpt.get();
    }
}


