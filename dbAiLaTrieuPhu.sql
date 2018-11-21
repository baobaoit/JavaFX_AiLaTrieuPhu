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
INSERT INTO `cau_hoi` VALUES ('\"Bu\" là gì?','Bà','Mẹ','Ông','Cha','B',9,1,0),('\"El Nino\" là gì?','Một vũ công chuyên nghiệp','Một khu rừng ở châu phi','Một điệu nhảy','Một hiện tượng thời tiết','D',9,1,0),('\"Trở về Eden\" là tiểu thuyết của tác giả nào?','Margaret Atwood','Steve Jobs','Rosalind Miles','Tim Cook','C',3,3,0),('1 + 1 = ?','0','2','69','10','B',1,1,0),('1 x 0 = ??','1','0','4','3','B',1,1,1),('2 + 2 = ?','0','2','3','4','D',1,1,0),('Ai là vị vua cuối cùng của triều đại nhà Mạc?','Mạc Văn Khoa','Mạc Đĩnh Chi','Mạc Từ Tốn','Mạc Toàn','D',13,3,0),('Bảy chú lùn trong \"Bạch Tuyết và bảy chú lùn\" làm nghề gì?','Thợ rèn','Thợ may','Thợ săn','Thợ mỏ','D',3,2,0),('Biểu tượng của thành phố Vác-xa-va, thủ đô Ba Lan là biểu tượng mang hình gì?','Một vị thánh','Một chú sư tử','Một nàng tiên','Một nàng tiên cá','D',9,3,0),('Hang động lớn nhất thế giới nằm ở đâu?','Malaysia','Chile','Việt Nam','Nam Phi','C',9,2,0),('Làng cổ Phước Tích ở Huế có nghề truyền thống gì?','Làm áo dài','Làm gốm','Làm tranh thờ cúng','Làm nước mắm','B',9,3,0),('Màu nào sau đây không có trên lá cờ Olympic','Đen','Tím','Đỏ','Xanh dương','B',9,2,0),('Người ta thường nấu canh cua với thứ gì?','Củ cải','Rau đay','Mộc nhĩ','Quả óc chó','B',8,2,0),('Quần đảo Nam Du thuộc tỉnh nào nước ta?','Cà Mau','Kiên Giang','Bạc Liêu','Trà Vinh','B',12,2,0),('Sông Trà Khúc nằm ở tỉnh nào?','Quảng Ngãi','Thanh Hoá','Quảng Bình','Nghệ An','A',12,3,0),('Tác phẩm \"Đời thừa\" của Nam Cao lần đầu tiên được đăng trên báo nào?','Tiểu thuyết thứ bảy','Ngày nay','Phụ nữ tân văn','Tiểu thuyết tình yêu','A',9,3,0),('Theo dân gian, sông Hương có mùi thơm là do loài cây nào mọc ở hai bên bờ?','Thạch xương bồ','Bồ công anh','Thạch thảo','Dạ hương','A',9,3,0),('Theo một câu hát \"Ba thương con vì con giống mẹ, mẹ thương con vì con giống ...\" ai?','Ông hàng xóm','Chú cạnh nhà','Ba','Bác đầu ngõ','C',11,1,0),('Thương cảng Vân Đồn cổ xưa của nước Đại Việt được thành lập dưới thời vị vua nào?','Lý Thánh Tông','Lý Nhân Tông','Lý Anh Tông','Lý Cao Tông','C',13,3,0),('Trong bàn cờ tướng có bao nhiêu quân Mã?','1','2','3','4','B',9,1,0),('Trong hệ nhị phân, 1 + 1 = ?','2','0','1010','10','D',10,1,0),('Đất nước nào nằm ở Châu Âu?','Trung Quốc','Đông Timo','Ai Cập','Pháp','D',12,2,0),('Đâu là loại cháo khác với các món còn lại?','Cháo bò','Cháo gà','Cháo heo','Cháo vịt','C',9,2,0),('Đâu là một sáng tác của Sơn Tùng M-TP','Nơi này có chị','Nơi này có anh','Nơi này có chú','Nơi này có cha','B',11,1,0),('Đâu là một tác phẩm Opera của nhà soạn kịch người Nga Pyotr Ilyich Tchaikovsky','Hồ Thiên Nga','Cô gái Orleans','Người đẹp ngủ trong rừng','Romeo và Juliet','B',3,3,0),('Đâu là tên một loại mũ?','Lưỡi hái','Lưỡi lê','Lưỡi trai','Lưỡi bò','C',9,2,0),('Đâu là tên một truyện cổ tích?','Công chúa ngủ trong rừng','Công chúa ngủ trong tủ','Công chúa ngủ trên giường','Công chúa bị mất ngủ','A',3,1,0),('Điền vào chỗ trống trong câu sau: \"Gieo gió gặt ...\"','Lũ','Mưa','Giông','Bão','D',9,1,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linh_vuc`
--

LOCK TABLES `linh_vuc` WRITE;
/*!40000 ALTER TABLE `linh_vuc` DISABLE KEYS */;
INSERT INTO `linh_vuc` VALUES (1,'Toán học'),(2,'Sinh học'),(3,'Văn học'),(4,'Khoa học'),(5,'Hoá học'),(6,'Ngoại ngữ'),(7,'Thể thao'),(8,'Ẩm thực'),(9,'Đời sống'),(10,'Tin học'),(11,'Âm nhạc'),(12,'Địa lý'),(13,'Lịch sử');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
INSERT INTO `nguoi_dung` VALUES ('admin','123456','Bảo Lê','Nam',1997,'Hồ Chí Minh'),('user1','123456','User Một','Nam',1996,'Hà Nội');
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

-- Dump completed on 2018-11-21 16:47:11
