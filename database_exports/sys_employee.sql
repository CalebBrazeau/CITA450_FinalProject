-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: sys
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL,
  `emplyee_fName` varchar(20) NOT NULL,
  `emplyee_lName` varchar(20) NOT NULL,
  `job_Id` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Caleb','Brazeau',1,'BrazeauDesk','Rooms'),(2,'Chino','Beach',2,'BeachClean','Mop'),(3,'Ricardo','Gibson',3,'GibsonRepair','Hammer'),(4,'Casper','Brazeau',2,'BrazeauClean','Janitor');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `room_id` int NOT NULL,
  `number_of_beds` int NOT NULL,
  `bed_1_size` varchar(45) DEFAULT NULL,
  `bed_2_size` varchar(45) DEFAULT NULL,
  `is_handicap_accessible` tinyint DEFAULT NULL,
  `has_bathtub` tinyint DEFAULT NULL,
  `room_service_id` int DEFAULT NULL,
  `cost_per_night` float DEFAULT NULL,
  `is_available` tinyint NOT NULL,
  `is_clean` tinyint NOT NULL,
  `customer_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (100,1,'Queen','',0,1,100,141,1,1,NULL),(101,1,'King','',0,1,101,114,0,1,'11'),(102,1,'Queen','',1,0,102,142,1,1,NULL),(103,2,'Full','Queen',0,0,103,133,1,1,NULL),(104,1,'Queen','',0,1,104,134,1,1,NULL),(105,2,'Full','Queen',0,0,105,136,1,1,NULL),(106,1,'Full','',0,1,106,123,1,1,NULL),(107,2,'King','Queen',1,0,107,115,1,1,NULL),(108,1,'King','',1,0,108,145,1,1,NULL),(109,1,'Queen','',0,0,109,115,1,1,NULL),(110,1,'King','',0,0,110,123,1,1,NULL),(111,2,'King','Queen',1,0,111,120,1,1,NULL),(112,1,'Full','',1,0,112,130,1,1,NULL),(113,1,'Queen','',1,0,113,121,1,1,NULL),(114,1,'Queen','',1,0,114,141,1,1,NULL),(115,1,'King','',0,1,115,129,1,1,NULL),(116,1,'King','',1,0,116,124,1,1,NULL),(117,1,'Queen','',1,1,117,124,1,1,NULL),(118,1,'King','',0,0,118,113,1,1,NULL),(119,2,'Queen','King',0,1,119,118,1,1,NULL),(200,2,'Queen','King',0,1,200,114,1,1,NULL),(201,1,'Full','',0,1,201,118,1,1,NULL),(202,1,'Queen','',0,1,202,116,1,1,NULL),(203,2,'Full','King',1,0,203,129,1,1,NULL),(204,1,'King','',0,0,204,118,1,1,NULL),(205,2,'Queen','Queen',1,0,205,141,1,1,NULL),(206,1,'Queen','',1,0,206,138,1,1,NULL),(207,2,'Queen','Queen',0,1,207,131,1,1,NULL),(208,1,'Queen','',0,1,208,131,1,1,NULL),(209,1,'Queen','',0,0,209,124,1,1,NULL),(210,2,'Queen','Queen',0,1,210,144,1,1,NULL),(211,2,'King','Full',0,0,211,136,1,1,NULL),(212,1,'Full','',0,1,212,119,1,1,NULL),(213,2,'King','King',1,1,213,126,1,1,NULL),(214,2,'Queen','Full',0,1,214,133,1,1,NULL),(215,1,'King','',0,1,215,124,1,1,NULL),(216,1,'Queen','',0,1,216,139,1,1,NULL),(217,1,'King','',1,0,217,117,1,1,NULL),(218,1,'King','',0,0,218,125,1,1,NULL),(219,1,'Queen','',1,0,219,123,1,1,NULL),(300,2,'Full','Queen',0,0,300,119,1,1,NULL),(301,1,'Queen','',1,1,301,113,1,1,NULL),(302,2,'Queen','King',1,0,302,134,1,1,NULL),(303,1,'Full','',1,0,303,131,1,1,NULL),(304,1,'Full','',1,1,304,121,1,1,NULL),(305,2,'Full','Full',0,1,305,142,1,1,NULL),(306,2,'Full','King',0,0,306,115,1,1,NULL),(307,2,'Full','King',0,0,307,145,1,1,NULL),(308,2,'Queen','King',1,1,308,135,1,1,NULL),(309,2,'King','Queen',0,1,309,114,1,1,NULL),(310,2,'Full','Queen',0,0,310,138,1,1,NULL),(311,1,'Full','',0,0,311,128,1,1,NULL),(312,1,'King','',1,1,312,117,1,1,NULL),(313,1,'King','',1,1,313,136,1,1,NULL),(314,2,'Queen','Queen',1,0,314,134,1,1,NULL),(315,1,'King','',1,1,315,129,1,1,NULL),(316,1,'Queen','',1,1,316,138,1,1,NULL),(317,2,'Queen','Full',0,1,317,142,1,1,NULL),(318,1,'Queen','',0,1,318,121,1,1,NULL),(319,2,'Queen','King',1,0,319,120,1,1,NULL),(400,1,'King','',0,1,400,138,1,1,NULL),(401,2,'Queen','King',0,0,401,115,1,1,NULL),(402,1,'King','',1,0,402,138,1,1,NULL),(403,1,'King','',0,1,403,135,1,1,NULL),(404,1,'Full','',1,0,404,120,1,1,NULL),(405,2,'Full','Queen',0,1,405,125,1,1,NULL),(406,2,'King','Queen',1,0,406,124,1,1,NULL),(407,2,'King','King',0,1,407,114,1,1,NULL),(408,1,'Full','',0,1,408,136,1,1,NULL),(409,1,'King','',1,1,409,139,1,1,NULL),(410,2,'Full','Queen',1,0,410,143,1,1,NULL),(411,1,'Full','',0,0,411,138,1,1,NULL),(412,1,'King','',1,1,412,130,1,1,NULL),(413,1,'Full','',1,0,413,113,1,1,NULL),(414,1,'Queen','',1,1,414,135,1,1,NULL),(415,2,'Full','Queen',0,1,415,136,1,1,NULL),(416,1,'King','',0,1,416,123,1,1,NULL),(417,1,'Queen','',0,0,417,144,1,1,NULL),(418,1,'Full','',0,1,418,120,1,1,NULL),(419,2,'Full','Full',0,1,419,131,1,1,NULL);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-17 12:00:52
