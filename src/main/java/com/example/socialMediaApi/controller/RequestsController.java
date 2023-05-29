package com.example.socialMediaApi.controller;

import com.example.socialMediaApi.model.dto.FriendshipRequestDto;
import com.example.socialMediaApi.service.FriendshipService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Valid
@RestController
@RequestMapping("/api/user/requests")
public class RequestsController {

    private final FriendshipService friendshipService;

    @Autowired
    public RequestsController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping
    public void sendFriendshipRequest(@RequestHeader("X-Sharer-User-Id") long userId, @RequestParam long friendId) {
        log.info("Sending friendRequest from user with id {} to user with id {}", userId, friendId);
        friendshipService.createRequest(userId, friendId);
    }

    @PatchMapping("/{requestId}/accept")
    public void acceptFriendRequest (@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        log.info("Accepting request with id {} by user with id {}", requestId, userId);
        friendshipService.acceptRequest(userId, requestId);
    }


}
