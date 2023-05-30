package com.example.socialMediaApi.service;

import com.example.socialMediaApi.exception.NotFoundException;
import com.example.socialMediaApi.model.Message;
import com.example.socialMediaApi.model.User;
import com.example.socialMediaApi.repository.FriendshipRepository;
import com.example.socialMediaApi.repository.MessageRepository;
import com.example.socialMediaApi.repository.SubscribersRepository;
import com.example.socialMediaApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class FriendshipServiceImpl implements FriendshipService{

    private final FriendshipRepository friendshipRepository;

    private final SubscribersRepository subscribersRepository;

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, SubscribersRepository subscribersRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.friendshipRepository = friendshipRepository;
        this.subscribersRepository = subscribersRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void deleteFriend(long userId, long friendId) {
        checkFriendship(userId, friendId);
        friendshipRepository.deleteFriendsByFriend1AndFriend2(userId, friendId);
        try {
            subscribersRepository.deleteSubscribersBySubscriberId(userId);
        } catch (NotFoundException e) {
            e.getMessage("This subscription already doesn't exist");
        }
    }

    @Override
    public void sendMessage(long userId, long friendId, String text) {
        checkFriendship(userId, friendId);
        Message message = new Message();
        message.setSender(getUser(userId));
        message.setReceiver(getUser(friendId));
        message.setText(text);
        message.setSent(LocalDateTime.now());
        messageRepository.save(message);
    }

    private void checkFriendship(long userId, long friendId) {
        try {
            friendshipRepository.getFriendsByFriend1AndFriend2(userId, friendId);
        } catch (NotFoundException e) {
            e.getMessage("This friendship already doesn't exist");
        }
    }

    private User getUser(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("Could not find User with id = " + userId);
        }
        return userOpt.get();
    }
}
