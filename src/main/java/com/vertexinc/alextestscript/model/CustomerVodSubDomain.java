package com.vertexinc.alextestscript.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Builder
@Entity
@Table(name = "customer_vodsubdomain")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CustomerVodSubDomainId.class)
public class CustomerVodSubDomain {

  @Id
  private UUID customerId;

  @Id
  private String subDomain;

  private String auth0ClientId;

  private boolean isAuth0Complete;

  //  @Column(name = "createdUtc")
  private Timestamp createdUtc = Timestamp.from(Instant.now());

  //  @Column(name = "modifiedUtc")
  private Timestamp modifiedUtc = Timestamp.from(Instant.now());

  //  @Column(name = "versionId")
  private UUID versionId = UUID.randomUUID();
}
