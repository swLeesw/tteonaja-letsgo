-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafy_trip_final
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
-- Table structure for table `travel_course`
--

DROP TABLE IF EXISTS `travel_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_course` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `content` text NOT NULL,
  `hit` int NOT NULL,
  `register_time` timestamp NOT NULL,
  `course_like` int NOT NULL,
  `travel_list` text,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`article_no`),
  UNIQUE KEY `article_no_UNIQUE` (`article_no`),
  KEY `fk_travel_course_member1_idx` (`user_id`),
  CONSTRAINT `fk_travel_course_member1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_course`
--

LOCK TABLES `travel_course` WRITE;
/*!40000 ALTER TABLE `travel_course` DISABLE KEYS */;
INSERT INTO `travel_course` VALUES (3,'ssafy','여행계획수정','여행 계획을 수정해봤습니다.',3,'2024-05-22 13:47:23',0,'json이 string으로 변환되어서 들어갈  수정된 내용.','2024-05-22 13:48:44'),(4,'ssafy','여행계획2','여행 계획을 짜봤습니다.',0,'2024-05-22 14:03:23',0,'json이 string으로 변환되어서 들어갈 내용.',NULL),(6,'ssafy','여행계획4','여행 계획을 짜봤습니다.',0,'2024-05-22 14:03:30',0,'json이 string으로 변환되어서 들어갈 내용.',NULL),(7,'ssafy','여행계획5','여행 계획을 짜봤습니다.',0,'2024-05-22 14:03:32',0,'json이 string으로 변환되어서 들어갈 내용.',NULL),(8,'ssafy','여행계획6','여행 계획을 짜봤습니다.',0,'2024-05-22 14:03:34',0,'json이 string으로 변환되어서 들어갈 내용.',NULL),(9,'ssafy','여행계획9','여행 계획을 짜봤습니다.',0,'2024-05-22 14:32:33',0,'json이 string으로 변환되어서 들어갈 내용.',NULL);
/*!40000 ALTER TABLE `travel_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23  2:32:07
