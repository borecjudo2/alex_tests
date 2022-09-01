package com.vertexinc.alextestscript.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
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
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue
  //  @Column(name = "userId")
  private UUID userId;

  //  @Column(name = "emailAddress", unique = true)
  private String emailAddress;

  //  @Column(name = "personId", unique = true)
  private String personId;

  //  @Column(name = "firstName")
  private String firstName;

  //  @Column(name = "lastName")
  private String lastName;

  //  @Column(name = "isAuth0Complete")
  private boolean isAuth0Complete;

  //  @Column(name = "hasAcceptedInvitation")
  private boolean hasAcceptedInvitation;

  //  @Column(name = "createdUtc")
  private Timestamp createdUtc = Timestamp.from(Instant.now());

  //  @Column(name = "modifiedUtc")
  private Timestamp modifiedUtc = Timestamp.from(Instant.now());

  //  @Column(name = "versionId")
  private UUID versionId = UUID.randomUUID();
}
