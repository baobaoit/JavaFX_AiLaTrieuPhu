CREATE DATABASE  IF NOT EXISTS `altp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `altp`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: altp
-- ------------------------------------------------------
-- Server version	5.7.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cau_hoi`
--

DROP TABLE IF EXISTS `cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cau_hoi` (
  `noi_dung` varchar(500) CHARACTER SET utf8 NOT NULL,
  `dapan_a` varchar(500) CHARACTER SET utf8 NOT NULL,
  `dapan_b` varchar(500) CHARACTER SET utf8 NOT NULL,
  `dapan_c` varchar(500) CHARACTER SET utf8 NOT NULL,
  `dapan_d` varchar(500) CHARACTER SET utf8 NOT NULL,
  `dapan` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `linh_vuc` int(11) NOT NULL,
  `muc_do` int(11) NOT NULL,
  `xoa` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`noi_dung`),
  KEY `fk_cauhoi_linhvuc_idx` (`linh_vuc`),
  KEY `fk_cauhoi_mucdo_idx` (`muc_do`),
  CONSTRAINT `fk_cauhoi_linhvuc` FOREIGN KEY (`linh_vuc`) REFERENCES `linh_vuc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cauhoi_mucdo` FOREIGN KEY (`muc_do`) REFERENCES `muc_do` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cau_hoi`
--

LOCK TABLES `cau_hoi` WRITE;
/*!40000 ALTER TABLE `cau_hoi` DISABLE KEYS */;
/*!40000 ALTER TABLE `cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linh_vuc`
--

DROP TABLE IF EXISTS `linh_vuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `linh_vuc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linh_vuc`
--

LOCK TABLES `linh_vuc` WRITE;
/*!40000 ALTER TABLE `linh_vuc` DISABLE KEYS */;
INSERT INTO `linh_vuc` VALUES (1,'Toán học'),(2,'Sinh học'),(3,'Văn học'),(4,'Khoa học'),(5,'Hoá học'),(6,'Ngoại ngữ'),(7,'Thể thao'),(8,'Ẩm thực'),(9,'Đời sống');
/*!40000 ALTER TABLE `linh_vuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muc_do`
--

DROP TABLE IF EXISTS `muc_do`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `muc_do` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muc_do`
--

LOCK TABLES `muc_do` WRITE;
/*!40000 ALTER TABLE `muc_do` DISABLE KEYS */;
INSERT INTO `muc_do` VALUES (1,'Dễ'),(2,'Trung bình'),(3,'Khó');
/*!40000 ALTER TABLE `muc_do` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoi_dung`
--

DROP TABLE IF EXISTS `nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nguoi_dung` (
  `tai_khoan` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(500) CHARACTER SET utf8 NOT NULL,
  `ho_ten` varchar(500) CHARACTER SET utf8 NOT NULL,
  `gioi_tinh` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nam_sinh` int(11) NOT NULL,
  `que_quan` varchar(500) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`tai_khoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung` VALUES ('admin','123456','Bao Le','Nam',1997,'Ho Chi Minh'),('user1','123456','User 1','Nu',1996,'Ha Noi');
/*!40000 ALTER TABLE `nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-19 15:27:50
