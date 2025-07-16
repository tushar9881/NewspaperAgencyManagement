-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: newspaper_agency
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK8ahhk8vqegfrt6pd1p9i03aej` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `full_address` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `sector_zone` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKj8dlm21j202cadsbfkoem0s58` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('456 New St, City','2025-05-31','Zone A',1),('123 Main St','2025-05-31','Zone A',5),('123 Main St','2025-06-02','Zone 5',8),('XYZ road pune',NULL,'North Zone',20),('afsdfr','2025-07-07','Zone A',25),('123 Main St','2025-06-02','Zone 5',28);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_delivery_log`
--

DROP TABLE IF EXISTS `daily_delivery_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_delivery_log` (
  `id` int NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `delivery_executive_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfmm3366i6l3grnnueifcqewpb` (`delivery_executive_id`),
  CONSTRAINT `FKfmm3366i6l3grnnueifcqewpb` FOREIGN KEY (`delivery_executive_id`) REFERENCES `delivery_executive` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_delivery_log`
--

LOCK TABLES `daily_delivery_log` WRITE;
/*!40000 ALTER TABLE `daily_delivery_log` DISABLE KEYS */;
INSERT INTO `daily_delivery_log` VALUES (1,'2025-06-14',10),(2,'2025-06-14',11),(52,'2025-06-15',10),(54,'2025-06-15',11),(102,'2025-06-25',11),(103,'2025-06-25',10),(153,'2025-06-26',11),(154,'2025-06-26',10),(202,'2025-07-08',10),(252,'2025-07-08',11),(302,'2025-07-08',17),(352,'2025-07-08',19),(402,'2025-07-09',10),(452,'2025-07-10',17);
/*!40000 ALTER TABLE `daily_delivery_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_delivery_log_seq`
--

DROP TABLE IF EXISTS `daily_delivery_log_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_delivery_log_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_delivery_log_seq`
--

LOCK TABLES `daily_delivery_log_seq` WRITE;
/*!40000 ALTER TABLE `daily_delivery_log_seq` DISABLE KEYS */;
INSERT INTO `daily_delivery_log_seq` VALUES (551);
/*!40000 ALTER TABLE `daily_delivery_log_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `delivered` bit(1) NOT NULL,
  `delivery_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `customer_user_id` int DEFAULT NULL,
  `daily_log_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcv4s1bisv4t6a6wg3h7bfrogh` (`customer_user_id`),
  KEY `FKjxj8jfoq833l1wx624nd8moea` (`daily_log_id`),
  CONSTRAINT `FKcv4s1bisv4t6a6wg3h7bfrogh` FOREIGN KEY (`customer_user_id`) REFERENCES `customer` (`user_id`),
  CONSTRAINT `FKjxj8jfoq833l1wx624nd8moea` FOREIGN KEY (`daily_log_id`) REFERENCES `daily_delivery_log` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,'123 Main St',_binary '','2025-06-14 09:21:28.253718','JD',5,1),(2,'456 New St, City',_binary '','2025-06-14 09:22:43.162187','newUser123',1,2),(52,'456 New St, City',_binary '','2025-06-15 08:04:03.025735','newUser123',1,52),(103,'123 Main St',_binary '','2025-06-15 13:29:22.951029','john_doe',8,52),(104,'123 Main St',_binary '','2025-06-15 13:29:26.518328','john_doe',8,54),(152,'123 Main St',_binary '','2025-06-25 17:43:41.823603','john_doe',8,102),(153,'456 New St, City',_binary '','2025-06-25 17:43:55.004640','newUser123',1,102),(154,'456 New St, City',_binary '','2025-06-25 17:45:41.588912','newUser123',1,102),(155,'456 New St, City',_binary '','2025-06-25 17:45:45.984801','newUser123',1,102),(156,'123 Main St',_binary '','2025-06-25 17:46:03.013342','JD',5,102),(157,'123 Main St',_binary '','2025-06-25 17:46:09.754181','john_doe',8,102),(158,'123 Main St',_binary '','2025-06-25 17:47:07.697041','john_doe',8,103),(203,'123 Main St',_binary '','2025-06-26 10:20:45.749563','JD',5,153),(204,'456 New St, City',_binary '','2025-06-26 10:21:15.254336','newUser123',1,154),(252,'456 New St, City',_binary '','2025-07-08 17:40:15.779938','new one',1,202),(352,'123 Main St',_binary '','2025-07-08 21:04:57.676558','Jonny Doe Shinde',5,252),(402,'123 Main St',_binary '','2025-07-08 21:10:33.391502','john_doe',8,302),(403,'XYZ road pune',_binary '','2025-07-08 21:11:11.374851','New user',20,352),(452,'afsdfr',_binary '','2025-07-08 21:23:14.930142','ABC',25,302),(453,'123 Main St',_binary '','2025-07-08 21:23:19.055246','john_doe',28,302),(502,'123 Main St',_binary '','2025-07-09 09:06:53.325449','Jonny Doe Shinde',5,402),(503,'123 Main St',_binary '','2025-07-09 09:07:02.201926','john_doe',28,402),(552,'123 Main St',_binary '','2025-07-09 09:33:09.202128','john_doe',8,402),(602,'XYZ road pune',_binary '','2025-07-09 10:03:22.527505','New user',20,402),(603,'123 Main St',_binary '','2025-07-09 10:03:44.933861','john_doe',28,402),(604,'123 Main St',_binary '','2025-07-09 10:03:57.520768','Jonny Doe Shinde',5,402),(605,'456 New St, City',_binary '','2025-07-09 10:04:21.125770','new one',1,402),(606,'123 Main St',_binary '','2025-07-09 10:04:27.040494','john_doe',8,402),(607,'afsdfr',_binary '','2025-07-09 10:04:33.875224','ABC',25,402),(652,'456 New St, City',_binary '','2025-07-10 09:52:41.783084','new one',1,452);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_executive`
--

DROP TABLE IF EXISTS `delivery_executive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_executive` (
  `zone_assigned` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  `daily_logs` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKhwr5bdj1dvyr49utkujx00j7c` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_executive`
--

LOCK TABLES `delivery_executive` WRITE;
/*!40000 ALTER TABLE `delivery_executive` DISABLE KEYS */;
INSERT INTO `delivery_executive` VALUES ('East Zone',10,NULL),('North Zone',11,NULL),('A Zone',17,NULL),('West Zone',19,NULL);
/*!40000 ALTER TABLE `delivery_executive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_seq`
--

DROP TABLE IF EXISTS `delivery_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_seq`
--

LOCK TABLES `delivery_seq` WRITE;
/*!40000 ALTER TABLE `delivery_seq` DISABLE KEYS */;
INSERT INTO `delivery_seq` VALUES (751);
/*!40000 ALTER TABLE `delivery_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `paper_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `total` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs80dosudnf2oyb8qse7iphcya` (`customer_id`),
  CONSTRAINT `FKs80dosudnf2oyb8qse7iphcya` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (2,'2025-06-30','TOI',5,2,'2025-06-01',10,5),(3,'2025-07-03','The Daily Times',10,1,'2025-06-03',300,28);
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `role` varchar(31) NOT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `version` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('customer',1,'2222222222','jane.doe@example.com','Jane Doey','123456789','new one',8),('customer',5,'9876543210','john.doe@example.com','John Doe','123456789','Jonny Doe Shinde',2),('admin',6,'1234567890','admin@gmail.com','Admin','admin','admin',0),('delivery_executive',7,'1234567890','de@gmail.com','delivery wale bhaiya','123456','de',0),('customer',8,'111111111111111','john@example.com','John Doe123','securePass123','john_doe',2),('delivery_executive',10,'123456789123','johndoe@example.com','new name','securePass123','mera naam jonny',21),('delivery_executive',11,'9876543210123','anika.sharma@example.com','new name','258258258','anika.sharma',8),('delivery_executive',17,'98156789858123','John@gmail.com','new name','987654321','newuser',9),('delivery_executive',19,'12345678','newgmail@gmail.com','New name','12345678','anika.sharma',8),('customer',20,'1234567890','Sharanya@gmail.com','Sharanya','123456789','New user',0),('customer',25,'1235','abc@gmail.com','abc','securePass123','ABC',0),('customer',28,'1234567890','john@example.com','John Doe','securePass123','john_doe',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-16 21:21:21
