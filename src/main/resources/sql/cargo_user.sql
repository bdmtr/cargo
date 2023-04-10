DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `fullname` varchar(50) COLLATE utf8_bin NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `role` varchar(25) COLLATE utf8_bin NOT NULL,
  `balance` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


INSERT INTO `user` VALUES (5,'user','Teoden Sauroniks','user@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',42),(19,'admin','admin','admin@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','MANAGER',1000),(29,'user2','Golum the Golum','gorlum@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',320),(30,'user3','Більбо Злоткінс','bilbo@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',283),(32,'user9','Fedir Borovko','fedir@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',1000),(33,'Vovar','Fedir Borovko','fifo@mail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',1000),(34,'Test','User Test','test123@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',1000),(35,'username','fullname','email','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',1000),(41,'user7','user user','user222@gmail.com','$2a$12$q2RTZNJG3ffXCAlrOGWf1exUx3nfGK6eVAZXzY54NGEHYXumZy0UG','USER',1000);
