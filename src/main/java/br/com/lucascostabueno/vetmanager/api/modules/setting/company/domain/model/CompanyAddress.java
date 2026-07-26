package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "company_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAddress {

  @Id
  @Column(name = "company_id")
  private UUID id;

  @Column(name = "zip_code", nullable = false, length = 9)
  private String zipCode;

  @Column(name = "address", nullable = false, length = 100)
  private String address;

  @Column(name = "number", nullable = false, length = 10)
  private String number;

  @Column(name = "complement", columnDefinition = "TEXT")
  private String complement;

  @Column(name = "neighborhood", nullable = false, length = 100)
  private String neighborhood;

  @Column(name = "city", nullable = false, length = 100)
  private String city;

  @Column(name = "state", nullable = false, length = 2)
  private String state;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "company_id")
  private Company company;
}
