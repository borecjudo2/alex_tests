package com.vertexinc.alextestscript.repo;

import com.vertexinc.alextestscript.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface CustomerTypeRepository extends JpaRepository<CustomerType, BigInteger> {
}
