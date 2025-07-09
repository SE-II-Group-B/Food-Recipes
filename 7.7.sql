-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cookbook
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
  `recipe_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SearchTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (11,'Kung Pao Chicken','2025-07-05 19:42:03'),(12,'Tomato and Egg Stir-fry','2025-07-05 19:42:55'),(13,'Mapo Tofu','2025-07-05 19:43:06'),(14,'Tomato and Egg Stir-fry','2025-07-05 19:43:43'),(15,'Shredded Potato Stir-fry','2025-07-05 19:47:33'),(16,'Kung Pao Chicken','2025-07-05 19:48:28'),(17,'Mapo Tofu','2025-07-05 19:50:14'),(18,'Hot and Sour Soup','2025-07-05 20:45:13'),(19,'Stir-fried Pork with Green Pepper','2025-07-06 18:34:09'),(20,'Sweet and Sour Pork','2025-07-06 22:07:17'),(21,'Tomato and Egg Stir-fry','2025-07-06 22:07:56'),(22,'Tomato and Egg Stir-fry','2025-07-06 22:14:13'),(23,'Tomato and Egg Stir-fry','2025-07-06 22:14:26'),(24,'Tomato and Egg Stir-fry','2025-07-06 22:16:04'),(25,'Tomato and Egg Stir-fry','2025-07-06 22:17:15'),(26,'Tomato and Egg Stir-fry','2025-07-06 22:20:31'),(27,'Tomato and Egg Stir-fry','2025-07-06 22:20:51'),(28,'Tomato and Egg Stir-fry','2025-07-06 22:21:41'),(29,'Tomato and Egg Stir-fry','2025-07-06 22:22:12'),(30,'Tomato and Egg Stir-fry','2025-07-06 22:22:23'),(31,'Tomato and Egg Stir-fry','2025-07-06 22:31:37'),(32,'Tomato and Egg Stir-fry','2025-07-06 22:31:45'),(33,'Braised Eggplant','2025-07-06 22:31:51'),(34,'Tomato and Egg Stir-fry','2025-07-06 22:33:31'),(35,'Tomato and Egg Stir-fry','2025-07-06 22:38:00'),(36,'Tomato and Egg Stir-fry','2025-07-06 22:46:18'),(37,'Stir-fried Pork with Green Pepper','2025-07-06 22:46:40'),(38,'Tomato and Egg Stir-fry','2025-07-06 22:46:49'),(39,'Stir-fried Pork with Green Pepper','2025-07-06 22:46:53'),(40,'Tomato and Egg Stir-fry','2025-07-06 22:48:20'),(41,'Stir-fried Pork with Green Pepper','2025-07-06 22:48:22'),(42,'Twice-Cooked Pork','2025-07-06 22:54:12'),(43,'Stir-fried Pork with Green Pepper','2025-07-06 22:56:39'),(44,'Tomato and Egg Stir-fry','2025-07-06 23:02:07'),(45,'Tomato and Egg Stir-fry','2025-07-06 23:05:43'),(46,'Tomato and Egg Stir-fry','2025-07-06 23:06:01'),(47,'Tomato and Egg Stir-fry','2025-07-06 23:08:50'),(48,'Tomato and Egg Stir-fry','2025-07-06 23:11:36'),(49,'Kung Pao Chicken','2025-07-06 23:17:30'),(50,'Braised Eggplant','2025-07-06 23:17:34'),(51,'Kung Pao Chicken','2025-07-06 23:17:38'),(52,'Tomato and Egg Stir-fry','2025-07-06 23:20:53'),(53,'Stir-fried Pork with Green Pepper','2025-07-06 23:20:58'),(54,'Braised Eggplant','2025-07-06 23:21:02'),(55,'Kung Pao Chicken','2025-07-06 23:21:05'),(56,'Mapo Tofu','2025-07-06 23:23:21'),(57,'Kung Pao Chicken','2025-07-06 23:23:25'),(58,'Braised Eggplant','2025-07-06 23:24:10'),(59,'Mapo Tofu','2025-07-06 23:24:16'),(60,'Kung Pao Chicken','2025-07-06 23:24:19'),(61,'Tomato and Egg Stir-fry','2025-07-06 23:24:47'),(62,'Tomato and Egg Stir-fry','2025-07-06 23:24:50'),(63,'Stir-fried Pork with Green Pepper','2025-07-06 23:24:54'),(64,'Mapo Tofu','2025-07-06 23:24:57'),(65,'Sweet and Sour Pork','2025-07-06 23:25:02'),(66,'Twice-Cooked Pork','2025-07-06 23:25:06'),(67,'Sour and Spicy Lotus Root','2025-07-06 23:25:08'),(68,'Kung Pao Chicken','2025-07-06 23:25:10'),(69,'Kung Pao Chicken','2025-07-06 23:25:15'),(70,'Kung Pao Chicken','2025-07-06 23:26:10'),(71,'Kung Pao Chicken','2025-07-06 23:26:14'),(72,'Kung Pao Chicken','2025-07-06 23:29:42'),(73,'Kung Pao Chicken','2025-07-06 23:29:53'),(74,'Kung Pao Chicken','2025-07-06 23:32:06'),(75,'Kung Pao Chicken','2025-07-06 23:32:26'),(76,'Kung Pao Chicken','2025-07-06 23:32:31'),(77,'Tomato and Egg Stir-fry','2025-07-06 23:33:09'),(78,'Kung Pao Chicken','2025-07-06 23:33:21'),(79,'Mapo Tofu','2025-07-06 23:33:30'),(80,'Sweet and Sour Pork','2025-07-06 23:33:33'),(81,'Beef with Broccoli','2025-07-06 23:33:36'),(82,'Shredded Potato Stir-fry','2025-07-06 23:33:39'),(83,'Hot and Sour Soup','2025-07-06 23:33:42'),(84,'Hot and Sour Soup','2025-07-06 23:37:17'),(85,'Sweet and Sour Pork','2025-07-06 23:38:32'),(86,'Hot and Sour Soup','2025-07-06 23:38:36'),(87,'Hot and Sour Soup','2025-07-06 23:39:28'),(88,'Stir-fried Pork with Green Pepper','2025-07-06 23:39:44'),(89,'Braised Eggplant','2025-07-06 23:39:47'),(90,'Kung Pao Chicken','2025-07-06 23:39:50'),(91,'Twice-Cooked Pork','2025-07-06 23:40:46'),(92,'Steamed Fish with Ginger and Scallion','2025-07-06 23:41:39'),(93,'Cabbage Stir-fry','2025-07-06 23:42:58'),(94,'Fried Rice with Egg','2025-07-06 23:43:40'),(95,'Tomato and Egg Stir-fry','2025-07-06 23:50:32'),(96,'Stir-fried Pork with Green Pepper','2025-07-06 23:50:35'),(97,'Braised Eggplant','2025-07-06 23:50:37'),(98,'Kung Pao Chicken','2025-07-06 23:50:40'),(99,'Sweet and Sour Pork','2025-07-06 23:50:43'),(100,'Beef with Broccoli','2025-07-06 23:54:16'),(101,'Shredded Potato Stir-fry','2025-07-06 23:55:53'),(102,'Sour and Spicy Lotus Root','2025-07-06 23:55:57'),(103,'Fried Rice with Egg','2025-07-06 23:56:00'),(104,'Kung Pao Chicken','2025-07-06 23:56:03'),(105,'Beef with Broccoli','2025-07-06 23:56:07'),(106,'Steamed Fish with Ginger and Scallion','2025-07-06 23:56:20'),(107,'Cabbage Stir-fry','2025-07-06 23:56:24'),(108,'Fried Rice with Egg','2025-07-06 23:56:27'),(109,'Braised Chicken with Mushroom','2025-07-06 23:56:30'),(110,'Sour and Spicy Lotus Root','2025-07-06 23:59:34'),(111,'Sour and Spicy Lotus Root','2025-07-07 00:02:32'),(112,'Stir-fried Pork with Green Pepper','2025-07-07 00:02:52'),(113,'Braised Eggplant','2025-07-07 00:02:56'),(114,'Stir-fried Pork with Green Pepper','2025-07-07 00:03:15'),(115,'Kung Pao Chicken','2025-07-07 00:03:17'),(116,'Sweet and Sour Pork','2025-07-07 00:03:28');
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
  `ingredient_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ingredient_id`),
  KEY `recipe_id` (`recipe_id`),
  CONSTRAINT `ingredient_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=657 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (61,5,'Tofu',1.00,'block'),(62,5,'Ground Pork',100.00,'g'),(63,5,'Doubanjiang',1.00,'tbsp'),(64,5,'Garlic',2.00,'cloves'),(347,8,'Potato',2.00,'pcs'),(348,8,'Vinegar',1.00,'tbsp'),(349,8,'Chili Pepper',1.00,'pcs'),(350,8,'Salt',1.00,'tsp'),(418,2,'Green Pepper',2.00,'pcs'),(419,2,'Pork',150.00,'g'),(420,2,'Soy Sauce',1.00,'tbsp'),(421,2,'Cooking Oil',1.00,'tbsp'),(422,2,'salt',1.00,'tbsp'),(423,2,'cooking wine',1.00,'tbsp'),(533,1,'Tomato',1.00,'pcs'),(534,1,'Egg',3.00,'pcs'),(535,1,'Salt',1.00,'tsp'),(536,1,'Cooking Oil',2.00,'tbsp'),(537,1,'chives (optional)',1.00,'pcs'),(546,9,'Tofu',2.00,'block'),(547,9,'Mushroom',50.00,'g'),(548,9,'Vinegar',1.00,'tbsp'),(549,9,'Pepper',1.00,'tsp'),(550,4,'Chicken Breast',200.00,'g'),(551,4,'Peanut',30.00,'g'),(552,4,'Dried Chili',5.00,'pcs'),(553,4,'Soy Sauce',1.00,'tbsp'),(554,4,'Garlic',2.00,'clove'),(555,4,'Ginger',1.00,'tsp'),(556,4,'Green Onion',1.00,'pcs'),(557,4,'Cooking Oil',1.00,'tbsp'),(558,4,'Sugar',1.00,'tbsp'),(559,4,'Cornstarch',1.00,'tsp'),(560,10,'Pork Belly',150.00,'g'),(561,10,'Cabbage',100.00,'g'),(562,10,'Broad Bean Paste',1.00,'tbsp'),(563,10,'Garlic',2.00,'cloves'),(564,11,'Fish',1.00,'pcs'),(565,11,'Ginger',5.00,'slices'),(566,11,'Scallion',2.00,'stalks'),(567,11,'Soy Sauce',1.00,'tbsp'),(568,12,'Cabbage',200.00,'g'),(569,12,'Garlic',2.00,'cloves'),(570,12,'Soy Sauce',1.00,'tbsp'),(571,12,'Salt',1.00,'tsp'),(580,13,'Rice',1.00,'bowl'),(581,13,'Egg',2.00,'pcs'),(582,13,'Soy Sauce',1.00,'tbsp'),(583,13,'Scallion',1.00,'stalk'),(584,13,'Green peas',30.00,'g'),(585,13,'Carrot',30.00,'g'),(586,13,'Cooking oil',1.00,'tbsp'),(587,13,'Salt',1.00,'tbsp'),(592,6,'Pork',200.00,'g'),(593,6,'Pineapple',50.00,'g'),(594,6,'Ketchup',2.00,'tbsp'),(595,6,'Vinegar',1.00,'tbsp'),(596,6,'Egg',1.00,'pcs'),(597,6,'Cornstarch',3.00,'tbsp'),(598,6,'Cooking oil',3.00,'tbsp'),(599,6,'onion',30.00,'g'),(600,6,'sugar',2.00,'tbsp'),(601,6,'water',3.00,'tbsp'),(602,7,'Beef',200.00,'g'),(603,7,'Broccoli',150.00,'g'),(604,7,'Soy Sauce',1.00,'tbsp'),(605,7,'Garlic',2.00,'cloves'),(606,7,'Oyster sauce',1.00,'tbsp'),(623,14,'Chicken Thigh',300.00,'g'),(624,14,'Mushroom',100.00,'g'),(625,14,'Ginger',3.00,'slices'),(626,14,'Soy Sauce',1.00,'tbsp'),(627,14,'Garlic',2.00,'cloves'),(628,14,'Water',2.00,'cups'),(629,14,'Salt',1.00,'tsp'),(630,14,'Green onion',1.00,'stalk'),(640,15,'Lotus Root',150.00,'g'),(641,15,'Chili Pepper',2.00,'pcs'),(642,15,'Vinegar',1.00,'tbsp'),(643,15,'Garlic',2.00,'cloves'),(644,15,'Soy sauce',1.00,'tbsp'),(645,15,'Sugar',1.00,'tbsp'),(646,15,'Cooking oil',1.00,'tbsp'),(647,15,'Salt',3.00,'g'),(648,15,'Green onion',1.00,'stalk'),(649,3,'Soy Sauce',1.00,'tbsp'),(650,3,'Garlic',2.00,'cloves'),(651,3,'Cooking Oil',2.00,'tbsp'),(652,3,'Ginger',1.00,'tsp'),(653,3,'Black vinegar',1.00,'tbsp'),(654,3,'Water',100.00,'ml'),(655,3,'Cornstarch',1.00,'tsp'),(656,3,'Salt',1.00,'tbsp');
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
  `recipe_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `preptime` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`recipe_id`),
  UNIQUE KEY `unique_recipe_name` (`recipe_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Tomato and Egg Stir-fry','It’s quick to cook, full of flavor, and perfect with a bowl of rice.','Tomato_and_Egg_Stir-fry_img.jpg','2025-07-04 16:47:55','2025-07-06 22:21:51','20 min'),(2,'Stir-fried Pork with Green Pepper','savory, easy to prepare, and pairs perfectly with steamed rice for a satisfying meal.','Stir-fried_Pork_with_Green_Pepper_img.jpg','2025-07-04 16:45:06','2025-07-06 22:47:40','25 min'),(3,'Braised Eggplant','Soft eggplant cooked in a savory, slightly sweet soy-based sauce. Comfort food classic.','Braised_Eggplant_img.jpg','2025-07-04 16:52:22','2025-07-07 00:03:11','20 min'),(4,'Kung Pao Chicken','It features tender diced chicken stir-fried with dried chili peppers, garlic, ginger, and crunchy peanuts, all tossed in a flavorful sauce that balances sweet, sour, salty, and spicy tastes.','Kung_Pao_Chicken_img.jpeg','2025-07-04 13:42:13','2025-07-06 23:40:41','25 min'),(5,'Mapo Tofu',NULL,'Mapo_Tofu_img.jpg','2025-07-04 13:15:34','2025-07-05 20:44:47','30 min'),(6,'Sweet and Sour Pork','Crispy pork in a tangy sweet and sour sauce with juicy pineapple chunks.','Sweet_and_Sour_Pork_img.jpg','2025-07-04 13:23:27','2025-07-06 23:54:10','35 min'),(7,'Beef with Broccoli','Tender beef stir-fried with crisp broccoli in a savory garlic sauce.','Beef_with_Broccoli_img.jpg','2025-07-04 13:24:38','2025-07-06 23:55:47','25 min'),(8,'Shredded Potato Stir-fry','','Shredded_Potato_Stir-fry_img.jpg','2025-07-04 13:25:41','2025-07-05 20:44:47','15 min'),(9,'Hot and Sour Soup','A flavorful soup with a spicy and sour kick.','Hot_and_Sour_Soup_img.jpg','2025-06-30 21:49:36','2025-07-06 23:36:11','40 min'),(10,'Twice-Cooked Pork','Sichuan-style pork boiled then stir-fried with vegetables.','Twice-Cooked_Pork_img.jpg','2025-06-30 21:49:36','2025-07-06 23:41:31','45 min'),(11,'Steamed Fish with Ginger and Scallion','Delicate steamed fish with aromatic toppings.','Steamed_Fish_with_Ginger_and_Scallion_img.jpg','2025-06-30 21:49:36','2025-07-06 23:42:47','25 min'),(12,'Cabbage Stir-fry','Quick stir-fry of cabbage with garlic and soy sauce.','Cabbage_Stir-fry_img.jpeg','2025-06-30 21:49:36','2025-07-06 23:43:35','10 min'),(13,'Fried Rice with Egg','A quick, tasty fried rice with eggs, vegetables, and soy sauce. Perfect for leftovers.','Fried_Rice_with_Egg_img.jpg','2025-06-30 21:49:36','2025-07-06 23:49:45','12 min'),(14,'Braised Chicken with Mushroom','A hearty, comforting dish of tender chicken and earthy mushrooms slow-cooked in savory broth.','Braised_Chicken_with_Mushroom_img.jpg','2025-06-30 21:49:36','2025-07-06 23:59:26','1 hour'),(15,'Sour and Spicy Lotus Root','Crunchy lotus root tossed in a tangy, spicy dressing, perfect as a refreshing appetizer or side dish.','Sour_and_Spicy_Lotus_Root_img.jpg','2025-06-30 21:49:36','2025-07-07 00:02:39','18 min');
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
  `instruction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`recipe_id`,`step_id`),
  CONSTRAINT `step_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (1,1,'Crack the eggs into a bowl, add a pinch of salt, and beat them until well mixed. Wash the tomatoes and cut them into small or medium-sized chunks, depending on your texture preference. If using, finely chop the green onion.'),(2,1,'Heat some oil in a pan over medium heat. Pour in the beaten eggs and stir gently until they just set and become fluffy and golden. Be careful not to overcook. Remove the eggs from the pan and set them aside.'),(3,1,'Add a bit more oil to the same pan and sauté the green onions briefly if using. Add the tomato chunks and stir-fry for about 2–3 minutes. Optionally, add a small splash of water to help release the tomato juices. Season with salt and a pinch of sugar to balance the sourness.'),(4,1,'Return the scrambled eggs to the pan and mix them with the softened tomatoes. Stir-fry everything together for about 30 seconds to 1 minute, allowing the eggs to soak up the tomato flavor. Taste and adjust seasoning if needed, then serve hot.'),(1,2,'Slice the pork thinly into strips. Marinate it with soy sauce, cooking wine, a pinch of salt, an cornstarch for 10–15 minutes. Wash and deseed the green pepper, then cut into thin strips. Peel and slice the garlic if using.'),(2,2,'Heat some oil in a pan or wok over medium-high heat. Add the pork and stir-fry quickly until it changes color and firms up, about 1–2 minutes. Take it out and set aside.'),(3,2,'Add a little more oil to the pan if needed, then stir-fry the green pepper strips until slightly tender but still crisp, about 1–2 minutes. Add the garlic and stir briefly if using.'),(4,2,'Return the pork to the pan and stir everything together for another 30 seconds to 1 minute. Adjust salt or soy sauce to taste, then serve immediately.'),(1,3,'Wash the eggplant and cut it into medium-sized strips. Soak them in salted water for 10 minutes to reduce bitterness and prevent browning, then drain well.'),(2,3,'Heat the oil in a pan or wok over medium-high heat. Add the eggplant strips and fry until they become soft and slightly browned, about 5–7 minutes. Remove and set aside.'),(3,3,'In the same pan, add a little more oil if needed, then sauté the minced garlic, ginger, and the white part of the green onion until fragrant. Stir in the soy sauce, vinegar, sugar, and water. Let it simmer briefly.'),(4,3,'Mix cornstarch with 2tbsp water thoroughly until smooth, set aside.'),(5,3,'Return the eggplant to the pan, toss to coat with the sauce, then slowly add the cornstarch slurry to thicken. Stir until the sauce clings to the eggplant and everything is heated through. Adjust salt if needed, garnish with green onion tops, and serve hot.'),(1,4,'Dice the chicken into bite-sized cubes and mix with soy sauce, cooking wine, and cornstarch. Let it sit for 10–15 minutes.'),(2,4,'In a small bowl, combine soy sauce, black vinegar, sugar, and water. Mix well and set aside.'),(3,4,'Heat oil in a wok or pan over medium-high heat. Add the marinated chicken and stir-fry until cooked through and lightly browned, about 3–4 minutes. Remove and set aside.'),(4,4,'Add a little more oil to the pan, then stir-fry the dried chilies, minced garlic, ginger, and white part of the green onion until fragrant, about 30 seconds.'),(5,4,'Return the chicken to the pan, add the sauce, and stir quickly. Pour in the cornstarch slurry gradually, stirring until the sauce thickens and coats the chicken evenly.'),(6,4,'Add the peanuts and green onion tops, stir briefly, then remove from heat and serve immediately.'),(1,5,'Cut tofu into cubes.'),(2,5,'Cook ground pork with Doubanjiang.'),(3,5,'Add tofu and simmer with sauce.'),(4,5,'Add garlic and serve hot.'),(1,6,'Cut pork tenderloin into thin strips. Beat the egg, dip pork strips, then coat evenly with cornstarch.'),(2,6,'Heat oil in a pan over medium-high heat. Deep-fry or pan-fry pork strips until golden and crispy. Remove and drain excess oil.'),(3,6,'In another pan, mix ketchup, vinegar, sugar, soy sauce, and water. Bring to a simmer.'),(4,6,'Add the cornstarch slurry to the sauce and stir until thickened.'),(5,6,'Add the fried pork strips, bell pepper, and onion to the sauce. Toss well to coat everything evenly and cook for 1-2 minutes. Serve hot.'),(1,7,'Marinate beef slices with 1 tbsp soy sauce and 1 tsp cornstarch for 10 minutes.'),(2,7,'Blanch broccoli in boiling water for 1-2 minutes until bright green and crisp-tender, then drain.'),(3,7,'Heat oil in a pan over medium-high heat. Stir-fry garlic until fragrant.'),(4,7,'Add beef and stir-fry until just cooked.'),(5,7,'Add broccoli, oyster sauce, remaining soy sauce, sugar, and salt. Stir well.'),(6,7,'Mix cornstarch with water, pour into the pan to thicken the sauce. Cook until sauce coats beef and broccoli. Serve hot.'),(1,8,'Shred potatoes and soak in water.'),(2,8,'Stir-fry chili and potato.'),(3,8,'Add vinegar and salt, cook until crisp.'),(1,9,'Prepare tofu and mushrooms.'),(2,9,'Boil ingredients with vinegar and pepper.'),(3,9,'Stir in cornstarch to thicken.'),(1,10,'Boil pork belly then slice thin.'),(2,10,'Stir-fry pork with bean paste.'),(3,10,'Add cabbage and garlic, stir-fry together.'),(1,11,'Clean the fish and make cuts.'),(2,11,'Place ginger and scallion on fish.'),(3,11,'Steam for 10 minutes and pour soy sauce.'),(1,12,'Chop cabbage and garlic.'),(2,12,'Stir-fry garlic and cabbage.'),(3,12,'Add soy sauce and cook until tender.'),(1,13,'Heat oil in a pan over medium heat. Beat the eggs and pour into the pan, scramble gently until just cooked but still soft. Remove eggs and set aside.'),(2,13,'Add a little more oil if needed, then stir-fry the diced carrots and peas for 2–3 minutes until tender.'),(3,13,'Add the cooked rice to the pan, breaking up any clumps. Stir well to combine with the vegetables.'),(4,13,'Pour soy sauce evenly over the rice, stirring continuously to coat all grains and add flavor.'),(5,13,'Return the scrambled eggs to the pan, mix everything thoroughly, then season with salt to taste. Cook for another minute and serve hot.'),(1,14,'Soak dried shiitake mushrooms in warm water for 20-30 minutes until soft, then slice.'),(2,14,'Heat cooking oil in a pot over medium heat. Add ginger slices and crushed garlic, sauté until fragrant.'),(3,14,'Add chicken pieces to the pot, brown them evenly on all sides to seal in flavor.'),(4,14,'Add the soaked and fresh mushrooms to the pot, stir for 2 minutes to combine flavors.'),(5,14,'Pour in soy sauce and water or chicken broth, bring to a boil.'),(6,14,'Reduce heat to low, cover the pot, and let it simmer gently for 40-45 minutes until chicken is tender and cooked through.'),(7,14,'Taste the broth and add salt if needed. Sprinkle chopped green onion on top before serving.'),(1,15,'Peel and thinly slice the lotus root, then soak in cold water for 5 minutes to remove excess starch. Drain well.'),(2,15,'Bring a pot of water to boil, blanch lotus root slices for 2-3 minutes until just tender but still crisp. Drain and set aside.'),(3,15,'Heat cooking oil in a small pan over medium heat, add minced garlic and dried chili flakes, stir-fry until fragrant but not burnt.'),(4,15,'In a bowl, combine rice vinegar, soy sauce, sugar, sesame oil, and salt; mix well.'),(5,15,'Add the blanched lotus root and the garlic-chili oil to the bowl, toss thoroughly to coat evenly.'),(6,15,'Garnish with chopped green onion before serving. Serve cold or at room temperature.');
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

-- Dump completed on 2025-07-07  0:05:01
