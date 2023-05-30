package com.example.socialMediaApi.controller;

import com.example.socialMediaApi.service.FriendshipService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Valid
@RestController
@RequestMapping("/api/user/friends")
public class FriendsController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendsController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/{friendId}/message")
    public void sendMessage(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long friendId,
                            @RequestBody String text) {
        log.info("Sending a message to user with id {}", friendId);
        friendshipService.sendMessage(userId, friendId, text);
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriend(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long friendId) {
        log.info("Deleting from friends user with id {}", friendId);
        friendshipService.deleteFriend(userId, friendId);
    }
}
