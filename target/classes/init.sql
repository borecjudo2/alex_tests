CREATE TABLE `user` (
  `userId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `emailAddress` varchar(150) NOT NULL,
  `personId` varchar(50) NOT NULL,
  `firstName` varchar(150) DEFAULT NULL,
  `lastName` varchar(150) DEFAULT NULL,
  `isAuth0Complete` bit(1) NOT NULL DEFAULT b'0',
  `hasAcceptedInvitation` bit(1) NOT NULL DEFAULT b'0',
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`userId`),
  UNIQUE KEY `uc_user_emailAddress` (`emailAddress`),
  UNIQUE KEY `uc_user_personId` (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `roleId` tinyint unsigned NOT NULL,
  `roleName` varchar(150) NOT NULL,
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`roleId`),
  UNIQUE KEY `uc_role_roleName` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customertype` (
  `customerTypeId` tinyint unsigned NOT NULL,
  `customerTypeName` varchar(150) NOT NULL,
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerTypeId`),
  UNIQUE KEY `uc_custtype_customerTypeName` (`customerTypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer` (
  `customerId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `customerTypeId` tinyint unsigned NOT NULL,
  `customerName` varchar(150) NOT NULL,
  `auth0OrganizationId` varchar(50) DEFAULT NULL,
  `organizationName` varchar(50) NOT NULL,
  `organizationDisplayName` varchar(50) DEFAULT NULL,
  `brandingLogoUrl` varchar(250) DEFAULT NULL,
  `organizationPrimaryHexColor` char(7) DEFAULT NULL,
  `organizationPageBackgroundHexColor` char(7) DEFAULT NULL,
  `essoMode` tinyint unsigned NOT NULL DEFAULT '0',
  `primaryContactUserId` binary(16) DEFAULT NULL,
  `isAuth0Complete` bit(1) NOT NULL DEFAULT b'0',
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerId`),
  UNIQUE KEY `uc_cust_organizationName` (`organizationName`),
  UNIQUE KEY `uc_cust_auth0OrganizationId` (`auth0OrganizationId`),
  KEY `ix_cust_user` (`primaryContactUserId`),
  KEY `fk_cust_customertype` (`customerTypeId`),
  CONSTRAINT `fk_cust_customertype` FOREIGN KEY (`customerTypeId`) REFERENCES `customertype` (`customerTypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cust_user` FOREIGN KEY (`primaryContactUserId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_emaildomain` (
  `customerId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `emailDomain` varchar(150) NOT NULL,
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerId`,`emailDomain`),
  UNIQUE KEY `uc_custemail_emailDomain` (`emailDomain`),
  CONSTRAINT `fk_custemail_customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_vodsubdomain` (
  `customerId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `subDomain` varchar(50) NOT NULL,
  `auth0ClientId` varchar(50) DEFAULT NULL,
  `isAuth0Complete` bit(1) NOT NULL DEFAULT b'0',
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerId`,`subDomain`),
  UNIQUE KEY `uc_custvodsub_subDomain` (`subDomain`),
  CONSTRAINT `fk_custvod_customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_user` (
  `customerId` binary(16) NOT NULL,
  `userId` binary(16) NOT NULL,
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerId`,`userId`),
  KEY `ix_custuser_user` (`userId`),
  CONSTRAINT `fk_custuser_customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_custuser_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `customer_user_role` (
  `customerId` binary(16) NOT NULL,
  `userId` binary(16) NOT NULL,
  `roleId` tinyint unsigned NOT NULL,
  `createdUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modifiedUtc` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `versionId` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),

  PRIMARY KEY (`customerId`,`userId`,`roleId`),
  KEY `ix_custuserrole_customer` (`customerId`),
  KEY `ix_custuserrole_user` (`userId`),
  KEY `ix_custuserrole_role` (`roleId`),
  CONSTRAINT `fk_custuserrole_customer_user` FOREIGN KEY (`customerId`, `userId`) REFERENCES `customer_user` (`customerId`, `userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_custuserrole_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


