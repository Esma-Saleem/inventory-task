package com.example.inventory.tenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String tenantId = request.getHeader("X-Tenant-Id");

        if (tenantId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing Tenant Id");
            return;
        }

        TenantContext.setTenantId(tenantId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}


/* 
@Component
public class TenantFilter extends OncePerRequestFilter {

    private static final String HEADER = "X-Tenant-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String tenantId = request.getHeader(HEADER);

        if (tenantId == null || tenantId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing X-Tenant-Id");
            return;
        }

        try {
            TenantContext.setTenantId(tenantId);
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
        
if (tenantId == null) {
    throw new RuntimeException("Missing X-Tenant-Id");
}

        if (!entity.getTenantId().equals(TenantContext.getTenantId())) {
    throw new RuntimeException("Forbidden");
}
    }
}
    */