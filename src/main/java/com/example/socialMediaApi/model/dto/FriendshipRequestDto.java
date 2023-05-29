package com.example.socialMediaApi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class FriendshipRequestDto {
    @NonNull
    private long id;
    @NonNull
    private long initiatorId;
    @NonNull
    private long friendId;
}
