SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `options_answer`;


CREATE TABLE `options_answer`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT,
    `value`     varchar(255) NOT NULL,
    `answer_id` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`answer_id`) REFERENCES `answers` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;
