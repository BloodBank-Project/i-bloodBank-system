CREATE DATABASE  IF NOT EXISTS `blood-bank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `blood-bank`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: blood-bank
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `blood_bank`
--

DROP TABLE IF EXISTS `blood_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blood_bank` (
  `blood_bank_id` bigint NOT NULL AUTO_INCREMENT,
  `available_blood_group` varchar(255) DEFAULT NULL,
  `blood_bank_location` varchar(255) DEFAULT NULL,
  `blood_bank_name` varchar(255) DEFAULT NULL,
  `blood_quantity` int DEFAULT NULL,
  PRIMARY KEY (`blood_bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood_bank`
--

LOCK TABLES `blood_bank` WRITE;
/*!40000 ALTER TABLE `blood_bank` DISABLE KEYS */;
INSERT INTO `blood_bank` VALUES (1,'A-','marathalli','IBM',15),(2,'AB-','hebbal','IBH',20),(4,'A+','marathalli','IBM',18),(5,'O-','silkboard','IBS',21),(6,'AB+','marathalli','IBM',32),(7,'B-','hebbal','IBH',19),(8,'O+','hebbal','IBH',30),(9,'B+','silkboard','IBS',37);
/*!40000 ALTER TABLE `blood_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blood_group`
--

DROP TABLE IF EXISTS `blood_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blood_group` (
  `blood_id` int NOT NULL AUTO_INCREMENT,
  `blood_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`blood_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood_group`
--

LOCK TABLES `blood_group` WRITE;
/*!40000 ALTER TABLE `blood_group` DISABLE KEYS */;
INSERT INTO `blood_group` VALUES (1,'A+'),(2,'B+'),(3,'O+'),(4,'AB+'),(5,'A-'),(6,'B-'),(7,'O-'),(8,'AB-');
/*!40000 ALTER TABLE `blood_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor`
--

DROP TABLE IF EXISTS `donor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor`
--

LOCK TABLES `donor` WRITE;
/*!40000 ALTER TABLE `donor` DISABLE KEYS */;
INSERT INTO `donor` VALUES (1,3),(2,7),(3,4),(4,5),(6,8),(8,2),(11,6),(12,10);
/*!40000 ALTER TABLE `donor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_details`
--

DROP TABLE IF EXISTS `donor_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_details` (
  `donation_id` bigint NOT NULL AUTO_INCREMENT,
  `blood_quantity` bigint DEFAULT NULL,
  `date_of_donation` date DEFAULT NULL,
  `status` varchar(255) DEFAULT 'Pending',
  `donor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`donation_id`),
  KEY `FKmn3odlhdftm3aigfxov5muuy3` (`donor_id`),
  CONSTRAINT `FKmn3odlhdftm3aigfxov5muuy3` FOREIGN KEY (`donor_id`) REFERENCES `donor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_details`
--

LOCK TABLES `donor_details` WRITE;
/*!40000 ALTER TABLE `donor_details` DISABLE KEYS */;
INSERT INTO `donor_details` VALUES (1,2,'2024-02-21','Accepted',1),(2,1,'2024-01-05','Pending',8),(3,2,'2024-02-09','Accepted',4),(4,1,'2024-01-19','Rejected',2),(5,1,'2024-02-29','Pending',4),(7,1,'2024-02-15','Rejected',2),(8,2,'2024-02-22','Rejected',4),(9,1,'2024-01-12','Accepted',2),(10,1,'2024-02-22','Pending',8),(14,1,'2024-03-16','Accepted',11),(15,1,'2024-03-23','Accepted',11),(16,2,'2024-03-15','Rejected',3),(17,1,'2024-03-15','Pending',11),(18,1,'2024-03-17','Pending',12),(19,1,'2024-03-16','Pending',6);
/*!40000 ALTER TABLE `donor_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_details_entity`
--

DROP TABLE IF EXISTS `donor_details_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_details_entity` (
  `donation_id` bigint NOT NULL AUTO_INCREMENT,
  `blood_quantity` bigint DEFAULT NULL,
  `date_of_donation` date DEFAULT NULL,
  `donor_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`donation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_details_entity`
--

LOCK TABLES `donor_details_entity` WRITE;
/*!40000 ALTER TABLE `donor_details_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `donor_details_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor_entity`
--

DROP TABLE IF EXISTS `donor_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donor_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor_entity`
--

LOCK TABLES `donor_entity` WRITE;
/*!40000 ALTER TABLE `donor_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `donor_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES (1,'gojevaishali9@gmail.com','2024-03-15 15:15:19.449226','665200'),(2,'mounikajeela002@gmail.com','2024-03-15 13:42:40.726566','629072');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patient_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,3),(2,2),(4,5),(5,6),(6,7),(7,10),(8,8);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_request`
--

DROP TABLE IF EXISTS `patient_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_request` (
  `patient_request_id` bigint NOT NULL,
  `patient_medical_condition` varchar(255) DEFAULT NULL,
  `patient_request_on_date` date DEFAULT NULL,
  `patient_request_units` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `patient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`patient_request_id`),
  KEY `FKj68tauwp8dpf73lir376xk2tt` (`patient_id`),
  CONSTRAINT `FKj68tauwp8dpf73lir376xk2tt` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_request`
--

LOCK TABLES `patient_request` WRITE;
/*!40000 ALTER TABLE `patient_request` DISABLE KEYS */;
INSERT INTO `patient_request` VALUES (2,'malaria','2024-01-20',1,'Rejected',4),(3,'anemia','2024-03-15',3,'Accepted',5),(4,'haemophilia','2024-02-09',2,'Pending',2),(5,'anemia','2024-02-12',1,'Accepted',2),(6,'malaria','2023-12-10',3,'Accepted',2),(52,'anemia','2024-03-15',2,'Pending',6),(53,'anemia','2024-03-16',3,'Accepted',4),(102,'anemia','2024-03-15',1,'Pending',5),(152,'malaria','2024-03-15',2,'Rejected',1),(153,'malaria','2024-03-24',2,'Pending',7),(154,'malaria','2024-03-27',2,'Accepted',5),(155,'fever','2024-03-16',2,'Pending',8);
/*!40000 ALTER TABLE `patient_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_request_seq`
--

DROP TABLE IF EXISTS `patient_request_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_request_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_request_seq`
--

LOCK TABLES `patient_request_seq` WRITE;
/*!40000 ALTER TABLE `patient_request_seq` DISABLE KEYS */;
INSERT INTO `patient_request_seq` VALUES (251);
/*!40000 ALTER TABLE `patient_request_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `alternate_contact_number` bigint DEFAULT NULL,
  `blood_bank_id` bigint DEFAULT NULL,
  `blood_group_id` bigint DEFAULT NULL,
  `contact_number` bigint DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anantapur',9898765432,2,8,9876543210,'2001-12-01','jyothi123@gmail.com','jyothi','female','ediga','Jyothi@555','A'),(2,'hyderabad',9898765432,8,3,9876543210,'2000-02-22','mounikajeela002@gmail.com','mounika','female','jeela','12345','U'),(3,'bellary',9898765432,4,1,9876543210,'2000-09-09','s@gmail.com','shashi','male','vardhan','12345','U'),(4,'kurnool',9898765432,2,8,9876543210,'1999-12-10','d@gmail.com','dhanush','male','kumar','12345','U'),(5,'mysore',9898765432,6,4,9876543210,'2001-03-02','varshini@gmail.com','varshini','female','reddy','Varshini@555','U'),(6,'mumbai',9898765432,9,2,9876543210,'1999-12-10','r@gmail.com','raju','male','reddy','12345','U'),(7,'dellhi',9898765432,1,5,9876543210,'2001-12-01','b@gmail.com','bharathi','female','paricharla','12345','U'),(8,'indore',9898765432,7,6,9876543210,'2000-09-09','gojevaishali9@gmail.com','vaishali','female','goje','Vaishali@555','U'),(9,'chennai',9898765432,5,7,9876543210,'1999-12-10','p@gmail.com','pradeep','male','kumar','12345','U'),(10,'whitefield',7470530540,1,5,6264944100,'2005-10-24','shalinihoro2@gmail.com','shalini','Female','horo','Shalini@24','user');
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

-- Dump completed on 2024-03-20 15:56:36
