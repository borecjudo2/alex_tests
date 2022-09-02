package com.vertexinc.alextestscript.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@Service
@AllArgsConstructor
public class FakeServiceImpl implements FakeService {

  public static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String USERS_FILE = "target/USERS_FILE.json";
  private static final String CUSTOMERS_FILE = "target/CUSTOMERS_FILE.json";
  private static final String CUSTOMERS_EMAIL_DOMAIN_FILE = "target/CUSTOMERS_EMAIL_DOMAIN_FILE.json";
  private static final String CUSTOMERS_VOD_FILE = "target/CUSTOMERS_VOD_FILE.json";
  private static final String CUSTOMERS_USER_FILE = "target/CUSTOMERS_USER_FILE.json";
  private static final String CUSTOMERS_USER_ROLE_FILE = "target/CUSTOMERS_USER_ROLE_FILE.json";

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

    try {
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
    } finally {
      saveInFile(USERS_FILE, users);
      saveInFile(CUSTOMERS_FILE, customers);
      saveInFile(CUSTOMERS_EMAIL_DOMAIN_FILE, customerEmailDomains);
      saveInFile(CUSTOMERS_VOD_FILE, customerVodSubDomains);
      saveInFile(CUSTOMERS_USER_FILE, customerUsers);
      saveInFile(CUSTOMERS_USER_ROLE_FILE, customerUserRoles);
    }
  }

  @SneakyThrows
  private void saveInFile(String filePath, List<?> list) {
    MAPPER.writeValue(new File(filePath), list);
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

  @SneakyThrows
  @Override
  public void deleteFakeData() {
    File customerUserRolesFile = new File(CUSTOMERS_USER_ROLE_FILE);
    List<CustomerUserRole> customerUserRoles = MAPPER.readValue(customerUserRolesFile, new TypeReference<>() {});
    customerUserRoleRepository.deleteAll(customerUserRoles);
    customerUserRolesFile.delete();

    File customerUsersFile = new File(CUSTOMERS_USER_FILE);
    List<CustomerUser> customerUsers = MAPPER.readValue(customerUsersFile, new TypeReference<>() {});
    customerUserRepository.deleteAll(customerUsers);
    customerUsersFile.delete();

    File customerVodSubDomainsFile = new File(CUSTOMERS_VOD_FILE);
    List<CustomerVodSubDomain> customerVodSubDomains = MAPPER.readValue(customerVodSubDomainsFile, new TypeReference<>() {});
    customerVobSubDomainRepository.deleteAll(customerVodSubDomains);
    customerVodSubDomainsFile.delete();

    File customerEmailDomainsFile = new File(CUSTOMERS_EMAIL_DOMAIN_FILE);
    List<CustomerEmailDomain> customerEmailDomains = MAPPER.readValue(customerEmailDomainsFile, new TypeReference<>() {});
    customerEmailDomainRepository.deleteAll(customerEmailDomains);
    customerEmailDomainsFile.delete();

    File customersFile = new File(CUSTOMERS_FILE);
    List<Customer> customers = MAPPER.readValue(customersFile, new TypeReference<>() {});
    customerRepository.deleteAll(customers);
    customersFile.delete();

    File usersFile = new File(USERS_FILE);
    List<User> users = MAPPER.readValue(usersFile, new TypeReference<>() {});
    userRepository.deleteAll(users);
    usersFile.delete();
  }
}
