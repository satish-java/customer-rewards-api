-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
INSERT INTO `transaction` VALUES (1,120,1,'2025-12-17'),(2,75,1,'2025-09-25'),(3,130,1,'2025-10-25'),(4,199,2,'2025-11-20'),(5,40,2,'2025-10-25'),(6,99,2,'2025-09-22'),(7,111,3,'2025-10-25'),(8,111,4,'2025-10-25');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed
