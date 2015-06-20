/*
SQLyog Ultimate v9.62 
MySQL - 5.5.43-0ubuntu0.14.04.1 : Database - nfc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nfc` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nfc`;

/*Table structure for table `CATEGORY` */

DROP TABLE IF EXISTS `CATEGORY`;

CREATE TABLE `CATEGORY` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `SHORTNAME` varchar(20) DEFAULT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `SORTORDER` char(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `CATEGORY` */

insert  into `CATEGORY`(`ID`,`SHORTNAME`,`FULLNAME`,`SORTORDER`) values (1,'VEG','Veg','11000'),(2,'NONVEG','Non-Veg','12000'),(3,'BEV','Beverages','13000'),(4,'DESERTS','Desserts','14000'),(5,'DOD','Deal of Day','99000');

/*Table structure for table `ERROR` */

DROP TABLE IF EXISTS `ERROR`;

CREATE TABLE `ERROR` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REASON` varchar(100) NOT NULL,
  `CREATED_ON` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ERROR` */

/*Table structure for table `MENU` */

DROP TABLE IF EXISTS `MENU`;

CREATE TABLE `MENU` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for the dish.',
  `NAME` varchar(50) NOT NULL COMMENT 'Name of the dish',
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL COMMENT 'How much quantity is avaiable for this dish',
  `AMOUNT` decimal(6,2) NOT NULL COMMENT 'Cost of the dish till 2 decimal digits',
  `PREP_TIME` int(11) DEFAULT NULL COMMENT 'How much time its required to prepare a dish.',
  `CATEGORY` varchar(20) NOT NULL COMMENT 'Category identify the menu item falls under which category',
  `URL` varchar(100) DEFAULT NULL COMMENT 'Item image stored in our public server',
  `SORTORDER` char(5) DEFAULT '00000',
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'When this menu item is created',
  `CREATED_BY` varchar(20) NOT NULL COMMENT 'Who created this menu item.',
  `MODIFIED_ON` timestamp NULL DEFAULT NULL COMMENT 'When this menu item is modified.',
  `MODIFIED_BY` varchar(20) DEFAULT NULL COMMENT 'Who modified this menu item.',
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  `CLOSED` tinyint(1) DEFAULT '0',
  `DISCOUNT` int(3) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `MENU` */

insert  into `MENU`(`ID`,`NAME`,`DESCRIPTION`,`QUANTITY`,`AMOUNT`,`PREP_TIME`,`CATEGORY`,`URL`,`SORTORDER`,`CREATED_ON`,`CREATED_BY`,`MODIFIED_ON`,`MODIFIED_BY`,`MERCHANT_ID`,`CLOSED`,`DISCOUNT`) values (3,'Dal Makhni','A spicy and heavy dal preparation made with a combination of rajma and urad dal.',10,'1.00',10,'VEG','http://www.nikata.in/demo/dal.jpg','11000','2015-05-01 22:24:50','Gaurav',NULL,NULL,2000,0,0),(4,'Shahi Paneer','Tomato based spicy gravy laced with Indian curry spices.',10,'1.00',10,'VEG','http://www.nikata.in/demo/paneer.jpg','11000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(5,'Mix Veg','Grilled flavor of vegetables with this lightly sweet and spicy blend of seasonings.',10,'1.00',18,'VEG','http://www.nikata.in/demo/mixveg.jpg','11000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(6,'Lemon Rice','Lemon juice gives a very refreshing and tangy flavor to the rice.',10,'1.00',16,'VEG','http://www.nikata.in/demo/rice.jpg','11000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(7,'Roomali Roti','A soft, thin delicious roti prepared using maida and wheat flour.',10,'1.00',15,'VEG','http://www.nikata.in/demo/roti.jpg','11000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(8,'Lobster Thermidor','Creamy mixture of cooked lobster meat, egg yolks, and brandy, stuffed into a lobster shell.',10,'1.00',20,'NONVEG','http://www.nikata.in/demo/lob.jpg','12000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(9,'Fish Fry','Meal containing battered or breaded fried fish.',10,'1.00',21,'NONVEG','http://www.nikata.in/demo/fish.jpg','12000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(10,'Chicken Briyan','Chicken Biryani is a biryani dish made with basmati rice, spices and chicken.',10,'1.00',12,'NONVEG','http://www.nikata.in/demo/chicknbriy.jpg','12000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(11,'Virgin Mojito','An icy, carbonated beverage with a Sprite base, mixed with mint flavoured syrup.',10,'1.00',8,'BEV','http://www.nikata.in/demo/vm.jpg','13000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(12,'Masala Lemonade','Refreshing and comes with a lot of health and beauty benefits too.',10,'1.00',5,'BEV','http://www.nikata.in/demo/ml.jpg','13000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(13,'Diet Coke ','Coke is a sugar-free soft drink produced and distributed by The Coca-Cola Company.',10,'1.00',0,'BEV','null','null','2015-05-02 21:41:36','Gaurav','2015-05-02 00:00:00','Jitendra Mathur',2000,0,0),(14,'Cappuccino','Traditionally prepared with espresso, hot milk and steamed milk foam.',10,'1.00',5,'BEV','http://www.nikata.in/demo/coff.jpg','13000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(15,'Chocolate chip cookie sunday','Have both semi-sweet dark and milk chocolate chunks.',10,'1.00',4,'DESSERTS','http://www.nikata.in/demo/cccs.jpg','14000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(16,'Bread and Butter Pudding','Made by layering slices of buttered bread scattered with raisins in an oven dish.',10,'1.00',6,'DESSERTS','http://www.nikata.in/demo/bbp.jpg','14000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(17,'Chocolate Cake','Rich chocolate mousse, hazelnuts, and chocolate ganache.',10,'1.00',7,'DESSERTS','http://www.nikata.in/demo/ccake.jpg','14000','2015-05-02 21:41:36','Gaurav',NULL,NULL,2000,0,0),(22,'Daal Fry','Daal fry made with tadka',100,'1.00',10,'VEG','null','11000','2015-05-04 20:21:53','Kamal Joshi','2015-05-04 00:00:00','Jitendra Mathur',2000,0,0),(23,'Item1','item desc',1,'20.00',1,'VEG','null','11000','2015-05-04 20:36:06','Jeet Mathur','2015-05-04 00:00:00','Jeet Mathur',2001,0,0);

/*Table structure for table `MERCHANT` */

DROP TABLE IF EXISTS `MERCHANT`;

CREATE TABLE `MERCHANT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(50) DEFAULT NULL,
  `LASTNAME` varchar(50) DEFAULT NULL,
  `BUSINESSNAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `MOBILE` varchar(20) DEFAULT NULL,
  `LANDLINE` varchar(20) DEFAULT NULL,
  `TABLECOUNT` decimal(5,0) DEFAULT '0',
  `PAYMENTOPTION` tinyint(1) DEFAULT '0',
  `VALIDFROM` date DEFAULT NULL,
  `VALIDTO` date DEFAULT NULL,
  `CITY` varchar(50) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  `PINCODE` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(50) DEFAULT NULL,
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(50) DEFAULT NULL,
  `MODIFIED_ON` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MODIFIED_BY` varchar(50) DEFAULT NULL,
  `IP_ADDRESS` varchar(20) DEFAULT NULL,
  `MERCHANTTYPE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=2004 DEFAULT CHARSET=latin1;

/*Data for the table `MERCHANT` */

insert  into `MERCHANT`(`ID`,`FIRSTNAME`,`LASTNAME`,`BUSINESSNAME`,`EMAIL`,`MOBILE`,`LANDLINE`,`TABLECOUNT`,`PAYMENTOPTION`,`VALIDFROM`,`VALIDTO`,`CITY`,`STATE`,`PINCODE`,`WEBSITE`,`CREATED_ON`,`CREATED_BY`,`MODIFIED_ON`,`MODIFIED_BY`,`IP_ADDRESS`,`MERCHANTTYPE`) values (2000,'Jitendra','Mathur','Apple','jeet427@gmail.com','9898989898','989898989898','2',1,'2015-05-07','2015-05-31','Delhi','New Delhi','110088','www.nikata.in','2015-05-07 00:00:00','Gaurav Oli','2015-05-07 00:00:00','Gaurav Oli','127.0.0.1',2),(2001,'Gaurav','Oli','Nikata','gauravoli@live.in','9891738383','9891738383','0',0,'2015-05-05','2015-05-05','Delhi','New Delhi','110088','www.nikata.in','2015-05-07 13:49:42',NULL,'2015-05-07 13:50:04','Gaurav Oli','127.0.0.1',1),(2002,'Abhishek','Sharma','Mystry','abhishek@gmail.com','9898989898','8989898989','5',2,'2015-05-05','2015-05-05','Delhi','New Delhi','110088','www.google.com','2015-05-05 00:00:00','Gaurav Oli','2015-05-05 00:00:00','Gaurav Oli','127.0.0.1',3);

/*Table structure for table `NOTIFICATION` */

DROP TABLE IF EXISTS `NOTIFICATION`;

CREATE TABLE `NOTIFICATION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` varchar(40) NOT NULL,
  `IS_NOTIFIED` tinyint(1) NOT NULL,
  `MERCHANT_ID` int(11) NOT NULL,
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `MODIFIED_ON` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `NOTIFICATION` */

insert  into `NOTIFICATION`(`ID`,`ORDER_ID`,`IS_NOTIFIED`,`MERCHANT_ID`,`CREATED_ON`,`MODIFIED_ON`) values (1,'81336ce3-8d37-4c1c-9d58-f1aa8e038efe',0,2000,'2015-05-06 13:17:54','2015-05-06 13:17:54'),(2,'81336ce3-8d37-4c1c-9d58-f1aa8e038eff',0,2000,'2015-05-06 13:51:44','2015-05-06 13:17:54'),(3,'81336ce3-8d37-4c1c-9d58-f1aa8e038efe',0,2000,'2015-05-06 13:17:54','2015-05-06 13:17:54'),(4,'2c94c7d9-15cd-4367-9a5c-9fc989c07820',0,2000,'2015-05-06 16:13:30','2015-05-06 16:13:30'),(5,'2c94c7d9-15cd-4367-9a5c-9fc989c07820',0,2000,'2015-05-06 16:14:04','2015-05-06 16:14:04');

/*Table structure for table `ORDERS` */

DROP TABLE IF EXISTS `ORDERS`;

CREATE TABLE `ORDERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for Order.',
  `ORDER_ID` varchar(40) NOT NULL COMMENT 'This order id will remain common for complete order.',
  `TABLE_ID` char(5) DEFAULT NULL,
  `MENU_ID` int(11) NOT NULL COMMENT 'Menu IDs are stored here as comma seprated values. As we can have multiple menu id for the same order.',
  `QUANTITY` int(11) NOT NULL COMMENT 'Quantity of the menu item',
  `AMOUNT` decimal(6,2) NOT NULL COMMENT 'This amount doesn''t include S.Tax & VAT till 2 decimal digits.',
  `STATUS` varchar(8) DEFAULT NULL,
  `CREATED_ON` timestamp NULL DEFAULT NULL COMMENT 'This is the date when this order is placed.',
  `MODIFIED_ON` timestamp NULL DEFAULT NULL COMMENT 'This is the date when this order is modified.',
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

/*Data for the table `ORDERS` */

insert  into `ORDERS`(`ID`,`ORDER_ID`,`TABLE_ID`,`MENU_ID`,`QUANTITY`,`AMOUNT`,`STATUS`,`CREATED_ON`,`MODIFIED_ON`,`MERCHANT_ID`) values (1,'b770ab2b-5879-42de-931b-8d1eba121c5a','5001',12,4,'125.00','INACTIVE','2015-04-12 21:33:45','2015-05-02 21:33:45',2000),(2,'b770ab2b-5879-42de-931b-8d1eba121c5h','5001',3,1,'11.00','INACTIVE','2015-04-21 21:41:55','2015-05-02 21:41:55',2000),(3,'b770ab2b-5879-42de-931b-8d1eba121c5g','5001',8,2,'41.00','INACTIVE','2015-04-19 21:41:55','2015-05-02 21:41:55',2000),(4,'b770ab2b-5879-42de-931b-8d1eba121c5g','5001',7,2,'122.00','INACTIVE','2015-04-19 21:41:55','2015-05-02 21:41:55',2000),(5,'b770ab2b-5879-42de-931b-8d1eba121c5f','5001',6,1,'41.00','INACTIVE','2015-04-18 21:41:55','2015-05-02 21:41:55',2000),(6,'b770ab2b-5879-42de-931b-8d1eba121c5c','5001',6,1,'123.00','INACTIVE','2015-04-16 22:40:43','2015-05-02 22:40:43',2000),(7,'b770ab2b-5879-42de-931b-8d1eba121c5a','5001',15,1,'89.00','INACTIVE','2015-04-12 22:40:43','2015-05-02 22:40:43',2000),(8,'b770ab2b-5879-42de-931b-8d1eba121c5b','5001',16,3,'134.00','INACTIVE','2015-04-14 22:40:43','2015-05-02 22:40:43',2000),(9,'b770ab2b-5879-42de-931b-8d1eba121c5b','5001',17,2,'143.00','INACTIVE','2015-04-14 22:40:43','2015-05-02 22:40:43',2000),(10,'b770ab2b-5879-42de-931b-8d1eba121c5b','5001',14,3,'156.00','INACTIVE','2015-04-14 22:40:43','2015-05-02 22:40:43',2000),(11,'b770ab2b-5879-42de-931b-8d1eba121c5c','5001',10,3,'453.00','INACTIVE','2015-04-16 22:40:43','2015-05-02 22:40:43',2000),(12,'b770ab2b-5879-42de-931b-8d1eba121c5a','5001',13,1,'145.00','INACTIVE','2015-04-12 22:40:43','2015-05-02 22:40:43',2000),(13,'b770ab2b-5879-42de-931b-8d1eba121c5d','5001',12,1,'1.00','INACTIVE','2015-04-17 22:40:43','2015-05-02 22:40:43',2000),(14,'b770ab2b-5879-42de-931b-8d1eba121c5d','5001',16,4,'1.00','INACTIVE','2015-04-17 22:40:43','2015-05-02 22:40:43',2000),(15,'b770ab2b-5879-42de-931b-8d1eba121c5d','5001',9,1,'41.00','INACTIVE','2015-04-17 06:35:36','2015-05-03 06:35:36',2000),(16,'b770ab2b-5879-42de-931b-8d1eba121c5f','5001',3,2,'231.00','INACTIVE','2015-04-18 11:17:21','2015-05-03 11:17:21',2000),(17,'b770ab2b-5879-42de-931b-8d1eba121c5f','5001',4,3,'1.00','INACTIVE','2015-04-18 11:17:21','2015-05-03 11:17:21',2000),(18,'eb0dc5c6-cc9d-4650-8c5e-f3cde8a3902c','5001',11,1,'100.00','INACTIVE','2015-04-11 13:02:50','2015-05-06 13:02:50',2000),(19,'1beb810a-ac0c-4d74-89cf-a6bfa46e86a2','5001',15,1,'111.00','ACTIVE','2015-05-06 13:04:19','2015-05-06 13:04:19',2000),(20,'1beb810a-ac0c-4d74-89cf-a6bfa46e86a2','5001',13,1,'134.00','ACTIVE','2015-05-06 13:05:34','2015-05-06 13:05:34',2000),(21,'332f1d98-44ba-456b-b386-b73973917cai','5001',6,3,'134.00','INACTIVE','2015-04-23 13:14:18','2015-05-06 13:14:18',2000),(22,'81336ce3-8d37-4c1c-9d58-f1aa8e038efi','5001',3,2,'12.00','INACTIVE','2015-04-23 13:17:30','2015-05-06 13:17:30',2000),(23,'2c94c7d9-15cd-4367-9a5c-9fc989c07820','5001',8,3,'111.00','INACTIVE','2015-04-25 16:13:13','2015-05-06 16:13:13',2000),(24,'2c94c7d9-15cd-4367-9a5c-9fc989c07820','5001',7,5,'231.00','INACTIVE','2015-04-25 16:14:00','2015-05-06 16:14:00',2000),(25,'eb0dc5c6-cc9d-4650-8c5e-f3cde8a3902c','5001',9,3,'89.00','INACTIVE','2015-04-11 13:02:50','2015-05-06 13:02:50',2000),(26,'eb0dc5c6-cc9d-4650-8c5e-f3cde8a3902c','5001',7,1,'13.00','INACTIVE','2015-04-11 13:02:50','2015-05-06 13:02:50',2000),(27,'eb0dc5c6-cc9d-4650-8c5e-f3cde8a3902c','5001',4,2,'12.00','INACTIVE','2015-04-11 13:02:50','2015-05-06 13:02:50',2000),(28,'2c94c7d9-15cd-4367-9a5c-9fc989c07821','5001',17,2,'111.00','INACTIVE','2015-04-26 16:13:13','2015-05-06 16:13:13',2000),(29,'2c94c7d9-15cd-4367-9a5c-9fc989c07821','5001',13,1,'12.00','INACTIVE','2015-04-26 16:13:13','2015-05-06 16:13:13',2000),(30,'2c94c7d9-15cd-4367-9a5c-9fc989c07821','5001',11,2,'31.00','INACTIVE','2015-04-26 16:13:13','2015-05-06 16:13:13',2000),(31,'2c94c7d9-15cd-4367-9a5c-9fc989c07827','5001',12,5,'12.00','INACTIVE','2015-04-28 16:13:13','2015-05-06 16:13:13',2000),(32,'2c94c7d9-15cd-4367-9a5c-9fc989c07827','5001',16,4,'41.00','INACTIVE','2015-04-28 16:13:13','2015-05-06 16:13:13',2000),(33,'2c94c7d9-15cd-4367-9a5c-9fc989c07827','5001',4,3,'31.00','INACTIVE','2015-04-28 16:13:13','2015-05-06 16:13:13',2000),(34,'2c94c7d9-15cd-4367-9a5c-9fc989c07844','5001',5,2,'85.00','INACTIVE','2015-05-05 16:13:13','2015-05-06 16:13:13',2000),(35,'2c94c7d9-15cd-4367-9a5c-9fc989c07843','5001',7,3,'167.00','INACTIVE','2015-05-04 16:13:13','2015-05-06 16:13:13',2000),(36,'2c94c7d9-15cd-4367-9a5c-9fc989c07843','5001',6,2,'134.00','INACTIVE','2015-05-04 16:13:13','2015-05-06 16:13:13',2000),(37,'2c94c7d9-15cd-4367-9a5c-9fc989c07811','5001',8,1,'85.00','INACTIVE','2015-05-03 16:13:13','2015-05-06 16:13:13',2000),(38,'2c94c7d9-15cd-4367-9a5c-9fc989c07811','5001',6,3,'76.00','INACTIVE','2015-05-03 16:13:13','2015-05-06 16:13:13',2000),(39,'2c94c7d9-15cd-4367-9a5c-9fc989c07811','5001',4,2,'64.00','INACTIVE','2015-05-03 16:13:13','2015-05-06 16:13:13',2000),(40,'2c94c7d9-15cd-4367-9a5c-9fc989c07811','5001',3,5,'432.00','INACTIVE','2015-05-03 16:13:13','2015-05-06 16:13:13',2000),(41,'2c94c7d9-15cd-4367-9a5c-9fc989c07829','5001',6,2,'321.00','INACTIVE','2015-05-02 16:13:13','2015-05-06 16:13:13',2000),(42,'2c94c7d9-15cd-4367-9a5c-9fc989c07829','5001',16,2,'121.00','INACTIVE','2015-05-02 16:13:13','2015-05-06 16:13:13',2000),(43,'2c94c7d9-15cd-4367-9a5c-9fc989c07829','5001',13,1,'11.00','INACTIVE','2015-05-02 16:13:13','2015-05-06 16:13:13',2000),(44,'2c94c7d9-15cd-4367-9a5c-9fc989c07828','5001',7,2,'1423.00','INACTIVE','2015-04-30 16:13:13','2015-05-06 16:13:13',2000),(45,'2c94c7d9-15cd-4367-9a5c-9fc989c07828','5001',5,3,'31.00','INACTIVE','2015-04-30 16:13:13','2015-05-06 16:13:13',2000),(46,'2c94c7d9-15cd-4367-9a5c-9fc989c07828','5001',4,5,'111.00','INACTIVE','2015-04-30 16:13:13','2015-05-06 16:13:13',2000),(47,'2c94c7d9-15cd-4367-9a5c-9fc989c07848','5001',8,3,'64.00','INACTIVE','2015-05-06 16:13:13','2015-05-06 16:13:13',2000),(48,'2c94c7d9-15cd-4367-9a5c-9fc989c07848','5001',7,2,'111.00','INACTIVE','2015-05-06 16:13:13','2015-05-06 16:13:13',2000),(49,'2c94c7d9-15cd-4367-9a5c-9fc989c07844','5001',17,1,'31.00','INACTIVE','2015-05-05 16:13:13','2015-05-06 16:13:13',2000),(50,'2c94c7d9-15cd-4367-9a5c-9fc989c07844','5001',6,4,'134.00','INACTIVE','2015-05-05 16:13:13','2015-05-06 16:13:13',2000);

/*Table structure for table `PMENU` */

DROP TABLE IF EXISTS `PMENU`;

CREATE TABLE `PMENU` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `URI` varchar(80) DEFAULT NULL,
  `ROLE_ID` tinyint(1) DEFAULT '0',
  `IDENTIFIER` char(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `PMENU` */

insert  into `PMENU`(`ID`,`NAME`,`URI`,`ROLE_ID`,`IDENTIFIER`) values (1,'Dashboard','/admin/dashboardView',1,'1100000000'),(2,'Merchants','',1,'1200000000'),(3,'Add Merchant','/views/admin/addMerchant.html',1,'1211000000'),(4,'List Merchants','/admin/getMerchantList?msg',1,'1212000000'),(5,'Transaction','',1,'1300000000'),(6,'View Transaction','',1,'1311000000'),(7,'Orders','',1,'1400000000'),(8,'View Orders','',1,'1411000000'),(9,'Analyse Orders','',1,'1412000000'),(10,'Transaction','/merchant/transactions',2,'2100000000'),(15,'Logout','/logout',9,'8100000000'),(16,'Manage Menu','/merchant/addMenu',2,'2500000000'),(17,'Manage Orders','',2,'2200000000'),(18,'Order History','/merchant/orders',2,'2211000000'),(19,'Active Orders','/merchant/activeOrder',2,'2212000000'),(20,'Manage Menu','/merchant/addMenu',3,'3100000000');

/*Table structure for table `ROLE` */

DROP TABLE IF EXISTS `ROLE`;

CREATE TABLE `ROLE` (
  `ID` int(11) NOT NULL,
  `ROLENAME` varchar(45) DEFAULT NULL,
  `GRANTID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `rolename_UNIQUE` (`ROLENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ROLE` */

insert  into `ROLE`(`ID`,`ROLENAME`,`GRANTID`) values (1,'Admin',NULL),(2,'Merchant',NULL),(3,'Passive Merchant',NULL);

/*Table structure for table `STATUS` */

DROP TABLE IF EXISTS `STATUS`;

CREATE TABLE `STATUS` (
  `ID` int(5) NOT NULL AUTO_INCREMENT,
  `MESSAGE` varchar(50) DEFAULT NULL,
  `CODE` int(4) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `STATUS` */

insert  into `STATUS`(`ID`,`MESSAGE`,`CODE`,`DESCRIPTION`) values (1,'ACTIVE',0,'Table is occupied'),(2,'INACTIVE',1,'Table is free'),(3,'SUCCESS',200,'Request completed successfully'),(4,'FAIL',400,'Request failed');

/*Table structure for table `TABLES` */

DROP TABLE IF EXISTS `TABLES`;

CREATE TABLE `TABLES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TID` char(5) DEFAULT NULL,
  `STATUS` decimal(3,0) DEFAULT NULL,
  `RESERVED` tinyint(1) DEFAULT '0',
  `MERCHANT_ID` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `TABLES` */

insert  into `TABLES`(`ID`,`TID`,`STATUS`,`RESERVED`,`MERCHANT_ID`) values (16,'T1','0',0,2000),(17,'T2','0',0,2000),(18,'T3','0',0,2000),(19,'T4','0',0,2000),(20,'T5','0',0,2000),(31,'T1','0',0,2001),(32,'T2','0',0,2001),(33,'T3','0',0,2001),(34,'T4','0',0,2001),(35,'T5','0',0,2001),(36,'T1','0',0,2002),(37,'T2','0',0,2002),(38,'T3','0',0,2002),(39,'T4','0',0,2002),(40,'T5','0',0,2002),(41,'T1','0',0,2003),(42,'T2','0',0,2003);

/*Table structure for table `TXNHISTORY` */

DROP TABLE IF EXISTS `TXNHISTORY`;

CREATE TABLE `TXNHISTORY` (
  `TXN_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` varchar(40) NOT NULL,
  `TAX_AMT` double DEFAULT NULL,
  `SHIPPING_AMT` double DEFAULT NULL,
  `ORDER_AMT` double DEFAULT NULL,
  `TOTAL_AMT` double DEFAULT NULL,
  `BANK_TXN_ID` varchar(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATED_ON` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `MERCHANT_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TXN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

/*Data for the table `TXNHISTORY` */

insert  into `TXNHISTORY`(`TXN_ID`,`ORDER_ID`,`TAX_AMT`,`SHIPPING_AMT`,`ORDER_AMT`,`TOTAL_AMT`,`BANK_TXN_ID`,`STATUS`,`CREATED_ON`,`CREATED_BY`,`MERCHANT_ID`) values (4,'6549ea53-8506-4e73-807a-b0536474e575',0,0,0,862,'null',111,'2015-05-15 11:50:53','System',2000),(8,'7d148a03-a104-4fd1-8b42-dc20d1caa2b1',0,0,0,4,'null',111,'2015-04-12 22:16:14','System',2000),(9,'6549ea53-8506-4e73-807a-b0536474e573',0,0,0,100,NULL,111,'2015-04-17 11:50:46','System',2000),(10,'7549ea53-8506-4e73-807a-b0536474e576',0,0,0,200,NULL,111,'2015-04-15 11:50:57','System',2000),(11,'8549ea53-8506-4e73-807a-b0536474e541',0,0,0,300,NULL,111,'2015-04-12 11:51:17','System',2000),(12,'9549ea53-8506-4e73-807a-b0536474e543',0,0,0,400,NULL,111,'2015-04-09 11:51:32','System',2000),(13,'0549ea53-8506-4e73-807a-b0536474e548',0,0,0,500,NULL,111,'2015-05-04 11:48:16','System',2000),(14,'1549ea53-8506-4e73-807a-b0536474e552',0,0,0,600,NULL,111,'2015-05-02 11:48:43','System',2000),(15,'2549ea53-8506-4e73-807a-b0536474e556',0,0,0,700,NULL,111,'2015-04-28 11:49:05','System',2000),(16,'3549ea53-8506-4e73-807a-b0536474e559',0,0,0,800,NULL,111,'2015-04-26 11:49:22','System',2000),(17,'4549ea53-8506-4e73-807a-b0536474e563',0,0,0,900,NULL,111,'2015-04-23 11:50:02','System',2000),(18,'5549ea53-8506-4e73-807a-b0536474e568',0,0,0,1000,NULL,111,'2015-04-19 11:50:23','System',2000),(19,'4449ea53-8506-4e73-807a-b0536474e561',0,0,0,1100,NULL,111,'2015-04-25 11:49:34','System',2000),(20,'6549ea53-8506-4e73-807a-b0536474e574',0,0,0,100,NULL,111,'2015-04-16 11:50:50','System',2000),(21,'7549ea53-8506-4e73-807a-b0536474e579',0,0,0,200,NULL,111,'2015-04-12 11:51:07','System',2000),(22,'8549ea53-8506-4e73-807a-b0536474e547',0,0,0,300,NULL,111,'2015-04-11 11:46:16','System',2000),(23,'9549ea53-8506-4e73-807a-b0536474e542',0,0,0,400,NULL,111,'2015-04-09 11:51:29','System',2000),(24,'0549ea53-8506-4e73-807a-b0536474e546',0,0,0,500,NULL,111,'2015-05-06 11:48:04','System',2000),(25,'1549ea53-8506-4e73-807a-b0536474e549',0,0,0,600,NULL,111,'2015-05-03 11:48:23','System',2000),(26,'2549ea53-8506-4e73-807a-b0536474e554',0,0,0,700,NULL,111,'2015-04-30 11:48:56','System',2000),(27,'3549ea53-8506-4e73-807a-b0536474e557',0,0,0,800,NULL,111,'2015-04-27 11:49:13','System',2000),(28,'4549ea53-8506-4e73-807a-b0536474e565',0,0,0,900,NULL,111,'2015-04-21 11:50:09','System',2000),(29,'5549ea53-8506-4e73-807a-b0536474e567',0,0,0,1000,NULL,111,'2015-04-19 11:50:17','System',2000),(30,'6549ea53-8506-4e73-807a-b0536474e571',0,0,0,100,NULL,111,'2015-04-18 11:50:37','System',2000),(31,'7549ea53-8506-4e73-807a-b0536474e577',0,0,0,200,NULL,111,'2015-04-14 11:51:00','System',2000),(32,'8549ea53-8506-4e73-807a-b0536474e543',0,0,0,300,NULL,111,'2015-04-11 11:51:23','System',2000),(33,'9549ea53-8506-4e73-807a-b0536474e546',0,0,0,400,NULL,111,'2015-04-08 11:46:02','System',2000),(34,'0549ea53-8506-4e73-807a-b0536474e547',0,0,0,500,NULL,111,'2015-05-05 11:48:07','System',2000),(35,'1549ea53-8506-4e73-807a-b0536474e550',0,0,0,600,NULL,111,'2015-05-03 11:48:37','System',2000),(36,'2549ea53-8506-4e73-807a-b0536474e555',0,0,0,700,NULL,111,'2015-04-29 11:49:00','System',2000),(37,'3549ea53-8506-4e73-807a-b0536474e560',0,0,0,800,NULL,111,'2015-04-25 11:49:26','System',2000),(38,'4549ea53-8506-4e73-807a-b0536474e566',0,0,0,900,NULL,111,'2015-04-20 11:50:13','System',2000),(39,'5549ea53-8506-4e73-807a-b0536474e569',0,0,0,1000,NULL,111,'2015-04-19 11:50:29','System',2000),(40,'6549ea53-8506-4e73-807a-b0536474e572',0,0,0,100,NULL,111,'2015-04-18 11:50:43','System',2000),(41,'7549ea53-8506-4e73-807a-b0536474e578',0,0,0,200,NULL,111,'2015-04-13 11:51:03','System',2000),(42,'8549ea53-8506-4e73-807a-b0536474e542',0,0,0,300,NULL,111,'2015-04-11 11:51:20','System',2000),(43,'9549ea53-8506-4e73-807a-b0536474e541',0,0,0,400,NULL,111,'2015-04-10 11:51:27','System',2000),(44,'0549ea53-8506-4e73-807a-b0536474e545',0,0,0,500,NULL,111,'2015-05-07 22:16:14','System',2000),(45,'1549ea53-8506-4e73-807a-b0536474e551',0,0,0,600,NULL,111,'2015-05-02 11:48:40','System',2000),(46,'2549ea53-8506-4e73-807a-b0536474e553',0,0,0,700,NULL,111,'2015-05-01 11:48:48','System',2000),(47,'3549ea53-8506-4e73-807a-b0536474e558',0,0,0,800,NULL,111,'2015-04-26 11:49:17','System',2000),(48,'4549ea53-8506-4e73-807a-b0536474e564',0,0,0,900,NULL,111,'2015-04-22 11:50:05','System',2000),(49,'5549ea53-8506-4e73-807a-b0536474e570',0,0,0,1000,NULL,111,'2015-04-18 11:50:33','System',2000),(50,'4449ea53-8506-4e73-807a-b0536474e562',0,0,0,1100,NULL,111,'2015-04-24 11:49:51','System',2000),(51,'b770ab2b-5879-42de-931b-8d1eba121c5B',0,0,0,1,'null',111,'2015-04-08 11:51:45','System',2000),(52,'b770ab2b-5879-42de-931b-8d1eba121c5a',0,0,0,17,'null',111,'2015-04-09 11:39:15','System',2000);

/*Table structure for table `USER` */

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `LOGINNAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(70) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(45) DEFAULT NULL,
  `UPDATED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `loginname_UNIQUE` (`LOGINNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `USER` */

insert  into `USER`(`ID`,`USERNAME`,`PASSWORD`,`STATUS`,`LOGINNAME`,`EMAIL`,`DOB`,`CREATED_BY`,`CREATED_TIME`,`UPDATED_BY`,`UPDATED_TIME`) values (2000,'Jitendra Mathur','12345','ACTIVE','jeet427','jeet427@gmail.com',NULL,NULL,NULL,NULL,NULL),(2001,'Gaurav Oli','pass@123','ACTIVE','gaurav','gauravoli@live.in',NULL,NULL,NULL,NULL,NULL),(2002,'Abhishek Sharma','ecf12c','ACTIVE','Mystry','abhishek@gmail.com',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `USERSANDROLES` */

DROP TABLE IF EXISTS `USERSANDROLES`;

CREATE TABLE `USERSANDROLES` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `USERSANDROLES` */

insert  into `USERSANDROLES`(`USER_ID`,`ROLE_ID`) values (2000,'2'),(2001,'1'),(2002,'3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
