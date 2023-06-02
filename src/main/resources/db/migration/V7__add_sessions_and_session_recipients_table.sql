SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sessions`;
DROP TABLE IF EXISTS `session_recipients`;

CREATE TABLE `sessions`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `title`         varchar(255)        DEFAULT NULL,
    `description`   varchar(500)        DEFAULT NULL,
    `form_id`       bigint(20)          DEFAULT NULL,
    `creator_id`    bigint(20)          DEFAULT NULL,
    `is_anonymous`  bit(1)     NOT NULL DEFAULT 0,
    `is_suggestion` bit(1)     NOT NULL DEFAULT 0,
    `is_draft`      bit(1)     NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`form_id`) REFERENCES `forms` (`id`),
    FOREIGN KEY (`creator_id`) REFERENCES `employees` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE `session_recipients`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `session_id`   bigint(20) NOT NULL,
    `employee_id`  bigint(20) NOT NULL,
    `is_completed` bit(1)     NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`),
    FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

ALTER TABLE `answers`
    ADD `employee_id` bigint(20) NOT NULL;
ALTER TABLE `answers`
    ADD CONSTRAINT fk_employee_id FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);

