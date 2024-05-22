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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attraction_review`
--

LOCK TABLES `attraction_review` WRITE;
/*!40000 ALTER TABLE `attraction_review` DISABLE KEYS */;
INSERT INTO `attraction_review` VALUES (1,'ssafy','안녕하세요','goodgood!!베리베리맛있어요',0,'2024-05-21 00:15:07',0,125266),(2,'ssafy','반가워요','goodgood!!!!! 잇츠 베리베리맛있어요',0,'2024-05-21 00:15:19',0,125266),(3,'ssafy','정말 재밌는 장소','진짜 재밌게 놀았습니다. 다음에도 또 오고싶어요!',0,'2024-05-21 06:30:18',0,125266),(4,'ssafy','안반가워요','goodgood!!!!! 잇츠 베리베리맛있어요',0,'2024-05-21 07:17:54',0,125266),(6,'ssafy','ㅁㄴㄹㅇㄴㅁ','ㄴㅇㅁㅁㄴㅇ',0,'2024-05-21 08:34:09',0,125266),(7,'ssafy','청태산은 어떤 곳일까?','프로젝트를 하며 항상 봐왔던 그곳.. 하지만 정작 가본 적은 없는데....',0,'2024-05-21 08:35:50',0,125266),(8,'wlk256032','리뷰를 써볼까?','응 써볼게!',0,'2024-05-21 08:50:48',0,125266),(14,'ssafy','묘가','음...',0,'2024-05-22 08:44:21',0,3057803),(15,'ssafy','등촌인 줄 알고 갔는데','동촌이었요. 칼국수를 못 먹고 가네요 ㅜㅜ',0,'2024-05-22 12:14:56',1,3058341),(16,'ssafy','조선백자는','무엇일까요?',0,'2024-05-22 13:37:34',1,125581);
/*!40000 ALTER TABLE `attraction_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23  2:32:08
