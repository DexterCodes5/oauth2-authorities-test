DROP DATABASE IF EXISTS `oauth2-authorities-demo`;

CREATE DATABASE `oauth2-authorities-demo`;

USE `oauth2-authorities-demo`; 

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `enabled` tinyint NOT NULL,
  `provider` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
);

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('dexter', '{noop}test123', 1, 'LOCAL'),
('john', '{noop}test123', 1, 'LOCAL');

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('dexter','ROLE_CLIENT'),
('dexter','ROLE_ADMIN'),
('john','ROLE_CLIENT');