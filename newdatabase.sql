-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: cookbook
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SearchTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `ingredient_id` int NOT NULL AUTO_INCREMENT,
  `recipe_id` int NOT NULL,
  `ingredient_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `unit` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `ingredient_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,1,'Tomato',2.00,'pcs'),(2,1,'Egg',3.00,'pcs'),(3,1,'Salt',1.00,'tsp'),(4,1,'Cooking Oil',2.00,'tbsp'),(5,2,'Green Pepper',2.00,'pcs'),(6,2,'Pork',150.00,'g'),(7,2,'Soy Sauce',1.00,'tbsp'),(8,2,'Cooking Oil',1.00,'tbsp'),(9,3,'Eggplant',2.00,'pcs'),(10,3,'Soy Sauce',1.00,'tbsp'),(11,3,'Garlic',2.00,'cloves'),(12,3,'Cooking Oil',2.00,'tbsp'),(13,4,'Chicken Breast',200.00,'g'),(14,4,'Peanut',30.00,'g'),(15,4,'Dried Chili',5.00,'pcs'),(16,4,'Soy Sauce',1.00,'tbsp'),(17,5,'Tofu',1.00,'block'),(18,5,'Ground Pork',100.00,'g'),(19,5,'Doubanjiang',1.00,'tbsp'),(20,5,'Garlic',2.00,'cloves'),(21,6,'Pork',200.00,'g'),(22,6,'Pineapple',50.00,'g'),(23,6,'Ketchup',2.00,'tbsp'),(24,6,'Vinegar',1.00,'tbsp'),(25,7,'Beef',200.00,'g'),(26,7,'Broccoli',150.00,'g'),(27,7,'Soy Sauce',1.00,'tbsp'),(28,7,'Garlic',2.00,'cloves'),(29,8,'Potato',2.00,'pcs'),(30,8,'Vinegar',1.00,'tbsp'),(31,8,'Chili Pepper',1.00,'pcs'),(32,8,'Salt',1.00,'tsp'),(33,9,'Tofu',0.50,'block'),(34,9,'Mushroom',50.00,'g'),(35,9,'Vinegar',1.00,'tbsp'),(36,9,'Pepper',0.50,'tsp'),(37,10,'Pork Belly',150.00,'g'),(38,10,'Cabbage',100.00,'g'),(39,10,'Broad Bean Paste',1.00,'tbsp'),(40,10,'Garlic',2.00,'cloves'),(41,11,'Fish',1.00,'pcs'),(42,11,'Ginger',5.00,'slices'),(43,11,'Scallion',2.00,'stalks'),(44,11,'Soy Sauce',1.00,'tbsp'),(45,12,'Cabbage',200.00,'g'),(46,12,'Garlic',2.00,'cloves'),(47,12,'Soy Sauce',1.00,'tbsp'),(48,12,'Salt',1.00,'tsp'),(49,13,'Rice',1.00,'bowl'),(50,13,'Egg',2.00,'pcs'),(51,13,'Soy Sauce',1.00,'tbsp'),(52,13,'Scallion',1.00,'stalk'),(53,14,'Chicken Thigh',300.00,'g'),(54,14,'Mushroom',100.00,'g'),(55,14,'Ginger',3.00,'slices'),(56,14,'Soy Sauce',1.00,'tbsp'),(57,15,'Lotus Root',150.00,'g'),(58,15,'Chili Pepper',2.00,'pcs'),(59,15,'Vinegar',1.00,'tbsp'),(60,15,'Garlic',2.00,'cloves');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_id` int NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `img_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recipe_id`),
  UNIQUE KEY `unique_recipe_name` (`recipe_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Tomato and Egg Stir-fry','A quick and classic Chinese home dish.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(2,'Stir-fried Pork with Green Pepper','Simple stir-fry with pork and green pepper.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(3,'Braised Eggplant','Soft and flavorful eggplant with savory sauce.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(4,'Kung Pao Chicken','Spicy stir-fried chicken with peanuts and vegetables.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(5,'Mapo Tofu','Spicy Sichuan dish with tofu and ground pork.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(6,'Sweet and Sour Pork','Crispy pork in a tangy sweet and sour sauce.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(7,'Beef with Broccoli','Tender beef stir-fried with broccoli and garlic.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(8,'Shredded Potato Stir-fry','Crisp shredded potato stir-fried with vinegar.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(9,'Hot and Sour Soup','A flavorful soup with a spicy and sour kick.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(10,'Twice-Cooked Pork','Sichuan-style pork boiled then stir-fried with vegetables.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(11,'Steamed Fish with Ginger and Scallion','Delicate steamed fish with aromatic toppings.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(12,'Cabbage Stir-fry','Quick stir-fry of cabbage with garlic and soy sauce.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(13,'Fried Rice with Egg','Simple and delicious egg fried rice.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(14,'Braised Chicken with Mushroom','Slow-cooked chicken with rich mushroom flavor.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36'),(15,'Sour and Spicy Lotus Root','Lotus root slices stir-fried with chili and vinegar.',NULL,'2025-06-30 21:49:36','2025-06-30 21:49:36');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `step` (
  `step_id` int NOT NULL,
  `recipe_id` int NOT NULL,
  `instruction` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`recipe_id`,`step_id`),
  CONSTRAINT `step_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (1,1,'Crack eggs into a bowl and beat evenly.'),(2,1,'Cut tomatoes into wedges.'),(3,1,'Heat oil, scramble eggs, remove.'),(4,1,'Stir-fry tomatoes, then return eggs and mix well.'),(1,2,'Slice pork thinly.'),(2,2,'Cut green pepper into strips.'),(3,2,'Stir-fry pork until browned.'),(4,2,'Add green pepper and seasonings, stir-fry briefly.'),(1,3,'Cut eggplant into strips.'),(2,3,'Fry eggplant until soft.'),(3,3,'Add garlic and sauce, cook until flavorful.'),(1,4,'Cut chicken into cubes.'),(2,4,'Fry peanuts and set aside.'),(3,4,'Stir-fry chicken with chili and sauce.'),(4,4,'Add peanuts and mix well before serving.'),(1,5,'Cut tofu into cubes.'),(2,5,'Cook ground pork with Doubanjiang.'),(3,5,'Add tofu and simmer with sauce.'),(4,5,'Add garlic and serve hot.'),(1,6,'Cut pork into bite-sized pieces and fry.'),(2,6,'Prepare sweet and sour sauce.'),(3,6,'Stir-fry pork with pineapple and sauce.'),(1,7,'Slice beef thinly.'),(2,7,'Blanch broccoli.'),(3,7,'Stir-fry beef with garlic.'),(4,7,'Add broccoli and sauce, cook briefly.'),(1,8,'Shred potatoes and soak in water.'),(2,8,'Stir-fry chili and potato.'),(3,8,'Add vinegar and salt, cook until crisp.'),(1,9,'Prepare tofu and mushrooms.'),(2,9,'Boil ingredients with vinegar and pepper.'),(3,9,'Stir in cornstarch to thicken.'),(1,10,'Boil pork belly then slice thin.'),(2,10,'Stir-fry pork with bean paste.'),(3,10,'Add cabbage and garlic, stir-fry together.'),(1,11,'Clean the fish and make cuts.'),(2,11,'Place ginger and scallion on fish.'),(3,11,'Steam for 10 minutes and pour soy sauce.'),(1,12,'Chop cabbage and garlic.'),(2,12,'Stir-fry garlic and cabbage.'),(3,12,'Add soy sauce and cook until tender.'),(1,13,'Beat eggs and cook scrambled.'),(2,13,'Add rice and stir-fry.'),(3,13,'Add soy sauce and scallion, mix well.'),(1,14,'Cut chicken and clean mushrooms.'),(2,14,'Stir-fry chicken with ginger.'),(3,14,'Add mushrooms and simmer with sauce.'),(1,15,'Slice lotus root thinly.'),(2,15,'Stir-fry chili and garlic.'),(3,15,'Add lotus root and vinegar, cook briefly.');
/*!40000 ALTER TABLE `step` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-01 14:10:59
