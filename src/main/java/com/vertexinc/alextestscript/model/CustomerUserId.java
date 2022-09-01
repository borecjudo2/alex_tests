package com.vertexinc.alextestscript.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserId implements Serializable {

  private UUID customerId;

  private UUID userId;
}
