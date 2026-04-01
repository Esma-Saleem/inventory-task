package com.example.inventory.tenant;

public class TenantContext {

    private static final ThreadLocal<String> tenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        tenant.set(tenantId);
    }

    public static String getTenantId() {
        return tenant.get();
    }

    public static void clear() {
        tenant.remove();
    }
}