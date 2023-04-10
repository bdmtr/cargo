DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `date` datetime DEFAULT NULL,
  `class` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `line_number` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `level` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `message` varchar(1000) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
