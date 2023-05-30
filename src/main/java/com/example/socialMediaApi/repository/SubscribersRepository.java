package com.example.socialMediaApi.repository;

import com.example.socialMediaApi.model.Subscribers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribersRepository extends JpaRepository<Subscribers, Long> {

    void deleteSubscribersBySubscriberId(long userId);
}
