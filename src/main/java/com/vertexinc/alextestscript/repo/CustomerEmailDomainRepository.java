package com.vertexinc.alextestscript.repo;

import com.vertexinc.alextestscript.model.CustomerEmailDomain;
import com.vertexinc.alextestscript.model.CustomerEmailDomainId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface CustomerEmailDomainRepository extends JpaRepository<CustomerEmailDomain, CustomerEmailDomainId> {
}
