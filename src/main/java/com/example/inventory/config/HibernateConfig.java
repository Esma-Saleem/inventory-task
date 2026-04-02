package com.example.inventory.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class HibernateConfig {

    @PersistenceContext
    private EntityManager entityManager;
/* 
    @PostConstruct
    public void enableFilter() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenantFilter")
               .setParameter("tenantId", TenantContext.getTenantId());
    }
               */

    @PostConstruct
public void enableFilter() {
    String tenantId = TenantContext.getTenantId();

    if (tenantId != null) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenantFilter")
               .setParameter("tenantId", tenantId);
    }
}
}