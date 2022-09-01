package com.vertexinc.alextestscript.service.impl;

import com.vertexinc.alextestscript.model.Customer;
import com.vertexinc.alextestscript.model.CustomerEmailDomain;
import com.vertexinc.alextestscript.model.CustomerType;
import com.vertexinc.alextestscript.model.CustomerUser;
import com.vertexinc.alextestscript.model.CustomerUserRole;
import com.vertexinc.alextestscript.model.CustomerVodSubDomain;
import com.vertexinc.alextestscript.model.Role;
import com.vertexinc.alextestscript.model.User;
import com.vertexinc.alextestscript.repo.CustomerEmailDomainRepository;
import com.vertexinc.alextestscript.repo.CustomerRepository;
import com.vertexinc.alextestscript.repo.CustomerTypeRepository;
import com.vertexinc.alextestscript.repo.CustomerUserRepository;
import com.vertexinc.alextestscript.repo.CustomerUserRoleRepository;
import com.vertexinc.alextestscript.repo.CustomerVobSubDomainRepository;
import com.vertexinc.alextestscript.repo.RoleRepository;
import com.vertexinc.alextestscript.repo.UserRepository;
import com.vertexinc.alextestscript.service.FakeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Service
@AllArgsConstructor
public class FakeServiceImpl implements FakeService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final CustomerRepository customerRepository;

  private final CustomerTypeRepository customerTypeRepository;

  private final CustomerUserRepository customerUserRepository;

  private final CustomerUserRoleRepository customerUserRoleRepository;

  private final CustomerEmailDomainRepository customerEmailDomainRepository;

  private final CustomerVobSubDomainRepository customerVobSubDomainRepository;

  @Override
  public void createFakeData(int countFakeRows) {
    CustomerType customerType = createCustomerType(1);
    Role role = createRole(1);

    List<User> users = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<CustomerEmailDomain> customerEmailDomains = new ArrayList<>();
    List<CustomerVodSubDomain> customerVodSubDomains = new ArrayList<>();
    List<CustomerUser> customerUsers = new ArrayList<>();
    List<CustomerUserRole> customerUserRoles = new ArrayList<>();

    for (int i = 0; i < countFakeRows; i++) {
      User user = createUser(i);
      users.add(user);

      Customer customer = createCustomer(i, customerType, user);
      customers.add(customer);

      CustomerEmailDomain customerEmailDomain = createCustomerEmailDomain(i, customer);
      customerEmailDomains.add(customerEmailDomain);

      CustomerVodSubDomain customerVodSubDomain = createCustomerVodSubDomain(i, customer);
      customerVodSubDomains.add(customerVodSubDomain);

      CustomerUser customerUser = createCustomerUser(customer, user);
      customerUsers.add(customerUser);

      CustomerUserRole customerUserRole = createCustomerUserRole(customer, user, role);
      customerUserRoles.add(customerUserRole);
    }
  }

  private CustomerType createCustomerType(int countFakeRows) {
    return customerTypeRepository.save(CustomerType.builder()
        .customerTypeId(BigInteger.valueOf(countFakeRows))
        .customerTypeName(countFakeRows + "_customer_alex_test")
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private Role createRole(int countFakeRows) {
    return roleRepository.save(Role.builder()
        .roleId(BigInteger.valueOf(countFakeRows))
        .roleName(countFakeRows + "_role_alex_test")
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private User createUser(int counter) {
    User user = new User();
    user.setEmailAddress(counter + "_alex_test@test.com");
    user.setPersonId(counter + "_person_id");
    user.setFirstName(counter + "_first_name");
    user.setLastName(counter + "_last_name");
    return userRepository.save(user);
  }

  private Customer createCustomer(int i, CustomerType customerType, User user) {
    return customerRepository.save(Customer.builder()
        .customerTypeId(customerType.getCustomerTypeId())
        .primaryContactUserId(user.getUserId())
        .customerName(i + "_customer_name")
        .auth0OrganizationId(i + "_auth0OrganizationId")
        .organizationName(i + "_organizationName")
        //        .organizationPageBackgroundHexColor(i + "_organizationPageBackgroundHexColor")
        //        .organizationPrimaryHexColor(i + "_organizationPrimaryHexColor")
        .organizationDisplayName(i + "_organizationDisplayName")
        .brandingLogoUrl(i + "_brandingLogoUrl")
        .essoMode(BigInteger.valueOf(0))
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private CustomerEmailDomain createCustomerEmailDomain(int counter, Customer customer) {
    return customerEmailDomainRepository.save(CustomerEmailDomain.builder()
        .customerId(customer.getCustomerId())
        .emailDomain(counter + "_emailDomain")
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private CustomerVodSubDomain createCustomerVodSubDomain(int counter, Customer customer) {
    return customerVobSubDomainRepository.save(CustomerVodSubDomain.builder()
        .customerId(customer.getCustomerId())
        .subDomain(counter + "_subDomain")
        .auth0ClientId(counter + "_auth0ClientId")
        .isAuth0Complete(false)
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private CustomerUser createCustomerUser(Customer customer, User user) {
    return customerUserRepository.save(CustomerUser.builder()
        .customerId(customer.getCustomerId())
        .userId(user.getUserId())
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }

  private CustomerUserRole createCustomerUserRole(Customer customer, User user, Role role) {
    return customerUserRoleRepository.save(CustomerUserRole.builder()
        .customerId(customer.getCustomerId())
        .userId(user.getUserId())
        .roleId(role.getRoleId())
        .createdUtc(Timestamp.from(Instant.now()))
        .modifiedUtc(Timestamp.from(Instant.now()))
        .versionId(UUID.randomUUID())
        .build());
  }
}
