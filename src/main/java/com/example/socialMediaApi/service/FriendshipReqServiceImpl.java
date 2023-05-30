package com.example.socialMediaApi.service;

import com.example.socialMediaApi.exception.IllegalArgumentException;
import com.example.socialMediaApi.exception.NotFoundException;
import com.example.socialMediaApi.model.*;
import com.example.socialMediaApi.repository.FriendshipRepository;
import com.example.socialMediaApi.repository.FriendshipReqRepository;
import com.example.socialMediaApi.repository.SubscribersRepository;
import com.example.socialMediaApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FriendshipReqServiceImpl implements FriendshipReqService {

    private final FriendshipReqRepository friendshipReqRepository;

    private final FriendshipRepository friendshipRepository;

    private final SubscribersRepository subscribersRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendshipReqServiceImpl(FriendshipReqRepository friendshipReqRepository, FriendshipRepository friendshipRepository, SubscribersRepository subscribersRepository, UserRepository userRepository) {
        this.friendshipReqRepository = friendshipReqRepository;
        this.friendshipRepository = friendshipRepository;
        this.subscribersRepository = subscribersRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRequest(long userId, long friendId) {
        User initiator = getUser(userId);
        User friend = getUser(friendId);
        FriendshipRequest request = new FriendshipRequest();
        request.setInitiator(initiator);
        request.setFriend(friend);
        request.setFriendStatus(FriendshipStatus.SUBSCRIBER);
        request.setReqStatus(RequestStatus.PENDING);
        friendshipReqRepository.save(request);
        Subscribers subscribers = new Subscribers();
        subscribers.setSubscriberId(userId);
        subscribers.setUserId(friendId);
        subscribersRepository.save(subscribers);
    }

    @Override
    public void acceptRequest(long userId, long requestId) {
        FriendshipRequest addRequest = getRequest(requestId);
        if (userId != addRequest.getFriend().getId()) {
            throw new IllegalArgumentException("The request is addressed to the wrong user!");
        }
        addRequest.setFriendStatus(FriendshipStatus.FRIEND);
        addRequest.setReqStatus(RequestStatus.ACCEPTED);
        Friends friendship = new Friends();
        friendship.setFriend1(userId);
        friendship.setFriend2(addRequest.getInitiator().getId());
        friendshipRepository.save(friendship);
        friendshipReqRepository.save(addRequest);
        Subscribers subscribers = new Subscribers();
        subscribers.setSubscriberId(userId);
        subscribers.setUserId(addRequest.getInitiator().getId());
        subscribersRepository.save(subscribers);
    }

    @Override
    public void rejectRequest(long userId, long requestId) {
        FriendshipRequest addRequest = getRequest(requestId);
        if (userId != addRequest.getFriend().getId()) {
            throw new IllegalArgumentException("The request is addressed to the wrong user!");
        }
        addRequest.setReqStatus(RequestStatus.REJECTED);
        friendshipReqRepository.save(addRequest);
    }

    private User getUser(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("Could not find User with id = " + userId);
        }
        return userOpt.get();
    }

    private FriendshipRequest getRequest(long requestId) {
        Optional<FriendshipRequest> reqOpt = friendshipReqRepository.findById(requestId);
        if (reqOpt.isEmpty()) {
            throw new NotFoundException("Could not find FriendshipRequest with id = " + requestId);
        }
        FriendshipRequest addRequest = reqOpt.get();
        return addRequest;
    }
}


