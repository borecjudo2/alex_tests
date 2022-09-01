package com.vertexinc.alextestscript.repo;

import com.vertexinc.alextestscript.model.CustomerUser;
import com.vertexinc.alextestscript.model.CustomerUserId;
import com.vertexinc.alextestscript.model.CustomerUserRole;
import com.vertexinc.alextestscript.model.CustomerUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface CustomerUserRoleRepository extends JpaRepository<CustomerUserRole, CustomerUserRoleId> {
}
