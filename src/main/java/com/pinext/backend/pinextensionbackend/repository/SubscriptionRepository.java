package com.pinext.backend.pinextensionbackend.repository;

import com.pinext.backend.pinextensionbackend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  SubscriptionRepository extends JpaRepository<Subscription, String> {
    Optional<Subscription> findBySubscriptionId(String id);
}
