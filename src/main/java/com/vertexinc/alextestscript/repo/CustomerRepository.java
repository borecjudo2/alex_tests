package com.vertexinc.alextestscript.repo;

import com.vertexinc.alextestscript.model.Customer;
import com.vertexinc.alextestscript.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
