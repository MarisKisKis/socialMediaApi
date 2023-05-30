package com.example.socialMediaApi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class FriendshipRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friend;

    @NonNull
    @Enumerated(EnumType.STRING)
    private FriendshipStatus friendStatus;

    @NonNull
    @Enumerated(EnumType.STRING)
    private RequestStatus reqStatus;
}
