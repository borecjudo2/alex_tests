package com.vertexinc.alextestscript.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Builder
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue
  private UUID customerId;

  private BigInteger customerTypeId;

  private String customerName;

  @Column(unique = true)
  private String auth0OrganizationId;

  @Column(unique = true)
  private String organizationName;

  private String organizationDisplayName;

  private String brandingLogoUrl;

  private String organizationPrimaryHexColor;

  private String organizationPageBackgroundHexColor;

  private BigInteger essoMode;

  private UUID primaryContactUserId;

  private boolean isAuth0Complete;

  //  @Column(name = "createdUtc")
  private Timestamp createdUtc = Timestamp.from(Instant.now());

  //  @Column(name = "modifiedUtc")
  private Timestamp modifiedUtc = Timestamp.from(Instant.now());

  //  @Column(name = "versionId")
  private UUID versionId = UUID.randomUUID();
}
