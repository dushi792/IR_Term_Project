CREATE USER IF NOT EXISTS 'IRWeb'@'localhost' IDENTIFIED BY 'IRWeb';
GRANT ALL PRIVILEGES ON * . * TO 'IRWeb'@'localhost';

CREATE DATABASE `irweb`;

CREATE TABLE `irweb`.`searchrecord` (
  `searchRecordID` int(11) NOT NULL AUTO_INCREMENT,
  `query` varchar(100) NOT NULL,
  `datetime` datetime NOT NULL,
  `userID` int(11) DEFAULT NULL,
  PRIMARY KEY (`searchRecordID`),
  UNIQUE KEY `searchID_UNIQUE` (`searchRecordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `irweb`.`user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;