package br.com.lucascostabueno.vetmanager.api.common.infrastructure.persistence.jpa.domain;

import br.com.lucascostabueno.vetmanager.api.common.infrastructure.multitenancy.TenantContext;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.hibernate.annotations.TenantId;

@Getter
@MappedSuperclass
public abstract class BaseTenantFullAuditEntity extends BaseFullAuditEntity {

  @TenantId
  @Column(name = "tenant_id", nullable = false, updatable = false)
  private String tenantId;

  @PrePersist
  public void prePersistTenant() {
    this.tenantId = TenantContext.getTenantId();
  }
}
