CREATE DATABASE  IF NOT EXISTS `ssafy_trip_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssafy_trip_final`;
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
-- Table structure for table `attraction_comment`
--

DROP TABLE IF EXISTS `attraction_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_comment` (
  `comment_no` varchar(45) NOT NULL,
  `content` varchar(100) NOT NULL,
  `register_time` timestamp NOT NULL,
  `article_no` int NOT NULL,
  `user_id` varchar(30) NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `fk_attraction_comment_attraction_review1_idx` (`article_no`),
  KEY `fk_attraction_comment_member1_idx` (`user_id`),
  CONSTRAINT `fk_attraction_comment_attraction_review1` FOREIGN KEY (`article_no`) REFERENCES `attraction_review` (`review_no`),
  CONSTRAINT `fk_attraction_comment_member1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_description`
--

DROP TABLE IF EXISTS `attraction_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_description` (
  `id` int NOT NULL,
  `homepage` text,
  `overview` text,
  PRIMARY KEY (`id`),
  CONSTRAINT `d_to_i_i` FOREIGN KEY (`id`) REFERENCES `attraction_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_info`
--

DROP TABLE IF EXISTS `attraction_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_info` (
  `id` int NOT NULL,
  `type_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `tel` varchar(200) DEFAULT NULL,
  `first_image` varchar(200) DEFAULT NULL,
  `latitude` varchar(200) DEFAULT NULL,
  `longitude` varchar(200) DEFAULT NULL,
  `sido_code` int DEFAULT NULL,
  `gugun_code` int DEFAULT NULL,
  `like` int DEFAULT NULL,
  `addr1` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_info_check`
--

DROP TABLE IF EXISTS `attraction_info_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_info_check` (
  `user_id` varchar(30) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`user_id`,`id`),
  KEY `check_to_info_id_idx` (`id`),
  CONSTRAINT `check_to_info_id` FOREIGN KEY (`id`) REFERENCES `attraction_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `check_to_member_user` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_review`
--

DROP TABLE IF EXISTS `attraction_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_review` (
  `review_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `review_title` varchar(50) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `hit` int DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT NULL,
  `review_like` int DEFAULT NULL,
  `attraction_id` int NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `fk_attraction_review_member1_idx` (`user_id`),
  KEY `fk_attraction_review_attraction_info1_idx` (`attraction_id`),
  CONSTRAINT `fk_attraction_review_attraction_info1` FOREIGN KEY (`attraction_id`) REFERENCES `attraction_info` (`id`),
  CONSTRAINT `fk_attraction_review_member1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attraction_review_check`
--

DROP TABLE IF EXISTS `attraction_review_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction_review_check` (
  `user_id` varchar(30) NOT NULL,
  `review_no` int NOT NULL,
  PRIMARY KEY (`user_id`,`review_no`),
  KEY `c_t_r_idx` (`review_no`),
  CONSTRAINT `c_t_m` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_t_r` FOREIGN KEY (`review_no`) REFERENCES `attraction_review` (`review_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `free_board`
--

DROP TABLE IF EXISTS `free_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `free_board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `register_time` timestamp NOT NULL,
  `modified_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`article_no`),
  UNIQUE KEY `article_no_UNIQUE` (`article_no`),
  KEY `fk_free_board_member1_idx` (`user_id`),
  CONSTRAINT `member_key` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `free_comment`
--

DROP TABLE IF EXISTS `free_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `free_comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `article_no` int NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `content` varchar(100) NOT NULL,
  `register_time` timestamp NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `fk_free_comment_free_board1_idx` (`article_no`),
  KEY `fk_free_comment_member1_idx` (`user_id`),
  CONSTRAINT `fk_free_comment_free_board1` FOREIGN KEY (`article_no`) REFERENCES `free_board` (`article_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_free_comment_member1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gugun`
--

DROP TABLE IF EXISTS `gugun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gugun` (
  `gugun_code` int NOT NULL,
  `gugun_name` varchar(30) DEFAULT NULL,
  `sido_code` int NOT NULL,
  PRIMARY KEY (`gugun_code`,`sido_code`),
  KEY `gugun_to_sido_sido_code_fk_idx` (`sido_code`),
  CONSTRAINT `gugun_to_sido_sido_code_fk` FOREIGN KEY (`sido_code`) REFERENCES `sido` (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `sido`
--

DROP TABLE IF EXISTS `sido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sido` (
  `sido_code` int NOT NULL,
  `sido_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `travel_comment`
--

DROP TABLE IF EXISTS `travel_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `article_no` int NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `content` varchar(100) NOT NULL,
  `register_time` timestamp NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `fk_travel_course_comment_travel_course1_idx` (`article_no`),
  KEY `fk_travel_course_comment_member1_idx` (`user_id`),
  CONSTRAINT `fk_travel_course_comment_member1` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_travel_course_comment_travel_course1` FOREIGN KEY (`article_no`) REFERENCES `travel_course` (`article_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `travel_course_check`
--

DROP TABLE IF EXISTS `travel_course_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_course_check` (
  `article_no` int NOT NULL,
  `user_id` varchar(30) NOT NULL,
  PRIMARY KEY (`article_no`,`user_id`),
  KEY `c_t_m_u_idx` (`user_id`),
  CONSTRAINT `c_t_m_u` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_to_t_ai` FOREIGN KEY (`article_no`) REFERENCES `travel_course` (`article_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23 16:06:22
