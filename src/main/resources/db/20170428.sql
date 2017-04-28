CREATE DATABASE  IF NOT EXISTS `ys` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ys`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ys
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `a_article`
--

DROP TABLE IF EXISTS `a_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `a_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(2) NOT NULL DEFAULT '0',
  `title` varchar(256) NOT NULL,
  `body` text NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `noOfRead` int(11) NOT NULL DEFAULT '0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `ip` varchar(40) DEFAULT NULL,
  `upvote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `categoryId_idx` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_article`
--

LOCK TABLES `a_article` WRITE;
/*!40000 ALTER TABLE `a_article` DISABLE KEYS */;
INSERT INTO `a_article` VALUES (1,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(2,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(3,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(4,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(5,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(6,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(7,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(8,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(9,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',0,'\0',NULL,0),(10,0,'test','hahaha',0,'2017-04-24 05:18:21','2017-04-24 05:18:21',1,'\0',NULL,0),(11,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(12,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(13,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(14,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(15,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(16,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(17,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(18,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(19,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',0,'\0',NULL,0),(20,0,'test','hahaha',0,'2017-04-24 06:06:01','2017-04-24 06:06:01',1,'\0',NULL,0),(21,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(22,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(23,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(24,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(25,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(26,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(27,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(28,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(29,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',0,'\0',NULL,0),(30,0,'test','hahaha',0,'2017-04-24 06:06:13','2017-04-24 06:06:13',1,'\0',NULL,0),(31,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(32,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(33,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(34,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(35,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(36,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(37,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(38,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(39,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',0,'\0',NULL,0),(40,0,'test','hahaha',0,'2017-04-24 06:09:41','2017-04-24 06:09:41',1,'\0',NULL,0),(41,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(42,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(43,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(44,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(45,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(46,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(47,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(48,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(49,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',0,'\0',NULL,0),(50,0,'test','hahaha',0,'2017-04-24 06:48:10','2017-04-24 06:48:10',1,'\0',NULL,0),(51,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(52,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(53,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(54,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(55,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(56,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(57,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(58,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(59,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',0,'\0',NULL,0),(60,0,'test','hahaha',0,'2017-04-24 06:48:30','2017-04-24 06:48:30',1,'\0',NULL,0),(61,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(62,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(63,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(64,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(65,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(66,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(67,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(68,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(69,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',0,'\0',NULL,0),(70,0,'test','hahaha',0,'2017-04-24 06:48:43','2017-04-24 06:48:43',1,'\0',NULL,0),(71,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(72,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(73,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(74,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(75,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(76,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(77,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(78,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(79,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',0,'\0',NULL,0),(80,0,'test','hahaha',0,'2017-04-24 06:48:58','2017-04-24 06:48:58',1,'\0',NULL,0),(81,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(82,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(83,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(84,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(85,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(86,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(87,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(88,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(89,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',0,'\0',NULL,0),(90,0,'test','hahaha',0,'2017-04-24 10:35:43','2017-04-24 10:35:43',1,'\0',NULL,0),(91,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(92,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(93,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(94,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(95,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(96,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(97,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(98,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(99,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',0,'\0',NULL,0),(100,0,'test','hahaha',0,'2017-04-27 01:28:40','2017-04-27 01:28:40',1,'\0',NULL,0),(101,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(102,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(103,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(104,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(105,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(106,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(107,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(108,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(109,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',0,'\0',NULL,0),(110,0,'test','hahaha',0,'2017-04-27 01:29:29','2017-04-27 01:29:29',1,'\0',NULL,0);
/*!40000 ALTER TABLE `a_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_template`
--

DROP TABLE IF EXISTS `article_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(2) NOT NULL DEFAULT '0',
  `title` varchar(256) NOT NULL,
  `body` text NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `noOfRead` int(11) NOT NULL DEFAULT '0',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `ip` varchar(40) DEFAULT NULL,
  `upvote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `categoryId_idx` (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_template`
--

LOCK TABLES `article_template` WRITE;
/*!40000 ALTER TABLE `article_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c_comment`
--

DROP TABLE IF EXISTS `c_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL,
  `categoryId` int(2) NOT NULL DEFAULT '0',
  `body` text NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `ip` varchar(40) DEFAULT NULL,
  `upvote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `categoryId_idx` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c_comment`
--

LOCK TABLES `c_comment` WRITE;
/*!40000 ALTER TABLE `c_comment` DISABLE KEYS */;
INSERT INTO `c_comment` VALUES (1,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(2,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(3,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(4,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(5,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(6,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(7,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(8,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(9,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(10,0,0,'hahaha',0,'2017-04-24 10:15:32','2017-04-24 10:15:32','\0',NULL,0),(11,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(12,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(13,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(14,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(15,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(16,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(17,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(18,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(19,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(20,0,0,'hahaha',0,'2017-04-24 10:16:03','2017-04-24 10:16:03','\0',NULL,0),(21,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(22,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(23,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(24,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(25,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(26,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(27,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(28,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(29,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(30,0,0,'hahaha',0,'2017-04-24 10:16:54','2017-04-24 10:16:54','\0',NULL,0),(31,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(32,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(33,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(34,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(35,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(36,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(37,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(38,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(39,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(40,0,0,'hahaha',0,'2017-04-24 10:19:13','2017-04-24 10:19:13','\0',NULL,0),(41,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(42,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(43,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(44,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(45,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(46,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(47,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(48,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(49,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(50,0,0,'hahaha',0,'2017-04-24 10:19:56','2017-04-24 10:19:56','\0',NULL,0),(51,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(52,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(53,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(54,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(55,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(56,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(57,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(58,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(59,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(60,0,0,'hahaha',0,'2017-04-24 10:20:31','2017-04-24 10:20:31','\0',NULL,0),(61,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(62,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(63,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(64,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(65,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(66,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(67,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(68,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(69,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(70,0,0,'hahaha',0,'2017-04-24 10:21:41','2017-04-24 10:21:41','\0',NULL,0),(71,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(72,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(73,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(74,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(75,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(76,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(77,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(78,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(79,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(80,0,0,'hahaha',0,'2017-04-24 10:35:28','2017-04-24 10:35:28','\0',NULL,0),(81,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(82,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(83,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(84,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(85,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(86,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(87,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(88,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(89,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(90,0,0,'hahaha',0,'2017-04-24 10:35:59','2017-04-24 10:35:59','\0',NULL,0),(91,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(92,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(93,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(94,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(95,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(96,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(97,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(98,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(99,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0),(100,0,0,'hahaha',0,'2017-04-27 01:28:27','2017-04-27 01:28:27','\0',NULL,0);
/*!40000 ALTER TABLE `c_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(2,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(3,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(4,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(5,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(6,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(7,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(8,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(9,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(10,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(11,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(12,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(13,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(14,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(15,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(16,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(17,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(18,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(19,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(20,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(21,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(22,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(23,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(24,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(25,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(26,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(27,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(28,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(29,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(30,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(31,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(32,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(33,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(34,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(35,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(36,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(37,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(38,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(39,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(40,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(41,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(42,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(43,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(44,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(45,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(46,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(47,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(48,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(49,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(50,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(51,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(52,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(53,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(54,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(55,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(56,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(57,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(58,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(59,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(60,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(61,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(62,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(63,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(64,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(65,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(66,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(67,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(68,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(69,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(70,'categoryName','\0','2017-04-17 06:12:45','2017-04-17 06:12:45'),(71,'categoryName','\0',NULL,NULL),(72,'categoryName','\0',NULL,NULL),(73,'categoryName','\0',NULL,NULL),(74,'categoryName','\0',NULL,NULL),(75,'categoryName','\0',NULL,NULL),(76,'categoryName','\0',NULL,NULL),(77,'categoryName','\0',NULL,NULL),(78,'categoryName','\0',NULL,NULL),(79,'categoryName','\0',NULL,NULL),(80,'categoryName','\0',NULL,NULL),(81,'categoryName','\0',NULL,NULL),(82,'categoryName','\0',NULL,NULL),(83,'categoryName','\0',NULL,NULL),(84,'categoryName','\0',NULL,NULL),(85,'categoryName','\0',NULL,NULL),(86,'categoryName','\0',NULL,NULL),(87,'categoryName','\0',NULL,NULL),(88,'categoryName','\0',NULL,NULL),(89,'categoryName','\0',NULL,NULL),(90,'categoryName','\0',NULL,NULL),(91,'categoryName','\0',NULL,NULL),(92,'categoryName','\0',NULL,NULL),(93,'categoryName','\0',NULL,NULL),(94,'categoryName','\0',NULL,NULL),(95,'categoryName','\0',NULL,NULL),(96,'categoryName','\0',NULL,NULL),(97,'categoryName','\0',NULL,NULL),(98,'categoryName','\0',NULL,NULL),(99,'categoryName','\0',NULL,NULL),(100,'categoryName','\0',NULL,NULL),(101,'categoryName','\0',NULL,NULL),(102,'categoryName','\0',NULL,NULL),(103,'categoryName','\0',NULL,NULL),(104,'categoryName','\0',NULL,NULL),(105,'categoryName','\0',NULL,NULL),(106,'categoryName','\0',NULL,NULL),(107,'categoryName','\0',NULL,NULL),(108,'categoryName','\0',NULL,NULL),(109,'categoryName','\0',NULL,NULL),(110,'categoryName','\0',NULL,NULL),(111,'categoryName','\0',NULL,NULL),(112,'categoryName','\0',NULL,NULL),(113,'categoryName','\0',NULL,NULL),(114,'categoryName','\0',NULL,NULL),(115,'categoryName','\0',NULL,NULL),(116,'categoryName','\0',NULL,NULL),(117,'categoryName','\0',NULL,NULL),(118,'categoryName','\0',NULL,NULL),(119,'categoryName','\0',NULL,NULL),(120,'categoryName','\0',NULL,NULL),(121,'categoryName','\0',NULL,NULL),(122,'categoryName','\0',NULL,NULL),(123,'categoryName','\0',NULL,NULL),(124,'categoryName','\0',NULL,NULL),(125,'categoryName','\0',NULL,NULL),(126,'categoryName','\0',NULL,NULL),(127,'categoryName','\0',NULL,NULL),(128,'categoryName','\0',NULL,NULL),(129,'categoryName','\0',NULL,NULL),(130,'categoryName','\0',NULL,NULL),(131,'categoryName','\0',NULL,NULL),(132,'categoryName','\0',NULL,NULL),(133,'categoryName','\0',NULL,NULL),(134,'categoryName','\0',NULL,NULL),(135,'categoryName','\0',NULL,NULL),(136,'categoryName','\0',NULL,NULL),(137,'categoryName','\0',NULL,NULL),(138,'categoryName','\0',NULL,NULL),(139,'categoryName','\0',NULL,NULL),(140,'categoryName','\0',NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_template`
--

DROP TABLE IF EXISTS `comment_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL,
  `categoryId` int(2) NOT NULL DEFAULT '0',
  `body` text NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `ip` varchar(40) DEFAULT NULL,
  `upvote` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `categoryId_idx` (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_template`
--

LOCK TABLES `comment_template` WRITE;
/*!40000 ALTER TABLE `comment_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `providerId` varchar(255) NOT NULL,
  `connectionId` varchar(255) NOT NULL,
  `providerConnectionId` varchar(255) NOT NULL DEFAULT '',
  `rank` int(11) DEFAULT '0',
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `roleId` int(1) NOT NULL DEFAULT '0',
  `str` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'0',
  `notLocked` bit(1) NOT NULL DEFAULT b'0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `trial` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=404 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,394,'0@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,395,'1@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,396,'2@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,397,'3@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,398,'4@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,399,'5@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,400,'6@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,401,'7@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,402,'8@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0),('ys','1234ABCD','ys1234ABCD',1,'ys test','http://www.youngsam.com/profile','http://www.google.com/image',NULL,NULL,NULL,10000,403,'9@email.com','ys username','ys password',1,'EedqvmvBeH','','','2017-04-27 01:30:26','2017-04-27 01:30:26',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ys'
--

--
-- Dumping routines for database 'ys'
--
/*!50003 DROP PROCEDURE IF EXISTS `Article_INS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Article_INS`(
  IN `table` varchar(256),
  IN `categoryId` int(2) ,
  IN `title` varchar(256),
  IN `body` text,
  IN `createTime` timestamp,
  IN `updateTime` timestamp,
  IN `userId` INT,
  IN `noOfread` INT, 
  IN `deleted` BIT,
  IN `ip` varchar(40),
  IN `upvote` int,
  OUT `resultId` INT
)
BEGIN
  SET @categoryId=`categoryId`;  
  SET @title=`title`;
  SET @body=`body`;
  SET @createTime=`createTime`;
  SET @updateTime=`updateTime`;
  SET @userId=`userId`;
  SET @noOfread=`noOfread`;
  SET @deleted=`deleted`;
  SET @ip=`ip`;
  SET @upvote=`upvote`;
  
  SET @query=CONCAT('INSERT INTO `',`table`,'` 
				(categoryId,title,body,createTime,updateTime,userId,noOfread,deleted,ip,upvote)
                VALUES
				(@categoryId,@title,@body,@createTime,@updateTime,@userId,@noOfread,@deleted,@ip,@upvote)              
				;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET `resultId`=LAST_INSERT_ID();
  

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Article_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Article_LIST`(
  IN  `table` varchar(50),
  IN  `pageNo` INT,
  IN  `pageSize` INT
)
BEGIN
   	SET @pn= `pageNo`;
	SET @ps= `pageSize`;	
	SET @s= (@pn-1)*@ps;

	SET @a=0;
    SET @b=0;
	    
     SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
    
	SET @query=CONCAT('SELECT IFNULL(MIN(`id`),0) as a, IFNULL(MAX(`id`),0) as b FROM
					   (SELECT `id` FROM `',`table`,'` WHERE deleted=false ORDER BY `id` DESC LIMIT ',@s,',',@ps,') as t	
                       INTO @a, @b ; '
					  );

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	
			   
    SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE deleted=false AND (id BETWEEN @a AND @b) ORDER BY `id` DESC');
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
    SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Article_LISTBYSEARCH` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Article_LISTBYSEARCH`(
  IN  `table` varchar(50),
  IN  `pageNo` INT,
  IN  `pageSize` INT,
  IN  `keyword` varchar(128)
)
BEGIN
   	SET @pn= `pageNo`;
	SET @ps= `pageSize`;	
	SET @s= (@pn-1)*@ps;

	SET @a=0;
    SET @b=0;
	    
    SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
        
	SET @query=CONCAT('SELECT IFNULL(MIN(`id`),0) as a, IFNULL(MAX(`id`),0) as b FROM
					   (SELECT `id` FROM `',`table`,'` WHERE ',`keyword`,' AND deleted=false ORDER BY `id` DESC  LIMIT ',@s,',',@ps,') as t	
                       INTO @a, @b ; '
					  );

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	
			   
    SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE (id BETWEEN @a AND @b) AND ',`keyword`,' AND deleted=false ORDER BY `id` DESC');
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
    SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Article_READ` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Article_READ`(
	IN  `table` VARCHAR(50),
	IN  `id`  INT
)
BEGIN
	SET @id = `id`;
	
    SET @query=CONCAT('UPDATE `',`table`,'` SET noOfRead=noOfRead+1 WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
	SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Article_UPD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Article_UPD`(
  IN `table` varchar(256),
  IN `id` int(11),
  IN `categoryId` int(2) ,  
  IN `title` varchar(256),
  IN `body` text,
  IN `createTime` timestamp,
  IN `updateTime` timestamp,
  IN `userId` INT,
  IN `noOfread` INT, 
  IN `deleted` BIT,
  IN `ip` varchar(40),
  IN `upvote` int,
  OUT `noOfRow` INT
)
BEGIN
  SET @id=`id`;
  SET @categoryId=`categoryId`;  
  SET @title=`title`;
  SET @body=`body`;
  SET @createTime=`createTime`;
  SET @updateTime=`updateTime`;
  SET @userId=`userId`;
  SET @noOfread=`noOfread`;
  SET @deleted=`deleted`;
  SET @ip=`ip`;
  SET @upvote=`upvote`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
					 SET 					 
                     title=@title      ,               
                     body=@body,
                     updateTime = @updateTime,
                     userId=@userId,
                     deleted=@deleted,
                     ip=@ip
					 WHERE 
					 id=@id
					 ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	

  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Create_TBL_Article` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Create_TBL_Article`(
IN `table` varchar(50)
)
BEGIN

SET @query=CONCAT('CREATE TABLE IF NOT EXISTS  `',`table`,'`  
				 LIKE `article_template`; '
				);

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Create_TBL_Comment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Create_TBL_Comment`(
IN `table` varchar(50)
)
BEGIN

SET @query=CONCAT('CREATE TABLE IF NOT EXISTS  `',`table`,'`  
				 LIKE `comment_template`; '
				);

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Drop_TBL_Article` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Drop_TBL_Article`(
IN `table` varchar(50)
)
BEGIN

SET @query=CONCAT('DROP TABLE `',`table`,'` ;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Drop_TBL_Comment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Drop_TBL_Comment`(
IN `table` varchar(50)
)
BEGIN

SET @query=CONCAT('DROP TABLE `',`table`,'` ;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_INS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_INS`(
  IN `table` varchar(256),
  IN `name` varchar(45),  
  IN `createTime` timestamp,
  IN `updateTime` timestamp,
  IN `deleted` BIT,
  OUT `resultId` INT
)
BEGIN
  SET @name=`name`;
  SET @createTime=`createTime`;
  SET @updateTime=`updateTime`;
  SET @deleted=`deleted`;

  SET @query=CONCAT('INSERT INTO `',`table`,'` 
				(
				name,createTime,updateTime,deleted
				)
				VALUES
				(
				@name,@createTime,@updateTime,@deleted
				);'
				);

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET `resultId`=LAST_INSERT_ID();
  

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Rename_TBL_Article` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Rename_TBL_Article`(
IN `oldTable` varchar(50), 
IN `newTable` varchar(50)
)
BEGIN

SET @query=CONCAT('RENAME TABLE `',`oldTable`,'` TO `',`newTable`,'`;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_Rename_TBL_Comment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_Rename_TBL_Comment`(
IN `oldTable` varchar(50), 
IN `newTable` varchar(50)
)
BEGIN

SET @query=CONCAT('RENAME TABLE `',`oldTable`,'` TO `',`newTable`,'`;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Category_UPD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Category_UPD`(
  IN `table` varchar(50),
  IN `id` int,
  IN `name` varchar(45),
  IN `updateTime` timestamp,
  IN `deleted` bit,
  OUT `noOfRow` INT
)
BEGIN
  
  SET @id     = `id`    ;
  SET @name=`name` ;
  SET @deleted=`deleted`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
				     SET 					 
					 name=@name,
                     updateTime=@updateTime,
                     deleted=@deleted                     
					 WHERE id=@id;
				    '
				);

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET @noOfRow=0;
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Comment_INS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Comment_INS`(
  IN `table` varchar(256),
  IN `articleId` int ,
  IN `categoryId` int(2) ,
  IN `body` text,
  IN `createTime` timestamp,
  IN `updateTime` timestamp,
  IN `userId` INT,
  IN `deleted` BIT,
  IN `ip` varchar(40),
  IN `upvote` int,
  OUT `resultId` INT
)
BEGIN
  SET @articleId=`articleId`;
  SET @categoryId=`categoryId`;  
  SET @body=`body`;
  SET @createTime=`createTime`;
  SET @updateTime=`updateTime`;
  SET @userId=`userId`;
  SET @deleted=`deleted`;
  SET @ip=`ip`;
  SET @upvote=`upvote`;
  
  SET @query=CONCAT('INSERT INTO `',`table`,'` 
				(articleId,categoryId,body,createTime,updateTime,userId,deleted,ip,upvote)
                VALUES
				(@articleId,@categoryId,@body,@createTime,@updateTime,@userId,@deleted,@ip,@upvote)              
				;');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET `resultId`=LAST_INSERT_ID();
  

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Comment_LIST` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Comment_LIST`(
  IN  `table` varchar(50),
  IN  `pageNo` INT,
  IN  `pageSize` INT
)
BEGIN
   	SET @pn= `pageNo`;
	SET @ps= `pageSize`;	
	SET @s= (@pn-1)*@ps;

	SET @a=0;
    SET @b=0;
	    
     SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
    
	SET @query=CONCAT('SELECT IFNULL(MIN(`id`),0) as a, IFNULL(MAX(`id`),0) as b FROM
					   (SELECT `id` FROM `',`table`,'` WHERE deleted=false ORDER BY `id` DESC LIMIT ',@s,',',@ps,') as t	
                       INTO @a, @b ; '
					  );

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	
			   
    SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE deleted=false AND (id BETWEEN @a AND @b) ORDER BY `id` DESC');
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
    SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Comment_READ` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Comment_READ`(
	IN  `table` VARCHAR(50),
	IN  `id`  INT
)
BEGIN
	SET @id = `id`;	
    
	SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Comment_UPD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Comment_UPD`(
  IN `table` varchar(256),
  IN `id` int(11),
  IN `articleId` int(11),
  IN `categoryId` int(2) ,  
  IN `body` text,
  IN `createTime` timestamp,
  IN `updateTime` timestamp,
  IN `userId` INT,
  IN `deleted` BIT,
  IN `ip` varchar(40),
  IN `upvote` int,
  OUT `noOfRow` INT
)
BEGIN
  SET @id=`id`;
  SET @articleId=`articleId`;
  SET @categoryId=`categoryId`;  
  SET @body=`body`;
  SET @createTime=`createTime`;
  SET @updateTime=`updateTime`;
  SET @userId=`userId`;
  SET @deleted=`deleted`;
  SET @ip=`ip`;
  SET @upvote=`upvote`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
					 SET 					 
                     body=@body,
                     updateTime = @updateTime,
                     userId=@userId,
                     deleted=@deleted,
                     ip=@ip
					 WHERE 
					 id=@id
					 ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	

  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteById`(
IN  `table` VARCHAR(50),
IN  `id`    INT,
OUT `noOfRow` INT
)
BEGIN
	SET @id   = `id`;
	SET @noOfRow ='';

	SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
    PREPARE stmt FROM @query2;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

	SET `noOfRow`=@noOfRow;

	SET @query=CONCAT('DELETE FROM `',`table`,'` WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteByUpdateId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteByUpdateId`(
  IN `table` varchar(256),
  IN `id` int(11),  
  OUT `noOfRow` INT
)
BEGIN
  SET @id=`id`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
					 SET 					                      
                     deleted=true
					 WHERE 
					 id=@id
					 ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	

  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_deleteById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_deleteById`(
IN  `table` VARCHAR(50),
IN  `id`    INT,
OUT `noOfRow` INT
)
BEGIN
	SET @id   = `id`;
	SET @noOfRow ='';

	SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
    PREPARE stmt FROM @query2;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

	SET `noOfRow`=@noOfRow;

	SET @query=CONCAT('DELETE FROM `',`table`,'` WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_deleteByUpdateId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_deleteByUpdateId`(
  IN `table` varchar(256),
  IN `id` int(11),  
  OUT `noOfRow` INT
)
BEGIN
  SET @id=`id`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
					 SET 					                      
                     deleted=true
					 WHERE 
					 id=@id
					 ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	

  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_getList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_getList`(
  IN  `table` varchar(50),
  IN  `pageNo` INT,
  IN  `pageSize` INT
)
BEGIN
   	SET @pn= `pageNo`;
	SET @ps= `pageSize`;	
	SET @s= (@pn-1)*@ps;

	SET @a=0;
    SET @b=0;
	    
    SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;    
        
	SET @query=CONCAT('SELECT IFNULL(MIN(`id`),0) as a, IFNULL(MAX(`id`),0) as b FROM
					   (SELECT `id` FROM `',`table`,'` ORDER BY `id` DESC LIMIT ',@s,',',@ps,') as t	
                       INTO @a, @b ; '
					  );

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	
			   
    SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE (id BETWEEN @a AND @b) ORDER BY `id` DESC');
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
    SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_getListAll` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_getListAll`(
IN `table` varchar(50)
)
BEGIN

	SET @query=CONCAT('SELECT * FROM `',`table`,'` ORDER BY `id` DESC ');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_getListBySearch` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_getListBySearch`(
  IN  `table` varchar(50),
  IN  `pageNo` INT,
  IN  `pageSize` INT,
  IN  `keyword` varchar(128)
)
BEGIN
   	SET @pn= `pageNo`;
	SET @ps= `pageSize`;	
	SET @s= (@pn-1)*@ps;

	SET @a=0;
    SET @b=0;
	    
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
        
	SET @query=CONCAT('SELECT IFNULL(MIN(`id`),0) as a, IFNULL(MAX(`id`),0) as b FROM
					   (SELECT `id` FROM `',`table`,'` WHERE ',`keyword`,' ORDER BY `id` DESC LIMIT ',@s,',',@ps,') as t	
                       INTO @a, @b ; '
					  );

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
	
			   
    SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE (id BETWEEN @a AND @b) AND ',`keyword`,' ORDER BY `id` DESC');
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	
    
    SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_getTotal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_getTotal`(
	IN  `table` VARCHAR(50),
    OUT `total` INT
)
BEGIN
	
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
    
	SET @query=CONCAT('SELECT COUNT(id) INTO @total FROM `',`table`,'`');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

	SET `total` = @total;

	SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_getTotalBySearch` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_getTotalBySearch`(
	IN  `table` VARCHAR(50),
    OUT `total` INT,
    IN  `keyword` VARCHAR(128)
)
BEGIN
	
    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED ;
    
	SET @query=CONCAT('SELECT COUNT(id) INTO @total FROM `',`table`,'` WHERE ', `keyword`);

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

	SET `total` = @total;

	SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ ;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_readByColumn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_readByColumn`(
	IN  `table` VARCHAR(50),
    IN  `columnName` VARCHAR(50),
	IN  `value`   VARCHAR(50)
)
BEGIN
		
	SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE `',`columnName`,'` = ''',`value`,''' ');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `G_readById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `G_readById`(
	IN  `table` VARCHAR(50),
	IN  `id`  INT
)
BEGIN
	SET @id = `id`;
	
	SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE id=@id;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `User_INS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_INS`(
  IN  `table` varchar(50),
  IN  `connectionId` varchar(255),
  IN  `providerId` varchar(255),
  IN  `providerConnectionId` varchar(255) ,
  IN  `rank` int(1) ,
  IN  `displayName` varchar(255) ,
  IN  `profileUrl` varchar(512) ,
  IN  `imageUrl` varchar(512) ,
  IN  `accessToken` varchar(512) ,
  IN  `secret` varchar(255) ,
  IN  `refreshToken` varchar(512) ,
  IN  `expireTime` bigint(20) ,
-- IN  `id` int(11) ,
  IN  `email` varchar(255) ,
  IN  `username` varchar(255) ,
  IN  `password` varchar(100) ,
  IN  `roleId` int(1) ,
  IN  `str` varchar(255) ,
  IN  `enabled` bit(1) ,
  IN  `notLocked` bit(1)  ,
  IN  `createTime` timestamp ,
  IN  `updateTime` timestamp ,
  IN  `trial` INT(1),
  OUT `resultId` INT
)
BEGIN

  SET @connectionId =`connectionId`;
  SET @providerId =  `providerId`;
  SET @providerConnectionId = `providerConnectionId`;
  SET @rank = `rank`;
  SET @displayName = `displayName`;
  SET @profileUrl = `profileUrl`;
  SET @imageUrl = `imageUrl`;
  SET @accessToken = `accessToken`;
  SET @secret = `secret`;
  SET @refreshToken = `refreshToken`;
  SET @expireTime = `expireTime`;
-- SET @id := `id`;
  SET @email = `email`;
  SET @username = `username`;
  SET @password = `password`;
  SET @roleId = `roleId`;
  SET @str = `str`;
  SET @enabled = `enabled`;
  SET @notLocked = `notLocked`;
  SET @createTime = `createTime`;
  SET @updateTime = `updateTime`;
  SET @trial=`trial`;
	
  SET @query=CONCAT('INSERT INTO `',`table`,'` 
				(connectionId,providerId,providerConnectionId,rank,displayName
				,profileUrl,imageUrl,accessToken,secret,refreshToken
				,expireTime
				,email,username,password,roleId,
				str,enabled,notLocked
				,createTime,updateTime,trial)
				VALUES
				(@connectionId,@providerId,@providerConnectionId,@rank,@displayName
				,@profileUrl,@imageUrl,@accessToken,@secret,@refreshToken
				,@expireTime,
				 @email,@username,@password,@roleId,
				 @str,@enabled,@notLocked
				,@createTime,@updateTime,@trial)');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET `resultId`=LAST_INSERT_ID();

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `User_readByEmail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_readByEmail`(
	IN  `table` VARCHAR(50),
    IN  `email` VARCHAR(100)
    )
BEGIN
		
	SET @email=`email`;
    
	SET @query=CONCAT('SELECT * FROM `',`table`,'` WHERE `email`=@email ;');

    PREPARE stmt FROM @query;
	EXECUTE stmt;	
    DEALLOCATE PREPARE stmt;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `User_UPD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_UPD`(
  IN  `table` varchar(50),
  IN  `connectionId` varchar(255),
  IN  `providerId` varchar(255),
  IN  `providerConnectionId` varchar(255) ,
  IN  `rank` int(1) ,
  IN  `displayName` varchar(255) ,
  IN  `profileUrl` varchar(512) ,
  IN  `imageUrl` varchar(512) ,
  IN  `accessToken` varchar(512) ,
  IN  `secret` varchar(255) ,
  IN  `refreshToken` varchar(512) ,
  IN  `expireTime` bigint(20) ,
  IN  `id` int(11) ,
  IN  `email` varchar(255) ,
  IN  `username` varchar(255) ,
  IN  `password` varchar(100) ,
  IN  `roleId` int(1) ,
  IN  `str` varchar(255) ,
  IN  `enabled` bit(1) ,
  IN  `notLocked` bit(1)  ,
  IN  `createTime` timestamp ,
  IN  `updateTime` timestamp ,
  OUT `noOfRow` INT
)
BEGIN

  SET @connectionId =`connectionId`;
  SET @providerId =  `providerId`;
  SET @providerConnectionId = `providerConnectionId`;
  SET @rank = `rank`;
  SET @displayName = `displayName`;
  SET @profileUrl = `profileUrl`;
  SET @imageUrl = `imageUrl`;
  SET @accessToken = `accessToken`;
  SET @secret = `secret`;
  SET @refreshToken = `refreshToken`;
  SET @expireTime = `expireTime`;
  SET @id = `id`;
  SET @email = `email`;
  SET @username = `username`;
  SET @password = `password`;
  SET @roleId = `roleId`;
  SET @str = `str`;
  SET @enabled = `enabled`;
  SET @notLocked = `notLocked`;
  SET @createTime = `createTime`;
  SET @updateTime = `updateTime`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
				SET 
                username=@username,
                roleId=@roleId,
                enabled=@enabled,
                notLocked=@notLocked,
                updateTime=@updateTime
                WHERE
                id=@id;
                ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `User_UPD_PWD` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_UPD_PWD`(
  IN  `table` varchar(50),
  IN  `id` int(11) ,
  IN  `email` varchar(255) ,
  IN  `password` varchar(100) ,
  IN `updateTime` timestamp,
  OUT `noOfRow` INT
)
BEGIN

  
  SET @id = `id`;
  SET @email = `email`;
  SET @password=`password`;
  SET @updateTime=`updateTime`;
	
  SET @query=CONCAT('UPDATE `',`table`,'` 
				SET 
                password=@password,
                updateTime=@updateTime
                WHERE
                id=@id OR email=@email;
                ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE id=@id OR email=@email;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `User_UPD_TRIAL` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `User_UPD_TRIAL`(
  IN  `table` varchar(50),
  IN  `email` varchar(255) ,
  IN `updateTime` timestamp,
  OUT `noOfRow` INT
)
BEGIN

  SET @email = `email`;
  SET @updateTime=`updateTime`;
  	
  SET @query=CONCAT('UPDATE `',`table`,'` 
				SET 
                trial=trial+1,
                updateTime=@updateTime  
                WHERE
                email=@email;
                ');

  PREPARE stmt FROM @query;
  EXECUTE stmt;	
  DEALLOCATE PREPARE stmt;	
  
  SET @noOfRow='';
  SET	@query2=CONCAT('SELECT COUNT(id) INTO @noOfRow FROM `',`table`,'` WHERE email=@email;');
  PREPARE stmt2 FROM @query2;
  EXECUTE stmt2;	
  DEALLOCATE PREPARE stmt2;	

  SET `noOfRow`=@noOfRow;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-28 13:31:03
