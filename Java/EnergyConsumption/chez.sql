CREATE DATABASE  IF NOT EXISTS `chez` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `chez`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: chez
-- ------------------------------------------------------
-- Server version	5.5.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `paid_bills`
--

DROP TABLE IF EXISTS `paid_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paid_bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `sum_without_tax` decimal(10,2) DEFAULT NULL,
  `sum_with_tax` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_fk_idx` (`userID`),
  CONSTRAINT `paid_fk` FOREIGN KEY (`userID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='Paid bills of customers.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paid_bills`
--

LOCK TABLES `paid_bills` WRITE;
/*!40000 ALTER TABLE `paid_bills` DISABLE KEYS */;
INSERT INTO `paid_bills` VALUES (1,1,5,2016,123.05,147.66),(2,1,4,2016,49.99,59.99),(3,2,6,2016,100.00,120.00),(4,2,7,2016,53.00,64.00),(5,3,1,2016,20.00,30.00),(6,3,2,2016,25.00,35.00),(7,3,3,2016,55.00,65.00),(8,3,4,2016,60.00,70.00),(9,4,8,2016,100.00,120.00),(10,4,9,2016,200.00,299.00),(11,4,1,2015,155.00,185.00),(12,4,2,2015,123.00,165.00),(13,5,5,2015,200.00,300.00),(14,5,5,2016,125.00,165.00),(15,6,6,2016,123.00,155.00),(16,6,6,2015,100.00,120.00),(18,1,1,2016,10.19,12.23),(19,1,2,2016,15.69,18.83),(20,1,3,2016,5.02,6.02),(21,1,6,2016,60.08,72.10),(22,1,7,2016,69.99,83.99),(37,1,1,2016,123.00,147.60),(45,1,1,1,1.00,1.20);
/*!40000 ALTER TABLE `paid_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unpaid_bills`
--

DROP TABLE IF EXISTS `unpaid_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unpaid_bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(4) DEFAULT NULL,
  `sum_without_tax` decimal(10,2) DEFAULT NULL,
  `sum_with_tax` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `paid_fk_idx` (`id`),
  KEY `unpaid_fk_idx` (`userID`),
  CONSTRAINT `unpaid_fk` FOREIGN KEY (`userID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='Customers unpaid bils';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unpaid_bills`
--

LOCK TABLES `unpaid_bills` WRITE;
/*!40000 ALTER TABLE `unpaid_bills` DISABLE KEYS */;
INSERT INTO `unpaid_bills` VALUES (1,1,3,2016,20.21,24.25),(2,2,1,2016,20.00,24.00),(3,3,1,2015,15.00,19.00),(4,3,2,2015,123.00,155.00),(5,4,1,2016,30.00,40.00),(6,4,2,2016,66.00,77.00),(7,5,1,2016,123.00,155.00),(8,5,6,2016,11.00,14.00),(9,6,5,2015,156.00,188.00),(12,1,2,2015,55.49,66.59),(13,1,3,2015,46.12,55.35),(14,1,4,2015,65.35,78.42),(15,1,5,2015,123.46,148.15),(19,1,1,2015,125.35,150.42);
/*!40000 ALTER TABLE `unpaid_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customerID_UNIQUE` (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Customers of Chez';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,111111,'admin','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Admin','Admin','admin@abv.bg'),(2,222222,'pesho','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Pesho','Peshov','peshov@abv.bg'),(3,33333,'gosho','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Gosho','Goshov','goshov@abv.bg'),(4,444444,'misho','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Misho','Mishov','mishov@abv.bg'),(5,55555,'minka','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Minka','Minkova','minkova@abv.bg'),(6,666666,'misha','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Misha','Mishovich','mishovich@abv.bg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-13 18:10:26
