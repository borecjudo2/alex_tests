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
@Table(name = "customer_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CustomerUserId.class)
public class CustomerUser {

  @Id
  private UUID customerId;

  @Id
  private UUID userId;

  //  @Column(name = "createdUtc")
  private Timestamp createdUtc = Timestamp.from(Instant.now());

  //  @Column(name = "modifiedUtc")
  private Timestamp modifiedUtc = Timestamp.from(Instant.now());

  //  @Column(name = "versionId")
  private UUID versionId = UUID.randomUUID();
}
