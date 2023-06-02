SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `forms`;
DROP TABLE IF EXISTS `answers`;
DROP TABLE IF EXISTS `options`;
DROP TABLE IF EXISTS `questions`;

CREATE TABLE `forms`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) NOT NULL,
    `description` varchar(500) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE `answers`
(
    `id`          bigint(20)    NOT NULL AUTO_INCREMENT,
    `value`       varchar(4000) NOT NULL,
    `question_id` bigint(20)    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE `options`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `value`       varchar(255) NOT NULL,
    `question_id` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE `questions`
(
    `id`          bigint(20)    NOT NULL AUTO_INCREMENT,
    `value`       varchar(4000) NOT NULL,
    `answer_type` varchar(255)  NOT NULL,
    `form_id`     bigint(20)    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`form_id`) REFERENCES `forms` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;
