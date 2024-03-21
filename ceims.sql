-- MySQL dump 10.10
--
-- Host: localhost    Database: ceims
-- ------------------------------------------------------
-- Server version	5.0.17-nt

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
-- Table structure for table `campusnum`
--

DROP TABLE IF EXISTS `campusnum`;
CREATE TABLE `campusnum` (
  `Date` date NOT NULL,
  `XYQZ` int(10) unsigned NOT NULL,
  `WZZ` int(10) unsigned NOT NULL,
  `XYYS` int(10) unsigned NOT NULL,
  `XYZZ` int(10) unsigned NOT NULL,
  `LJQZ` int(10) unsigned NOT NULL,
  `JWSR` int(10) unsigned NOT NULL,
  `LJZY` int(10) unsigned NOT NULL,
  `LJSW` int(10) unsigned NOT NULL,
  `dXYQZ` int(11) NOT NULL,
  `dWZZ` int(11) NOT NULL,
  `dXYYS` int(11) NOT NULL,
  `dXYZZ` int(11) NOT NULL,
  `dLJQZ` int(11) NOT NULL,
  `dJWSR` int(11) NOT NULL,
  `dLJZY` int(11) NOT NULL,
  `dLJSW` int(11) NOT NULL,
  PRIMARY KEY  (`Date`),
  UNIQUE KEY `Date` (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `campusnum`
--


/*!40000 ALTER TABLE `campusnum` DISABLE KEYS */;
LOCK TABLES `campusnum` WRITE;
INSERT INTO `campusnum` VALUES ('2020-09-20',1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16),('2020-09-24',1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16),('2020-09-26',11,12,13,14,12,11,12,13,1,0,1,1,0,-1,-1,-1),('2020-09-27',100,200,13,5,500,20,400,0,1,0,1,1,0,-1,1,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `campusnum` ENABLE KEYS */;

--
-- Table structure for table `everydayreport`
--

DROP TABLE IF EXISTS `everydayreport`;
CREATE TABLE `everydayreport` (
  `ID` varchar(20) NOT NULL,
  `SubmitTime` datetime NOT NULL,
  `Tel` varchar(20) NOT NULL,
  `Addr` varchar(100) default NULL,
  `Temperature` double NOT NULL,
  `Remark` varchar(200) default NULL,
  PRIMARY KEY  (`ID`,`SubmitTime`),
  UNIQUE KEY `ID` (`ID`,`SubmitTime`),
  CONSTRAINT `everydayreport_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `everydayreport`
--


/*!40000 ALTER TABLE `everydayreport` DISABLE KEYS */;
LOCK TABLES `everydayreport` WRITE;
INSERT INTO `everydayreport` VALUES ('001','2020-09-27 14:25:19','13757142557','浙江杭州拱墅区认真的',36.9,'无'),('918106840206','2020-09-06 07:20:15','13757142557','宁静轩',36.8,NULL),('918106840206','2020-09-07 07:22:15','13757142557','宁静轩',36.8,'nothing'),('918106840206','2020-09-08 09:22:15','13757142557','宁静轩',36.8,'nothing'),('918106840206','2020-09-09 12:30:00','13757142557','宁静轩',36.8,'nothing'),('918106840206','2020-09-10 13:47:00','13757142557','宁静轩',36.8,'nothing'),('918106840206','2020-09-11 13:48:00','13757142557','宁静轩',36.8,'nothing'),('918106840206','2020-09-12 16:31:17','13757142557','宁静轩',37.1,'~'),('918106840206','2020-09-13 14:36:36','13757142557','宁静轩',37.8,'隔离'),('918106840229','2020-09-12 13:59:49','12345',NULL,36.8,'哈'),('918106840229','2020-09-13 09:26:20','12345','明理居',36.7,NULL),('918106840229','2020-09-24 14:44:11','13212345678','江苏南京玄武区南京理工大学明理居',36.9,'无'),('918106840229','2020-09-27 08:58:36','13212345678','江苏南京玄武区南京理工大学明理居',37,'你猜呀');
UNLOCK TABLES;
/*!40000 ALTER TABLE `everydayreport` ENABLE KEYS */;

--
-- Table structure for table `everydayrespond`
--

DROP TABLE IF EXISTS `everydayrespond`;
CREATE TABLE `everydayrespond` (
  `ID` varchar(20) NOT NULL,
  `SubmitTime` datetime NOT NULL,
  `ReplyTime` datetime NOT NULL,
  `AdminID` varchar(20) NOT NULL,
  `Msg` varchar(100) NOT NULL,
  PRIMARY KEY  (`ID`,`SubmitTime`),
  UNIQUE KEY `ID` (`ID`,`SubmitTime`),
  CONSTRAINT `everydayrespond_ibfk_1` FOREIGN KEY (`ID`, `SubmitTime`) REFERENCES `everydayreport` (`ID`, `SubmitTime`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `everydayrespond`
--


/*!40000 ALTER TABLE `everydayrespond` DISABLE KEYS */;
LOCK TABLES `everydayrespond` WRITE;
INSERT INTO `everydayrespond` VALUES ('918106840229','2020-09-12 13:59:49','2020-09-12 20:01:33','918106840206','good luck'),('918106840229','2020-09-24 14:44:11','2020-09-27 17:28:27','918106840206',''),('918106840229','2020-09-27 08:58:36','2020-09-27 11:09:46','918106840206','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `everydayrespond` ENABLE KEYS */;

--
-- Table structure for table `nationalnum`
--

DROP TABLE IF EXISTS `nationalnum`;
CREATE TABLE `nationalnum` (
  `Date` date NOT NULL,
  `XYQZ` int(10) unsigned NOT NULL,
  `WZZ` int(10) unsigned NOT NULL,
  `XYYS` int(10) unsigned NOT NULL,
  `XYZZ` int(10) unsigned NOT NULL,
  `LJQZ` int(10) unsigned NOT NULL,
  `JWSR` int(10) unsigned NOT NULL,
  `LJZY` int(10) unsigned NOT NULL,
  `LJSW` int(10) unsigned NOT NULL,
  `dXYQZ` int(11) NOT NULL,
  `dWZZ` int(11) NOT NULL,
  `dXYYS` int(11) NOT NULL,
  `dXYZZ` int(11) NOT NULL,
  `dLJQZ` int(11) NOT NULL,
  `dJWSR` int(11) NOT NULL,
  `dLJZY` int(11) NOT NULL,
  `dLJSW` int(11) NOT NULL,
  PRIMARY KEY  (`Date`),
  UNIQUE KEY `Date` (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `nationalnum`
--


/*!40000 ALTER TABLE `nationalnum` DISABLE KEYS */;
LOCK TABLES `nationalnum` WRITE;
INSERT INTO `nationalnum` VALUES ('2020-09-21',413,397,1,2,90879,2742,85722,4744,25,25,0,0,36,12,11,0),('2020-09-24',392,402,0,3,90925,2765,85788,4745,-20,20,0,0,10,7,29,1),('2020-09-26',379,400,2,3,90952,2788,85827,4746,-4,30,1,0,18,15,21,1),('2020-09-27',375,391,2,3,90972,2802,85851,4746,-2,26,0,0,15,14,17,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `nationalnum` ENABLE KEYS */;

--
-- Table structure for table `outreport`
--

DROP TABLE IF EXISTS `outreport`;
CREATE TABLE `outreport` (
  `OutReportID` int(10) unsigned NOT NULL auto_increment,
  `ID` varchar(20) NOT NULL,
  `OutDate` date NOT NULL,
  `PlannedBackDate` date NOT NULL,
  `Destination` varchar(100) NOT NULL,
  `ActualBackDate` date default NULL,
  PRIMARY KEY  (`OutReportID`),
  UNIQUE KEY `ID` (`ID`,`OutDate`,`PlannedBackDate`),
  CONSTRAINT `outreport_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `outreport`
--


/*!40000 ALTER TABLE `outreport` DISABLE KEYS */;
LOCK TABLES `outreport` WRITE;
INSERT INTO `outreport` VALUES (1,'918106840206','2020-09-09','2020-09-11','杭州','2020-09-12'),(2,'918106840229','2020-09-13','2020-09-14','新街口','2020-09-26'),(3,'918106840206','2020-09-13','2020-09-13','三号门','2020-09-13'),(11,'918106840206','2020-09-25','2020-09-27','浙江杭州拱墅区呵呵','2020-09-25'),(13,'918106840206','2020-09-26','2020-09-27','浙江杭州拱墅区呵呵','2020-09-26'),(14,'918106840229','2020-09-27','2020-09-28','江苏扬州仪征市哈哈','2020-09-29'),(15,'001','2020-09-12','2020-09-27','江苏南京玄武区才不告诉你我去哪儿','2020-09-26');
UNLOCK TABLES;
/*!40000 ALTER TABLE `outreport` ENABLE KEYS */;

--
-- Table structure for table `tes`
--

DROP TABLE IF EXISTS `tes`;
CREATE TABLE `tes` (
  `id` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tes`
--


/*!40000 ALTER TABLE `tes` DISABLE KEYS */;
LOCK TABLES `tes` WRITE;
INSERT INTO `tes` VALUES (2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tes` ENABLE KEYS */;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
CREATE TABLE `tour` (
  `OutReportID` int(10) unsigned NOT NULL,
  `ApproachTime` datetime NOT NULL,
  `Province` varchar(50) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Area` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  PRIMARY KEY  (`OutReportID`,`ApproachTime`),
  CONSTRAINT `tour_ibfk_1` FOREIGN KEY (`OutReportID`) REFERENCES `outreport` (`OutReportID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `tour`
--


/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
LOCK TABLES `tour` WRITE;
INSERT INTO `tour` VALUES (1,'2020-09-09 12:30:00','浙江','杭州','拱墅','大塘新村51-3-405'),(1,'2020-09-10 14:00:00','浙江','杭州','下城','杭州市中医院'),(2,'2020-09-26 10:30:00','江苏','南京','玄武','紫金商业街'),(2,'2020-09-26 11:30:00','江苏','南京','玄武','钟鼎名悦'),(3,'2020-09-13 12:00:00','江苏','南京','玄武','孝陵卫卫生服务中心'),(3,'2020-09-13 14:00:00','江苏','南京','玄武','紫金商业街'),(13,'2020-09-26 10:30:00','浙江','杭州','拱墅','大塘新村'),(13,'2020-09-26 11:30:00','浙江','杭州','江干','杭州东站'),(14,'2020-09-28 10:30:00','江苏','扬州','仪征','都市枫林'),(14,'2020-09-28 12:30:00','江苏','扬州','仪征','仪征中学'),(14,'2020-09-28 14:00:00','江苏','扬州','仪征','第二附属中学'),(15,'2020-09-25 10:30:00','浙江','杭州','江干','市民之家'),(15,'2020-09-25 11:30:00','浙江','台州','仙居','县人民医院'),(15,'2020-09-25 14:00:00','浙江','台州','仙居','安岭乡政府'),(15,'2020-09-25 17:00:00','浙江','杭州','江干','杭州东站');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(20) NOT NULL,
  `Pswd` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Department` varchar(50) default NULL,
  `IsAdmin` bit(1) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('001','111111','瞎写的','化工学院','\0'),('918106840206','991011','滕依筱','计算机科学与工程学院/人工智能学院',''),('918106840229','000000','马运国','计算机科学与工程学院/人工智能学院','\0'),('918106840234','123456','汤超','理学院','\0'),('918113370214','123','王耀仑','理学院','\0');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

