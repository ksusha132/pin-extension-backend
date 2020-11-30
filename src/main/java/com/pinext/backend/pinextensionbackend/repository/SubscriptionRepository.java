package com.pinext.backend.pinextensionbackend.repository;

import com.pinext.backend.pinextensionbackend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  SubscriptionRepository extends JpaRepository<Subscription, String> {
}
