-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
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
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (100,2,'Queen','Queen',0,0,100,132,1),(101,2,'Full','Full',0,0,101,123,1),(102,1,'Queen','',1,0,102,138,1),(103,2,'Queen','Queen',1,0,103,133,1),(104,1,'Full','',1,0,104,134,1),(105,1,'Queen','',1,1,105,143,1),(106,2,'Full','Full',1,0,106,120,1),(107,1,'King','',0,0,107,119,1),(108,2,'Queen','Full',0,1,108,119,1),(109,2,'Queen','Queen',0,1,109,117,1),(110,2,'Queen','Queen',0,0,110,125,1),(111,1,'King','',0,0,111,143,1),(112,1,'Full','',1,1,112,134,1),(113,2,'King','Full',0,0,113,138,1),(114,2,'King','King',1,1,114,119,1),(115,2,'Queen','Full',0,1,115,143,1),(116,2,'King','Full',0,1,116,127,1),(117,2,'Queen','Full',0,1,117,119,1),(118,1,'King','',0,1,118,145,1),(119,1,'Full','',0,1,119,116,1),(200,1,'Full','',1,0,200,125,1),(201,2,'Queen','Full',1,1,201,128,1),(202,2,'Queen','Full',1,1,202,121,1),(203,1,'Full','',1,1,203,115,1),(204,1,'King','',0,0,204,125,1),(205,2,'Queen','King',1,1,205,141,1),(206,2,'King','Queen',0,0,206,125,1),(207,1,'Queen','',1,1,207,114,1),(208,2,'Full','Queen',1,0,208,145,1),(209,2,'Full','King',0,0,209,144,1),(210,1,'Queen','',0,0,210,128,1),(211,1,'King','',1,0,211,132,1),(212,1,'Queen','',0,1,212,135,1),(213,2,'Full','King',1,0,213,120,1),(214,2,'Queen','King',1,1,214,140,1),(215,1,'Full','',1,0,215,142,1),(216,1,'King','',0,1,216,135,1),(217,1,'Queen','',1,0,217,116,1),(218,1,'Queen','',0,1,218,115,1),(219,2,'King','Queen',0,1,219,115,1),(300,1,'King','',0,0,300,139,1),(301,1,'Full','',0,0,301,121,1),(302,1,'Queen','',0,0,302,131,1),(303,1,'King','',1,1,303,142,1),(304,2,'Full','King',0,0,304,113,1),(305,2,'King','Full',0,1,305,120,1),(306,2,'Queen','Queen',0,0,306,143,1),(307,2,'King','Queen',1,1,307,141,1),(308,2,'King','Queen',0,0,308,144,1),(309,2,'Full','Full',0,0,309,120,1),(310,2,'Queen','King',1,0,310,123,1),(311,2,'Queen','Full',1,0,311,130,1),(312,1,'Full','',0,1,312,136,1),(313,1,'Queen','',0,0,313,113,1),(314,2,'Full','Full',1,0,314,124,1),(315,2,'Queen','Queen',0,0,315,136,1),(316,1,'King','',0,1,316,126,1),(317,2,'Queen','Full',1,0,317,123,1),(318,2,'Queen','Queen',0,1,318,130,1),(319,1,'King','',0,1,319,129,1),(400,2,'Full','Full',0,0,400,116,1),(401,1,'Full','',1,1,401,121,1),(402,1,'Full','',1,0,402,137,1),(403,2,'King','Full',0,0,403,139,1),(404,2,'King','Full',0,0,404,119,1),(405,1,'Full','',0,0,405,126,1),(406,2,'Full','Queen',0,0,406,126,1),(407,1,'Queen','',0,1,407,119,1),(408,1,'Queen','',1,0,408,121,1),(409,2,'King','Full',1,0,409,130,1),(410,1,'Queen','',1,1,410,116,1),(411,1,'Full','',0,1,411,130,1),(412,1,'King','',0,0,412,135,1),(413,1,'Full','',1,0,413,140,1),(414,2,'Queen','Queen',1,1,414,138,1),(415,2,'King','Full',1,0,415,125,1),(416,2,'Queen','Full',1,0,416,126,1),(417,2,'Full','Full',0,1,417,131,1),(418,2,'Full','King',1,0,418,131,1),(419,1,'King','',1,0,419,119,1);
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

-- Dump completed on 2022-09-28 14:18:48
