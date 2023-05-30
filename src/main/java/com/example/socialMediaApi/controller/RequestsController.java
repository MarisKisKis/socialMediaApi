package com.example.socialMediaApi.controller;

import com.example.socialMediaApi.service.FriendshipReqService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Valid
@RestController
@RequestMapping("/api/user/requests")
public class RequestsController {

    private final FriendshipReqService friendshipReqService;

    @Autowired
    public RequestsController(FriendshipReqService friendshipReqService) {
        this.friendshipReqService = friendshipReqService;
    }

    @PostMapping
    public void sendFriendshipRequest(@RequestHeader("X-Sharer-User-Id") long userId, @RequestParam long friendId) {
        log.info("Sending friendRequest from user with id {} to user with id {}", userId, friendId);
        friendshipReqService.createRequest(userId, friendId);
    }

    @PatchMapping("/{requestId}/accept")
    public void acceptFriendRequest (@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        log.info("Accepting request with id {} by user with id {}", requestId, userId);
        friendshipReqService.acceptRequest(userId, requestId);
    }

    @PatchMapping("/{requestId}/reject")
    public void rejectFriendRequest (@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long requestId) {
        log.info("Rejecting request with id {} by user with id {}", requestId, userId);
        friendshipReqService.rejectRequest(userId, requestId);
    }


}
