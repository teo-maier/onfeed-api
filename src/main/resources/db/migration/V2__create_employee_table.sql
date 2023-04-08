SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT,
    `first_name`        varchar(255) DEFAULT NULL,
    `last_name`         varchar(255) DEFAULT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `password`          varchar(255) DEFAULT NULL,
    `status`            BOOL         DEFAULT NULL,
    `type`              varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;
