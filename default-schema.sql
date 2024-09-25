CREATE DATABASE  IF NOT EXISTS `futurelight` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `futurelight`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: futurelight
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('shahriar123','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_messages`
--

DROP TABLE IF EXISTS `chat_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` longtext,
  `sender` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_messages`
--

LOCK TABLES `chat_messages` WRITE;
/*!40000 ALTER TABLE `chat_messages` DISABLE KEYS */;
INSERT INTO `chat_messages` VALUES (1,'Hello','user','2024-09-20 16:03:02.095078','admin'),(2,'Hello! How can I help you with medical, gene, or DNA related information today? \n','model','2024-09-20 16:03:04.539156','admin'),(3,'What is 2+4?','user','2024-09-20 16:03:26.099496','admin'),(4,'2 + 4 = 6 \n\nWhile that\'s a simple math problem, is there anything specific you\'d like to know about medicine, genes, or DNA? \n','model','2024-09-20 16:03:26.759946','admin'),(5,'Multiply it by two','user','2024-09-20 16:04:02.489223','admin'),(6,'6 x 2 = 12\n\n  Is there anything else I can help you with related to medicine, genes, or DNA?  \n','model','2024-09-20 16:04:03.115506','admin');
/*!40000 ALTER TABLE `chat_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `text` tinytext,
  `author_id` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
  CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `banner_image_path` varchar(255) DEFAULT NULL,
  `content` longtext,
  `title` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (6,'e2687152-a04d-47ec-a757-4760c38feb25_bird.jpeg','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Sample title 1','admin'),(7,'624150a1-10cd-4870-9edc-edf17067b103_bird2.jpg','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Sample title 2','admin'),(8,'ac342586-e9b1-4946-ac6b-e57261c80fd5_dna.jpg','Nanopore sequence analysis can now be done more than 30 times faster, providing quicker and better specialized treatments for patients with cancer and other diseases.\r\n\r\nA new computer file format which helps process DNA samples 30 times faster than existing systems has been developed by teams at UNSW and the Garvan Institute of Medical Research.\r\n\r\nThe SLOW5 format has been specifically designed to more efficiently analyze nanopore sequences, which provide a more complete view of genetic variations.\r\n\r\nThe improved efficiency not only helps medical experts analyze individual DNA samples much more quickly and provide faster and better healthcare to patients, but also allows more sampling in any given period.\r\n\r\nThe research behind the development has been published in Nature Biotechnology, but the software has already been made available through open source and has been downloaded more than 1,000 times in just a few weeks.\r\n\r\nThe complex nature of nanopore sequencing of DNA means huge amounts of data are created which then needs to be stored and properly analyzed.\r\n\r\nThis data has routinely been recorded in a file format called FAST5, with such complex information often producing files around 1.3 terabyte in size—roughly the equivalent of 650 hours of HD video.','DNA sample analysis times dramatically reduced thanks to new file format','admin'),(9,'f7b74b9d-737d-4dbd-a183-e9d333cf2576_dna2.jpg','A research team led by scientists at the University of California, Riverside, has developed a computational workflow for analyzing large data sets in the field of metabolomics, the study of small molecules found within cells, biofluids, tissues, and entire ecosystems.\r\n\r\nMost recently, the team applied this new computational tool to analyze pollutants in seawater in Southern California. The team swiftly captured the chemical profiles of coastal environments and highlighted potential sources of pollution.\r\n\r\n\"We are interested in understanding how such pollutants get introduced in the ecosystem,\" said Daniel Petras, an assistant professor of biochemistry at UC Riverside, who led the research team. \"Figuring out which molecules in the ocean are important for environmental health is not straightforward because of the ocean\'s sheer chemical diversity. The protocol we developed greatly speeds up this process. More efficient sorting of the data means we can understand problems related to ocean pollution faster.\"\r\n\r\nPetras and his colleagues report in the journal Nature Protocols that their protocol is designed not only for experienced researchers but also for educational purposes, making it an ideal resource for students and early-career scientists. This computational workflow is accompanied by an accessible web application with a graphical user interface that makes metabolomics data analysis accessible for non-experts and enables them to gain statistical insights into their data within minutes.','New data science tool greatly speeds up molecular analysis of our environment','admin'),(10,'a577172e-a0a8-4380-b2f7-b92a1c4f28f6_dna.png','Researchers have reconstructed the oldest human genomes ever found in South Africa from two people who lived around 10,000 years ago, allowing a better understanding of how the region was populated, an author of the study said Sunday.\r\n\r\nThe genetic sequences were from a man and a woman whose remains were found at a rock shelter near the southern coastal town of George, about 370 kilometers (230 miles) east of Cape Town, said University of Cape Town (UCT) biological anthropology professor Victoria Gibbon.\r\n\r\nThey were among 13 sequences reconstructed from people whose remains were found at the Oakhurst shelter and lived 1,300-10,000 years ago. Prior to these discoveries, the oldest genomes reconstructed from the region dated back around 2,000 years.\r\n\r\nA surprise finding from the Oakhurst study was that the oldest genomes were genetically similar to those from San and Khoekhoe groups living in the same region today, UCT said in a statement.\r\n\r\n\"Similar studies from Europe have revealed a history of large-scale genetic changes due to human movements over the last 10,000 years,\" said lead author of the study, Joscha Gretzinger, in the statement.\r\n\r\n\"These new results from southernmost Africa are quite different, and suggest a long history of relative genetic stability,\" said Gretzinger, from the Max Planck Institute for Evolutionary Anthropology in Leipzig, Germany, which participated in the study.\r\n\r\nDNA data currently show this only changed around 1,200 years ago when newcomers arrived and introduced pastoralism, agriculture and new languages to the region, and began interacting with local hunter-gatherer groups.\r\n\r\nEven though some of the world\'s earliest evidence of modern humans can be traced to southern Africa, it tends to be poorly preserved, Gibbon told AFP. Newer technology made it possible to obtain this DNA, she said.\r\n\r\nUnlike in Europe and Asia where the genomes of thousands of people have been reconstructed, fewer than two dozen ancient genomes have been recovered from southern Africa, specifically Botswana, South Africa and Zambia.\r\n\r\n\"Sites like this are rare in South Africa, and Oakhurst has allowed for a better understanding of local population movements and relationships across the landscape over nearly 9,000 years,\" Gibbon said.','Researchers decode oldest human DNA from South Africa to date','shahriar123');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` varchar(5) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','$2a$12$10A5k3MByQ.642h7Jeyp5.2M3sahlz8dZXT9Gr0fEvdOQ5gP9A7Fq','1','admin','admin@futurelight.com','male',25),('shahriar123','$2a$12$10A5k3MByQ.642h7Jeyp5.2M3sahlz8dZXT9Gr0fEvdOQ5gP9A7Fq','1','Shahriar Kabir','shahriar1234@gmail.com','male',24);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-25 13:08:02
