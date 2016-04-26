-- Dumping database structure for jsearch
DROP DATABASE `jsearch`;
CREATE DATABASE IF NOT EXISTS `jsearch` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jsearch`;


-- Dumping structure for table users
CREATE TABLE IF NOT EXISTS `users` (
  `username`      varchar(255)   NOT NULL,
  `firstname`     varchar(60)   NOT NULL,
  `lastname`      varchar(60)   NOT NULL,
  `password`      varchar(60)  NOT NULL,
  `city`   		  varchar(90) 	NOT NULL,
  `state`   	  varchar(60) 	NOT NULL,
  `zipcode`	  	  int(5)		NOT NULL,
  `interest`   	  int(2) 		NOT NULL,
  `tagline`   	  varchar(60) 	NOT NULL,
  `isRecruiter`	  int(1)		NOT NULL DEFAULT 0,
  `isExperienced` int(1)		NOT NULL DEFAULT 0,
  `latitude`      DECIMAL(10, 8) NOT NULL,
  `longitude`     DECIMAL(11, 8) NOT NULL,
  `enabled`       tinyint(4)    NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping structure for table user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id`  int(11)       NOT NULL AUTO_INCREMENT,
  `username`      varchar(45)   NOT NULL,
  `ROLE`          varchar(45)   NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping structure for table jobs
CREATE TABLE IF NOT EXISTS `jobs` (
  `job_id`      int(11)      NOT NULL AUTO_INCREMENT,
  `company`     varchar(45)  NOT NULL,
  `position`    varchar(45)  NOT NULL,
  `recruiter`   varchar(60)  NOT NULL,
  `description` blob   		 NOT NULL,
  `address`     varchar(255) NOT NULL,
  `industry`   	int(2) 		 NOT NULL,
  `tagline`   	varchar(60)  NOT NULL,
  `latitude`    DECIMAL(10, 8) NOT NULL,
  `longitude`   DECIMAL(11, 8) NOT NULL,
  `verified`   int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`job_id`),
  KEY `fk_recruiter_idx` (`recruiter`),
  CONSTRAINT `fk_recruiter` FOREIGN KEY (`recruiter`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Dumping structure for table jobs
CREATE TABLE IF NOT EXISTS `experiences` (
  `experience_id`  int(11)  NOT NULL AUTO_INCREMENT,
  `company`     varchar(45) NOT NULL,
  `position`    varchar(45) NOT NULL,
  `username`   	varchar(60) NOT NULL,
  `description` blob   		NOT NULL,
  `start_date`  date 		NOT NULL,
  `end_date`    date 				,
  `city`   		varchar(45) NOT NULL,
  `state`   	varchar(45) NOT NULL,
  PRIMARY KEY (`experience_id`),
  KEY `fk_experience_idx` (`username`),
  CONSTRAINT `fk_experiences` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Dumping structure for table feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `name`          varchar(60)     NOT NULL,
  `location`      varchar(128)    NOT NULL,
  `feedback`      blob            NOT NULL,
  `email`         varchar(60)     NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping structure for table dynamic_urls
CREATE TABLE IF NOT EXISTS `dynamic_urls` (
	`url`		varchar(255)	NOT NULL,
	`username`	varchar(255)	NOT NULL,
	`type`		int(1)			NOT NULL,
	PRIMARY KEY (`url`),
	KEY `fk_urls_idx` (`username`),
	CONSTRAINT `fk_urls` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping structure for table geo_assist
CREATE TABLE IF NOT EXISTS `geo_assist` (
	`address`		varchar(255)	NOT NULL,
  	`latitude`    DECIMAL(10, 8) NOT NULL,
  	`longitude`   DECIMAL(11, 8) NOT NULL,
	PRIMARY KEY (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
INSERT INTO `users` (`username`, `firstname`, `lastname`, `city`, `state`, `zipcode`, `interest`, `tagline`, `isRecruiter`, `isExperienced`, `latitude`, `longitude`, `password`, `enabled`) VALUES
 ('admin@email.com', 'Master', 'Commander', 'fairfax', 'va', 22033, 0, 'The Admin', 0, 0, 38.859707, -77.399765, '$2a$10$r1AJfVgCQLJ9bizMnDHhX.zucyPHYNKPEpq3BUa0XzCl6JfWLI.i.', 1),
 ('user@email.com', 'Not', 'Admin', 'fairfax', 'va', 22033, 0, 'The User',  0, 0, 38.859707, -77.399765, '$2a$10$r1AJfVgCQLJ9bizMnDHhX.zucyPHYNKPEpq3BUa0XzCl6JfWLI.i.', 1),
 ('unverified@email.com', 'Not', 'Admin', 'fairfax', 'va', 22033, 0, 'The User',  0, 0, 38.859707, -77.399765, '$2a$10$r1AJfVgCQLJ9bizMnDHhX.zucyPHYNKPEpq3BUa0XzCl6JfWLI.i.', 1);

INSERT INTO `user_roles` (`user_role_id`, `username`, `ROLE`) VALUES
 (1, 'admin@email.com', 'ROLE_ADMIN'),
 (2, 'user@email.com', 'ROLE_USER'),
 (3, 'unverified@email.com', 'ROLE_UNVERIFIED');
