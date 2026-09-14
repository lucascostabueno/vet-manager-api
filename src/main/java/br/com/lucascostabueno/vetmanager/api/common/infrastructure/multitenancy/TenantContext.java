package br.com.lucascostabueno.vetmanager.api.common.infrastructure.multitenancy;

public class TenantContext {
  public static final String DEFAULT_TENANT = "default";
  private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

  private TenantContext() {}

  public static void setTenantId(String tenantId) {
    CURRENT_TENANT.set(tenantId);
  }

  public static String getTenantId() {
    return CURRENT_TENANT.get();
  }

  public static void clear() {
    CURRENT_TENANT.remove();
  }
}
