package com.example.inventory.repository;

import com.example.inventory.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerRepository extends JpaRepository<Dealer, String> {

    List<Dealer> findByTenantId(String tenantId);

    long countBySubscriptionType(String subscriptionType);

    // optional (better)
    long countBySubscriptionTypeAndTenantId(String subscriptionType, String tenantId);
}