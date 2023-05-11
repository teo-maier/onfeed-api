SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `teams`;
DROP TABLE IF EXISTS `team_members`;

CREATE TABLE `teams`
(
    `id`   bigint(20)   NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;

CREATE TABLE `team_members`
(
    `team_id`     bigint(20) NOT NULL,
    `employee_id` bigint(20) NOT NULL,
    `is_manager`  boolean DEFAULT NULL,
    FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`),
    FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE = InnoDB;

INSERT INTO teams (id, name)
VALUES (1, 'Red Tigers'),
       (2, 'Blue Eagles'),
       (3, 'Green Panthers'),
       (4, 'Yellow Lions'),
       (5, 'Purple Cobras'),
       (6, 'Orange Owls'),
       (7, 'Black Hawks'),
       (8, 'White Wolves'),
       (9, 'Pink Pumas'),
       (10, 'Gold Falcons');

INSERT INTO team_members (team_id, employee_id, is_manager)
VALUES (1, 104, true),
       (1, 105, false),
       (1, 106, false),
       (2, 107, true),
       (2, 108, false),
       (3, 109, true),
       (3, 110, false),
       (3, 111, false),
       (3, 112, false),
       (4, 113, true),
       (4, 104, false);

INSERT INTO employees (id, first_name, last_name, email, password, status, type)
VALUES (104, 'Linda', 'Wilson', 'lindawilson@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW',
        1, 'EMPLOYEE'),
       (105, 'David', 'Lee', 'davidlee@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW', 1,
        'MANAGER'),
       (106, 'Karen', 'Garcia', 'karengarcia@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW',
        1, 'ADMIN'),
       (107, 'Eric', 'Wong', 'ericwong@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW', 1,
        'EMPLOYEE'),
       (108, 'Sarah', 'Kim', 'sarahkim@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW', 1,
        'MANAGER'),
       (109, 'Michael', 'Chang', 'michaelchang@example.com',
        '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW', 1, 'EMPLOYEE'),
       (110, 'Julie', 'Davis', 'juliedavis@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW',
        1, 'ADMIN'),
       (111, 'Daniel', 'Lee', 'daniellee@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW',
        1, 'EMPLOYEE'),
       (112, 'Sophia', 'Zhang', 'sophiazhang@example.com', '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW',
        1, 'MANAGER'),
       (113, 'Matthew', 'Nguyen', 'matthewnguyen@example.com',
        '$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW', 1, 'EMPLOYEE');


