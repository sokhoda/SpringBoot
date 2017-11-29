CREATE DATABASE  IF NOT EXISTS `db_example` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_example`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: pizza
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `appNo` varchar(255) DEFAULT NULL,
  `buildingNo` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `strName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  `Cust_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgo99edubu6rcsnqbtj38dh204` (`Cust_ID`),
  CONSTRAINT `FKgo99edubu6rcsnqbtj38dh204` FOREIGN KEY (`Cust_ID`) REFERENCES `customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheque` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `totalSum` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cheque`
--

LOCK TABLES `cheque` WRITE;
/*!40000 ALTER TABLE `cheque` DISABLE KEYS */;
INSERT INTO `cheque` VALUES (1,'2017-11-23 05:59:27','Simple Pizza Cheque #',1320),(2,'2017-11-19 06:02:44','Simple Pizza Cheque #',516),(3,'2017-11-24 06:20:44','Simple Pizza Cheque #',1906);
/*!40000 ALTER TABLE `cheque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `LoyalCard_ID` bigint(20) DEFAULT NULL,
  `appNo` varchar(255) DEFAULT NULL,
  `buildingNo` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `strName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `zipCode` varchar(255) DEFAULT NULL,
  `streetName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  KEY `FKimdowhusaf02j60kd3oegiswp` (`LoyalCard_ID`),
  CONSTRAINT `FKimdowhusaf02j60kd3oegiswp` FOREIGN KEY (`LoyalCard_ID`) REFERENCES `loyaltycard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'john@bestcompany.com','John',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'marry@bestcompany.com','Marry USA',NULL,'23','4','Oklakhoma',NULL,'OFFICE','34262','John Linkoln'),(3,'irene@bestcompany.com','Irene',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'mike@bestcompany.com','Mike Poland',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'steward@bestcompany.com','Steward Austria',NULL,'43','2-11','Vienna',NULL,'HOME','34521','Mykola'),(6,'jeffry@bestcompany.com','Jeffry',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'alex@bestcompany.com','Alex',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'alan@bestcompany.com','Alan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'dewdw@dec.c','Anna Dorina',NULL,'33','4','Vienna',NULL,NULL,'34521','Sagaydachnogo Str.'),(13,'user1@mycompany.com','user1',NULL,'6','43','Venecia',NULL,'OFFICE','34543','Tarasa Shevchenka'),(14,'erf@ded.com','eff',NULL,'HOME','34','fef',NULL,'OFFICE','34521','efefre');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discountrecord`
--

DROP TABLE IF EXISTS `discountrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discountrecord` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sum` double DEFAULT NULL,
  `Cheque_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcs54ddicxxuglkcns7vhh6vmi` (`Cheque_ID`),
  CONSTRAINT `FKcs54ddicxxuglkcns7vhh6vmi` FOREIGN KEY (`Cheque_ID`) REFERENCES `cheque` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discountrecord`
--

LOCK TABLES `discountrecord` WRITE;
/*!40000 ALTER TABLE `discountrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `discountrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen` (
  `GEN_KEY` varchar(255) NOT NULL,
  `GEN_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`GEN_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen`
--

LOCK TABLES `id_gen` WRITE;
/*!40000 ALTER TABLE `id_gen` DISABLE KEYS */;
INSERT INTO `id_gen` VALUES ('CHEQUE_ID',4),('CUSTOMER_ID',15),('ORDER_ID',35),('PIZZA_ID',9);
/*!40000 ALTER TABLE `id_gen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loyaltycard`
--

DROP TABLE IF EXISTS `loyaltycard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loyaltycard` (
  `id` bigint(20) NOT NULL,
  `sum` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loyaltycard`
--

LOCK TABLES `loyaltycard` WRITE;
/*!40000 ALTER TABLE `loyaltycard` DISABLE KEYS */;
/*!40000 ALTER TABLE `loyaltycard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza`
--

DROP TABLE IF EXISTS `pizza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza` (
  `pizzaId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pizzaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza`
--

LOCK TABLES `pizza` WRITE;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` VALUES (1,'Napoletano',100,'MEAT'),(2,'Hawaii',130,'SEA'),(3,'Country',129,'VEGETERIAN'),(4,'Mushroom',245.1,'VEGETERIAN'),(5,'Cheese',354,'VEGETERIAN'),(6,'calamari',454,'SEA'),(7,'DoubleCheese',453,'VEGETERIAN'),(8,'DoubleMushroom',200,'VEGETERIAN');
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_quant`
--

DROP TABLE IF EXISTS `pizza_quant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pizza_quant` (
  `Ord_ID` bigint(20) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PIZZA_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Ord_ID`,`PIZZA_ID`),
  KEY `FKpb3ao6h8b53ol0ep7my0ati5d` (`PIZZA_ID`),
  CONSTRAINT `FK4q84akwhv9ul7c6al4h3r9gd2` FOREIGN KEY (`Ord_ID`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `FKpb3ao6h8b53ol0ep7my0ati5d` FOREIGN KEY (`PIZZA_ID`) REFERENCES `pizza` (`pizzaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_quant`
--

LOCK TABLES `pizza_quant` WRITE;
/*!40000 ALTER TABLE `pizza_quant` DISABLE KEYS */;
INSERT INTO `pizza_quant` VALUES (1,1,1),(1,2,2),(1,3,3),(1,4,4),(1,5,5),(1,6,6),(1,7,7),(1,8,8),(2,1,1),(2,2,4),(2,4,6),(3,1,2),(3,5,5),(4,1,2),(4,2,4),(5,2,1),(5,1,4),(6,10,3),(7,2,3),(7,1,5),(8,1,3),(9,2,2),(10,3,3),(11,1,3),(12,2,5),(13,2,2),(14,2,3),(15,2,2),(16,4,3),(17,4,5),(17,2,7),(18,22,2),(18,2,3),(19,2,3),(19,1,4),(20,4,2),(21,3,4),(21,4,5),(22,5,3),(23,3,2),(24,44,4),(25,2,2),(26,2,3),(26,4,5),(27,2,3),(27,5,5),(28,1,2),(28,3,4),(29,3,2),(29,2,4),(29,5,6),(30,3,3),(30,4,5),(31,1,3),(31,2,4),(32,2,3),(32,3,5),(33,4,3),(34,2,4),(34,4,5);
/*!40000 ALTER TABLE `pizza_quant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL,
  `curState` varchar(255) DEFAULT NULL,
  `CHEQUE_ID` bigint(20) DEFAULT NULL,
  `CUST_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdhdkpgiinpxgafqe57p58s305` (`CHEQUE_ID`),
  KEY `FKhaljhbl5sqg9yt70blddck54x` (`CUST_ID`),
  CONSTRAINT `FKdhdkpgiinpxgafqe57p58s305` FOREIGN KEY (`CHEQUE_ID`) REFERENCES `cheque` (`id`),
  CONSTRAINT `FKhaljhbl5sqg9yt70blddck54x` FOREIGN KEY (`CUST_ID`) REFERENCES `customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (1,'NEW',NULL,NULL),(2,'NEW',NULL,13),(3,'NEW',NULL,13),(4,'NEW',NULL,13),(5,'NEW',NULL,13),(6,'NEW',NULL,13),(7,'NEW',NULL,13),(8,'NEW',NULL,13),(9,'NEW',NULL,13),(10,'NEW',NULL,13),(11,'NEW',NULL,13),(12,'NEW',NULL,13),(13,'NEW',NULL,13),(14,'NEW',NULL,13),(15,'NEW',NULL,13),(16,'NEW',NULL,13),(17,'NEW',NULL,13),(18,'NEW',NULL,13),(19,'NEW',NULL,13),(20,'NEW',NULL,13),(21,'NEW',NULL,13),(22,'NEW',NULL,13),(23,'NEW',NULL,13),(24,'NEW',NULL,13),(25,'NEW',NULL,13),(26,'NEW',NULL,13),(27,'NEW',NULL,13),(28,'NEW',NULL,13),(29,'NEW',NULL,13),(30,'NEW',NULL,13),(31,'NEW',NULL,13),(32,'NEW',1,13),(33,'NEW',2,13),(34,'NEW',3,13);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pizza'
--

--
-- Dumping routines for database 'pizza'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-29 10:00:13
