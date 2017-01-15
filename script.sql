DROP DATABASE IF EXISTS TEST;
CREATE DATABASE TEST;
USE TEST;
DROP TABLE IF EXISTS `test`.`users`;

CREATE TABLE USERS (
	`Id` INT(8) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(25) NOT NULL,
	`Age` INT(3) NOT NULL,
	`Is_Admin` TINYINT(1) NOT NULL DEFAULT '0',
	`Created_Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User1', 10, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User12', 11, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User123', 12, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User14', 13, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User15', 14, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User16', 15, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User17', 16, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User18', 17, false);
INSERT INTO USERS(Name, Age, Is_Admin) VALUES('User19', 18, true);
COMMIT;