-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: SymptomTracker
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(300) NOT NULL DEFAULT 'new activity',
  `note` varchar(700) DEFAULT NULL,
  `datetime_start` datetime NOT NULL,
  `datetime_end` datetime NOT NULL,
  `Strain` int NOT NULL DEFAULT '0',
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_user_id_idx` (`user_id`),
  CONSTRAINT `activity_FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `default_symptom`
--

DROP TABLE IF EXISTS `default_symptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `default_symptom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `category` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `default_symptom`
--

LOCK TABLES `default_symptom` WRITE;
/*!40000 ALTER TABLE `default_symptom` DISABLE KEYS */;
INSERT INTO `default_symptom` VALUES (1,'Hovedpine',NULL),(2,'Svimmelhed',NULL),(3,'Kvalme',NULL),(4,'Lydfølsomhed',NULL),(5,'Lysfølsomhed',NULL),(6,'Anspændte øjne',NULL),(7,'Synsproblemer',NULL),(8,'Nedsat synsevne',NULL),(9,'Udmattelse',NULL),(10,'Akut træthed',NULL),(11,'Nakkesmerter',NULL),(12,'Ondt i led',NULL),(13,'Trykken i hovedet',NULL),(14,'Overaktivt nervesystem',NULL),(15,'Muskel spændinger',NULL),(16,'Migræne',NULL),(17,'Koncentrationsbesvær',NULL),(18,'Hukommelsesbesvær',NULL),(19,'Vanskeligheder med arbejdshukommelsen',NULL),(20,'Nedsat synsregistrering',NULL),(21,'Beslutningstræthed',NULL),(22,'Langsommere tænkning',NULL),(23,'Nedsat sensorisk filter',NULL),(24,'Tristhed',NULL),(25,'Ængstelighed',NULL),(26,'Angst',NULL),(27,'Irritabelhed',NULL),(28,'Aggression',NULL),(29,'Stress',NULL),(30,'Problemer med emotionel kontrol',NULL),(31,'Søvnløshed',NULL),(32,'Spændinger i kraniekanten',NULL),(33,'Humørsvingninger',NULL),(34,'Osteklokke følelse',NULL),(35,'Tinnitus',NULL);
/*!40000 ALTER TABLE `default_symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom`
--

DROP TABLE IF EXISTS `symptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `symptom` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT 'new symptom',
  `description` varchar(700) NOT NULL DEFAULT '-',
  `num_daily_registration` int NOT NULL DEFAULT '1',
  `color` varchar(100) NOT NULL DEFAULT 'black',
  `active` tinyint NOT NULL DEFAULT '1',
  `default_symptom_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `visibility_on_statistics` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_user_id_idx` (`user_id`),
  KEY `FK_defaultsymptom_id_idx` (`default_symptom_id`),
  CONSTRAINT `FK_defaultsymptom_id` FOREIGN KEY (`default_symptom_id`) REFERENCES `default_symptom` (`id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom`
--

LOCK TABLES `symptom` WRITE;
/*!40000 ALTER TABLE `symptom` DISABLE KEYS */;
INSERT INTO `symptom` VALUES (1,'Hovedpine','bla',3,'#9E0059',1,NULL,1,1),(2,'Svimmelhed','bla',3,'#AE5E26',1,NULL,1,0),(5,'Lysfølsomhed','bla',3,'#FF8000',1,NULL,1,1),(6,'Overanstrengte øjne','bla',3,'#FF4000',1,NULL,1,0),(7,'Træthed','bla',3,'#85AD14',1,NULL,1,0),(8,'Hjernetræthed','bla',3,'#F3DE2C',1,NULL,1,0),(9,'Søvnig','bla',3,'#6C0079',1,NULL,1,0),(10,'Nakkesmerter','bla',3,'#218380',1,NULL,1,0),(11,'Trykken i hovedet','bla',3,'#7CB518',1,NULL,1,0),(12,'Overaktivt nervesystem','bla',3,'#FFBD00',1,NULL,1,0),(13,'Migræne','bla',3,'#FF4000',1,NULL,1,0),(14,'begrænset arbejdshukommelsen','bla',3,'#40DDC8',1,NULL,1,0),(15,'Koncentrationsbesvær','bla',3,'#CC33FF',1,NULL,1,1),(16,'Hurtig/akut udtrætning','bla',3,'#9E0059',1,NULL,1,1),(17,'Beslutningstræthed','bla',3,'#00A0E3',1,NULL,1,1),(19,'Nedsat reaktionsevne ','bla',3,'#FC2F00',1,NULL,1,1),(25,'Stress','bla',3,'#03045E',1,NULL,1,0),(29,'Søvnløshed','bla',3,'#6B0504',1,NULL,1,0);
/*!40000 ALTER TABLE `symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_registration`
--

DROP TABLE IF EXISTS `symptom_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `symptom_registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reg_num` int NOT NULL,
  `reg_date` date NOT NULL,
  `intensity` int NOT NULL DEFAULT '0',
  `symptom_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `constraint` (`reg_num`,`symptom_id`,`reg_date`),
  KEY `FK_symptomId_idx` (`symptom_id`),
  CONSTRAINT `FK_symptom_id` FOREIGN KEY (`symptom_id`) REFERENCES `symptom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=997 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_registration`
--

LOCK TABLES `symptom_registration` WRITE;
/*!40000 ALTER TABLE `symptom_registration` DISABLE KEYS */;
INSERT INTO `symptom_registration` VALUES (639,1,'2021-06-01',6,1),(640,2,'2021-06-01',2,1),(641,3,'2021-06-01',3,1),(642,1,'2021-06-01',2,5),(643,1,'2021-06-01',1,2),(644,2,'2021-06-01',2,5),(645,3,'2021-06-01',6,5),(646,1,'2021-06-01',6,6),(647,2,'2021-06-01',4,6),(648,3,'2021-06-01',4,6),(649,1,'2021-06-01',6,7),(650,2,'2021-06-01',4,7),(651,3,'2021-06-01',4,7),(652,1,'2021-06-01',7,8),(653,2,'2021-06-01',7,8),(654,3,'2021-06-01',7,8),(655,1,'2021-06-01',1,9),(656,2,'2021-06-01',4,9),(657,3,'2021-06-01',3,9),(658,1,'2021-06-01',1,10),(659,2,'2021-06-01',2,10),(660,3,'2021-06-01',1,10),(661,1,'2021-06-01',2,11),(662,2,'2021-06-01',1,11),(663,3,'2021-06-01',2,11),(664,1,'2021-06-01',4,12),(665,2,'2021-06-01',4,12),(666,3,'2021-06-01',3,12),(667,1,'2021-06-01',1,13),(668,1,'2021-06-01',5,14),(669,2,'2021-06-01',5,14),(670,3,'2021-06-01',5,14),(671,1,'2021-06-01',6,15),(672,2,'2021-06-01',5,15),(673,3,'2021-06-01',3,15),(674,1,'2021-06-01',2,16),(675,2,'2021-06-01',2,16),(676,3,'2021-06-01',2,16),(677,1,'2021-06-01',4,17),(678,2,'2021-06-01',4,17),(679,3,'2021-06-01',4,17),(680,1,'2021-06-01',1,19),(681,2,'2021-06-01',1,19),(682,3,'2021-06-01',2,19),(683,1,'2021-06-01',7,25),(684,2,'2021-06-01',6,25),(685,3,'2021-06-01',4,25),(686,1,'2021-06-01',1,29),(687,2,'2021-06-01',1,29),(688,3,'2021-05-31',7,29),(689,1,'2021-05-31',7,1),(690,2,'2021-05-31',7,1),(691,3,'2021-05-31',5,1),(692,1,'2021-05-31',5,2),(693,2,'2021-05-31',2,2),(694,3,'2021-05-31',5,2),(695,1,'2021-05-31',6,5),(696,3,'2021-05-31',3,5),(697,1,'2021-05-31',7,6),(698,2,'2021-05-31',2,6),(699,3,'2021-05-31',2,6),(700,1,'2021-05-31',2,7),(701,2,'2021-05-31',1,7),(702,3,'2021-05-31',1,7),(703,1,'2021-05-31',2,8),(704,2,'2021-05-31',3,8),(705,3,'2021-05-31',2,8),(706,1,'2021-05-31',1,9),(707,2,'2021-05-31',1,9),(708,3,'2021-05-31',4,9),(709,1,'2021-05-31',3,10),(710,2,'2021-05-31',4,10),(711,3,'2021-05-31',5,10),(712,1,'2021-05-31',5,11),(713,2,'2021-05-31',6,11),(714,3,'2021-05-31',4,11),(715,1,'2021-05-31',5,12),(716,2,'2021-05-31',5,12),(717,3,'2021-05-31',6,12),(718,1,'2021-05-31',7,13),(719,2,'2021-05-31',7,13),(720,3,'2021-05-31',7,13),(721,1,'2021-05-31',2,14),(722,2,'2021-05-31',2,14),(723,3,'2021-05-31',3,14),(724,1,'2021-05-31',6,15),(725,2,'2021-05-31',5,15),(726,3,'2021-05-31',4,15),(727,1,'2021-05-31',1,16),(728,2,'2021-05-31',1,16),(729,3,'2021-05-31',1,16),(730,1,'2021-05-31',7,17),(731,2,'2021-05-31',7,17),(732,3,'2021-05-31',7,17),(733,1,'2021-05-31',5,19),(734,2,'2021-05-31',2,19),(735,1,'2021-05-31',6,25),(736,3,'2021-05-31',7,25),(737,2,'2021-05-31',7,29),(738,1,'2021-05-31',7,29),(739,2,'2021-05-30',3,5),(740,1,'2021-05-30',2,1),(741,2,'2021-05-30',3,1),(742,3,'2021-05-30',1,1),(743,1,'2021-05-30',4,2),(744,2,'2021-05-30',4,2),(745,1,'2021-05-30',3,5),(746,3,'2021-05-30',4,5),(747,1,'2021-05-30',1,6),(748,1,'2021-05-30',5,10),(749,1,'2021-05-30',5,11),(750,1,'2021-05-30',6,12),(751,1,'2021-05-30',1,13),(752,2,'2021-05-30',1,13),(753,3,'2021-05-29',1,13),(754,1,'2021-05-29',4,1),(755,2,'2021-05-29',4,1),(756,3,'2021-05-29',4,1),(757,2,'2021-05-29',5,2),(758,1,'2021-05-29',6,2),(759,1,'2021-05-29',3,5),(760,2,'2021-05-29',5,6),(761,2,'2021-05-29',3,7),(762,1,'2021-05-29',5,8),(763,1,'2021-05-29',3,7),(764,3,'2021-05-29',4,7),(765,3,'2021-05-29',3,8),(766,3,'2021-05-29',5,9),(767,2,'2021-05-29',2,9),(768,1,'2021-05-29',2,9),(769,1,'2021-05-29',4,10),(770,2,'2021-05-29',5,10),(771,3,'2021-05-29',4,10),(772,3,'2021-05-29',5,11),(773,2,'2021-05-29',7,11),(774,1,'2021-05-29',6,11),(775,1,'2021-05-29',5,12),(776,2,'2021-05-29',4,12),(777,3,'2021-05-29',5,12),(778,3,'2021-05-29',3,14),(779,2,'2021-05-29',4,14),(780,1,'2021-05-29',5,14),(781,1,'2021-05-29',7,15),(782,1,'2021-05-29',2,16),(783,2,'2021-05-29',4,16),(784,1,'2021-05-29',6,17),(785,3,'2021-05-29',2,16),(786,2,'2021-05-29',6,17),(787,3,'2021-05-29',6,17),(788,2,'2021-05-29',3,19),(789,3,'2021-05-29',4,19),(790,1,'2021-05-29',4,19),(791,1,'2021-05-29',5,25),(792,2,'2021-05-29',5,25),(793,3,'2021-05-29',6,25),(794,3,'2021-05-29',1,29),(795,2,'2021-05-29',1,29),(796,1,'2021-05-27',1,29),(797,1,'2021-05-27',2,1),(798,2,'2021-05-27',3,1),(799,3,'2021-05-27',1,1),(800,1,'2021-05-27',1,2),(801,2,'2021-05-27',1,2),(802,3,'2021-05-27',1,2),(803,2,'2021-05-27',6,5),(804,3,'2021-05-27',3,5),(805,2,'2021-05-27',6,6),(806,1,'2021-05-27',6,6),(807,3,'2021-05-27',6,6),(808,1,'2021-05-27',6,7),(809,2,'2021-05-27',6,7),(810,3,'2021-05-27',6,7),(811,1,'2021-05-27',6,8),(812,2,'2021-05-27',6,8),(813,3,'2021-05-27',6,8),(814,1,'2021-05-27',1,9),(815,2,'2021-05-27',1,9),(816,3,'2021-05-27',3,9),(817,1,'2021-05-27',1,10),(818,2,'2021-05-27',1,10),(819,3,'2021-05-27',1,10),(820,1,'2021-05-27',6,25),(821,2,'2021-05-27',4,25),(822,3,'2021-05-27',5,25),(823,1,'2021-05-27',6,17),(824,2,'2021-05-27',1,17),(825,3,'2021-05-27',2,17),(826,1,'2021-05-27',6,16),(827,2,'2021-05-27',6,16),(828,3,'2021-05-26',4,16),(829,1,'2021-05-26',4,1),(831,1,'2021-06-02',1,1),(832,2,'2021-06-02',1,1),(833,3,'2021-06-02',1,1),(834,3,'2021-06-02',1,2),(835,3,'2021-06-02',2,5),(836,2,'2021-06-02',4,5),(837,1,'2021-06-02',1,2),(838,1,'2021-06-02',4,6),(839,1,'2021-06-02',3,7),(840,1,'2021-06-02',2,8),(841,2,'2021-06-02',5,7),(842,2,'2021-06-02',4,8),(843,3,'2021-06-02',6,8),(844,3,'2021-06-02',5,7),(845,3,'2021-06-02',3,6),(846,2,'2021-06-02',3,10),(847,2,'2021-06-02',6,11),(848,1,'2021-06-02',4,11),(849,3,'2021-06-02',3,11),(850,3,'2021-06-02',3,12),(851,2,'2021-06-02',2,12),(852,1,'2021-06-02',3,12),(853,1,'2021-06-02',3,14),(854,2,'2021-06-02',3,14),(855,3,'2021-06-02',5,14),(856,1,'2021-06-02',2,15),(857,2,'2021-06-02',2,15),(858,3,'2021-06-02',2,15),(859,1,'2021-06-02',4,25),(860,2,'2021-06-02',4,25),(861,3,'2021-06-02',4,25),(862,3,'2021-06-02',3,19),(863,2,'2021-06-02',4,19),(864,1,'2021-06-02',3,19),(865,1,'2021-06-02',5,17),(866,1,'2021-06-02',4,16),(867,2,'2021-06-02',5,16),(868,3,'2021-06-02',5,16),(869,1,'2021-06-04',2,1),(870,1,'2021-06-04',3,5),(871,2,'2021-06-04',1,5),(872,3,'2021-06-04',1,5),(873,1,'2021-06-04',2,6),(874,2,'2021-06-04',2,6),(875,3,'2021-06-04',2,6),(876,1,'2021-06-04',3,8),(877,2,'2021-06-04',1,8),(878,3,'2021-06-04',1,8),(879,1,'2021-06-05',1,11),(880,2,'2021-06-05',1,11),(881,3,'2021-06-05',1,11),(882,1,'2021-06-05',2,14),(883,2,'2021-06-05',3,14),(884,3,'2021-06-05',2,14),(885,3,'2021-06-05',2,17),(886,3,'2021-06-05',1,25),(887,1,'2021-06-05',1,29),(888,1,'2021-06-05',2,1),(889,1,'2021-06-05',5,5),(890,1,'2021-06-05',6,6),(891,1,'2021-06-05',6,8),(892,2,'2021-06-05',5,8),(893,2,'2021-06-05',7,6),(894,2,'2021-06-05',3,5),(895,3,'2021-06-05',3,5),(896,3,'2021-06-05',6,6),(897,3,'2021-06-05',5,8),(898,3,'2021-06-05',1,1),(899,1,'2021-06-05',1,10),(900,2,'2021-06-05',1,10),(901,3,'2021-06-05',2,10),(902,1,'2021-06-05',3,15),(903,2,'2021-06-05',4,15),(904,3,'2021-06-05',3,15),(905,1,'2021-06-05',1,17),(906,2,'2021-06-05',1,25),(907,1,'2021-06-06',2,1),(908,2,'2021-06-06',2,1),(909,3,'2021-06-06',1,1),(910,1,'2021-06-06',3,5),(911,2,'2021-06-06',3,5),(912,3,'2021-06-06',3,5),(913,1,'2021-06-06',6,6),(914,1,'2021-06-06',6,8),(915,2,'2021-06-06',5,8),(916,2,'2021-06-06',6,6),(917,3,'2021-06-06',5,6),(918,3,'2021-06-06',5,8),(919,1,'2021-06-06',2,10),(920,1,'2021-06-06',1,11),(921,2,'2021-06-06',1,10),(922,2,'2021-06-06',2,11),(923,3,'2021-06-06',2,10),(924,3,'2021-06-06',1,11),(925,1,'2021-06-06',2,14),(926,2,'2021-06-06',2,14),(927,3,'2021-06-06',1,14),(928,3,'2021-06-06',3,15),(929,2,'2021-06-06',4,15),(930,1,'2021-06-06',3,15),(931,1,'2021-06-06',1,16),(932,2,'2021-06-06',1,16),(933,3,'2021-06-06',2,16),(934,3,'2021-06-06',2,17),(935,3,'2021-06-06',1,25),(936,2,'2021-06-06',1,25),(937,1,'2021-06-03',1,29),(938,1,'2021-06-07',1,2),(939,2,'2021-06-07',1,2),(940,3,'2021-06-07',2,2),(941,1,'2021-06-07',3,7),(942,2,'2021-06-07',2,7),(943,3,'2021-06-07',1,7),(944,1,'2021-06-07',1,10),(945,2,'2021-06-07',2,10),(946,3,'2021-06-07',3,10),(947,1,'2021-06-03',2,11),(948,2,'2021-06-07',2,11),(949,3,'2021-06-07',3,11),(950,1,'2021-06-07',1,13),(951,2,'2021-06-07',2,13),(952,3,'2021-06-07',3,13),(953,1,'2021-06-07',1,14),(954,2,'2021-06-07',1,14),(955,3,'2021-06-07',1,14),(956,1,'2021-06-07',6,15),(957,2,'2021-06-07',4,15),(958,3,'2021-06-07',6,15),(959,2,'2021-06-07',2,16),(960,2,'2021-06-07',3,17),(961,1,'2021-06-07',1,17),(962,1,'2021-06-03',2,2),(963,2,'2021-06-03',1,2),(964,3,'2021-06-03',1,2),(965,1,'2021-06-03',2,7),(966,2,'2021-06-03',2,7),(967,3,'2021-06-03',3,7),(968,1,'2021-06-03',2,10),(969,2,'2021-06-03',1,10),(970,3,'2021-06-03',1,10),(971,2,'2021-06-03',2,11),(972,3,'2021-06-03',1,11),(973,2,'2021-06-03',1,13),(974,3,'2021-06-03',1,13),(975,1,'2021-06-03',2,14),(976,2,'2021-06-03',2,14),(977,3,'2021-06-03',1,14),(978,1,'2021-06-03',5,15),(979,2,'2021-06-03',3,15),(980,3,'2021-06-03',2,15),(981,1,'2021-06-03',2,16),(982,2,'2021-06-03',2,16),(983,3,'2021-06-03',3,16),(984,1,'2021-06-03',3,17),(985,2,'2021-06-03',2,17),(986,3,'2021-06-03',1,17),(987,1,'2021-06-03',1,19),(988,1,'2021-06-03',1,25),(989,2,'2021-06-03',2,25),(990,3,'2021-06-03',1,25),(991,1,'2021-06-03',6,5),(992,1,'2021-06-03',1,6),(993,1,'2021-06-03',1,8),(994,1,'2021-06-03',5,1),(995,3,'2021-06-03',5,12),(996,2,'2021-06-03',6,12);
/*!40000 ALTER TABLE `symptom_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(300) NOT NULL,
  `red_strain_visibility` tinyint NOT NULL DEFAULT '0',
  `yellow_strain_visibility` tinyint NOT NULL DEFAULT '0',
  `green_strain_visibility` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@kea','$2y$12$ZfegoBbbLEYbc82qC6Ni.udYKG1T5E1N4L7LwOJzWYLBjxb6/jSiO',0,0,0);
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

-- Dump completed on 2021-06-04 11:12:56
