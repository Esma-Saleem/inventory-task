package com.example.inventory.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String tenantId = req.getHeader("X-Tenant-Id");

        if (tenantId != null) {
            TenantContext.setTenantId(tenantId);

            // 🔥 Enable Hibernate filter per request
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("tenantFilter")
                   .setParameter("tenantId", tenantId);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}