-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ssafy_trip_final
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `user_nickname` varchar(20) NOT NULL,
  `join_date` timestamp NOT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `refresh_token` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('','123','','','','','2024-05-17 04:27:50',NULL,NULL),('1','12345','12345','12345','12345','12345','2024-05-17 04:31:47',NULL,NULL),('123','123','123','123','123','123','2024-05-17 04:30:37',NULL,NULL),('12345','12345','12345','12345','12345','12345','2024-05-17 04:30:49',NULL,NULL),('admin','관리자','1234','ad@naver.com','01032855012','ad','2024-05-16 01:40:26','2024-05-16 01:40:26',NULL),('mj1584','김민주','1234','mj1584.mk@gmail.com','01032855012','computer','2024-05-16 05:24:34','2024-05-17 05:55:27',NULL),('mz158457','김민수','1234','mj1584.mk@gmail.com','01012345678','computer','2024-05-17 01:24:37',NULL,NULL),('mz1584571','김민수','1234','mj1584.mk@gmail.com','01012345678','computer','2024-05-17 01:37:36',NULL,NULL),('ssafy','이승원','1234','wlk2560@naver.com','01032855012','cute','2024-05-16 01:40:04','2024-05-16 01:40:04','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzE2MjgwMzY0LCJleHAiOjE3MTYyODAzNzksInVzZXJJZCI6InNzYWZ5In0.NiHemMqQPqLd2pp3Wv5Mp0_Hay5WcljOjS8YM01HS5w');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 17:38:22
