
-- DROP database `db_example`;
CREATE DATABASE  IF NOT EXISTS `db_example` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_example`;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
`customer_id` bigint(20) NOT NULL,
`app_no` varchar(255) DEFAULT NULL,
`building_no` varchar(255) DEFAULT NULL,
`city` varchar(255) DEFAULT NULL,
`street_name` varchar(255) DEFAULT NULL,
`type` varchar(255) DEFAULT NULL,
`zip_code` varchar(255) DEFAULT NULL,
`email` varchar(255) DEFAULT NULL,
`name` varchar(255) DEFAULT NULL,
`loyal_card_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `id_gen`;
CREATE TABLE `id_gen` (
`GEN_KEY` varchar(255) NOT NULL,
`GEN_VALUE` bigint(20) DEFAULT NULL,
PRIMARY KEY (`GEN_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `pizza`;
CREATE TABLE `pizza` (
`pizza_id` bigint(20) NOT NULL,
`name` varchar(255) DEFAULT NULL,
`price` double DEFAULT NULL,
`type` varchar(255) DEFAULT NULL,
PRIMARY KEY (`pizza_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


