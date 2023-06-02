ALTER TABLE `employees` MODIFY `status` bit(1) DEFAULT 1;
INSERT INTO `employees`(first_name, last_name, email, password, status, type)
VALUES ("Oana", "Banana", "oana@banana.com", "$2a$10$x.ApKK0ts9K.8JgjqBi0GejnFqCSdeD2CeuFIH.fXu5re6izr9zmW", 1, "EMPLOYEE");
