CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8;
CREATE TABLE `test`.`User` ( `id` INT(8) NOT NULL AUTO_INCREMENT ,
`name` VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
 `age` INT(8) NOT NULL ,
  `isAdmin` BIT(1) NOT NULL DEFAULT false,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY (`id`)) ENGINE = InnoDB;

INSERT INTO `test`.`user` (`NAME`, `AGE`) VALUES ('Maks', '24');
INSERT INTO `test`.`user` (`NAME`, `AGE`) VALUES ('Oleg', '17');
INSERT INTO `test`.`user` (`NAME`, `AGE`) VALUES ('UserWithoutName', '20');