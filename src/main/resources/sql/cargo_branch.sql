DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(25) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `branch` VALUES (1,'KYIV','Vasilkivskaya'),(2,'LVIV','Kordubu'),(3,'DNIPRO','Gagarina2'),(4,'KHARKIV','Gagarina25');