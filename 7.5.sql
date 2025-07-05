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
  `recipe_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SearchTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (11,'Kung Pao Chicken','2025-07-05 19:42:03'),(12,'Tomato and Egg Stir-fry','2025-07-05 19:42:55'),(13,'Mapo Tofu','2025-07-05 19:43:06'),(14,'Tomato and Egg Stir-fry','2025-07-05 19:43:43'),(15,'Shredded Potato Stir-fry','2025-07-05 19:47:33'),(16,'Kung Pao Chicken','2025-07-05 19:48:28'),(17,'Mapo Tofu','2025-07-05 19:50:14'),(18,'Hot and Sour Soup','2025-07-05 20:45:13');
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
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (33,9,'Tofu',0.50,'block'),(34,9,'Mushroom',50.00,'g'),(35,9,'Vinegar',1.00,'tbsp'),(36,9,'Pepper',0.50,'tsp'),(37,10,'Pork Belly',150.00,'g'),(38,10,'Cabbage',100.00,'g'),(39,10,'Broad Bean Paste',1.00,'tbsp'),(40,10,'Garlic',2.00,'cloves'),(41,11,'Fish',1.00,'pcs'),(42,11,'Ginger',5.00,'slices'),(43,11,'Scallion',2.00,'stalks'),(44,11,'Soy Sauce',1.00,'tbsp'),(49,13,'Rice',1.00,'bowl'),(50,13,'Egg',2.00,'pcs'),(51,13,'Soy Sauce',1.00,'tbsp'),(52,13,'Scallion',1.00,'stalk'),(53,14,'Chicken Thigh',300.00,'g'),(54,14,'Mushroom',100.00,'g'),(55,14,'Ginger',3.00,'slices'),(56,14,'Soy Sauce',1.00,'tbsp'),(57,15,'Lotus Root',150.00,'g'),(58,15,'Chili Pepper',2.00,'pcs'),(59,15,'Vinegar',1.00,'tbsp'),(60,15,'Garlic',2.00,'cloves'),(61,5,'Tofu',1.00,'block'),(62,5,'Ground Pork',100.00,'g'),(63,5,'Doubanjiang',1.00,'tbsp'),(64,5,'Garlic',2.00,'cloves'),(89,6,'Pork',200.00,'g'),(90,6,'Pineapple',50.00,'g'),(91,6,'Ketchup',2.00,'tbsp'),(92,6,'Vinegar',1.00,'tbsp'),(93,7,'Beef',200.00,'g'),(94,7,'Broccoli',150.00,'g'),(95,7,'Soy Sauce',1.00,'tbsp'),(96,7,'Garlic',2.00,'cloves'),(339,12,'Cabbage',200.00,'g'),(340,12,'Garlic',2.00,'cloves'),(341,12,'Soy Sauce',1.00,'tbsp'),(342,12,'Salt',1.00,'tsp'),(347,8,'Potato',2.00,'pcs'),(348,8,'Vinegar',1.00,'tbsp'),(349,8,'Chili Pepper',1.00,'pcs'),(350,8,'Salt',1.00,'tsp'),(366,2,'Green Pepper',2.00,'pcs'),(367,2,'Pork',150.00,'g'),(368,2,'Soy Sauce',1.00,'tbsp'),(369,2,'Cooking Oil',1.00,'tbsp'),(370,2,'salt',1.00,'tbsp'),(371,2,'cooking wine',1.00,'tbsp'),(380,4,'Chicken Breast',200.00,'g'),(381,4,'Peanut',30.00,'g'),(382,4,'Dried Chili',5.00,'pcs'),(383,4,'Soy Sauce',1.00,'tbsp'),(384,4,'Garlic',2.00,'clove'),(385,4,'Ginger',1.00,'tsp'),(386,4,'Green Onion',1.00,'pcs'),(387,4,'Cooking Oil',1.00,'tbsp'),(388,4,'Sugar',1.00,'tbsp'),(389,4,'Cornstarch',1.00,'tsp'),(390,3,'Soy Sauce',1.00,'tbsp'),(391,3,'Garlic',2.00,'cloves'),(392,3,'Cooking Oil',2.00,'tbsp'),(393,3,'Ginger',1.00,'tsp'),(394,3,'Black vinegar',1.00,'tbsp'),(395,3,'Water',100.00,'ml'),(396,3,'Cornstarch',1.00,'tsp'),(397,3,'Salt',1.00,'tbsp'),(398,1,'Tomato',1.00,'pcs'),(399,1,'Egg',3.00,'pcs'),(400,1,'Salt',1.00,'tsp'),(401,1,'Cooking Oil',2.00,'tbsp'),(402,1,'chives (optional)',1.00,'pcs');
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
  `preptime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`recipe_id`),
  UNIQUE KEY `unique_recipe_name` (`recipe_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Tomato and Egg Stir-fry','','Tomato_and_Egg_Stir-fry_img.jpg','2025-07-04 16:47:55','2025-07-05 20:44:47','10 min'),(2,'Stir-fried Pork with Green Pepper','123','Stir-fried_Pork_with_Green_Pepper_img.jpg','2025-07-04 16:45:06','2025-07-05 20:44:47','15 min'),(3,'Braised Eggplant','','Braised_Eggplant_img.jpg','2025-07-04 16:52:22','2025-07-05 20:44:47','20 min'),(4,'Kung Pao Chicken','','Kung_Pao_Chicken_img.jpg','2025-07-04 13:42:13','2025-07-05 20:44:47','25 min'),(5,'Mapo Tofu',NULL,'Mapo_Tofu_img.jpg','2025-07-04 13:15:34','2025-07-05 20:44:47','30 min'),(6,'Sweet and Sour Pork',NULL,'Sweet_and_Sour_Pork_img.jpg','2025-07-04 13:23:27','2025-07-05 20:44:47','35 min'),(7,'Beef with Broccoli',NULL,'Beef_with_Broccoli_img.jpg','2025-07-04 13:24:38','2025-07-05 20:44:47','20 min'),(8,'Shredded Potato Stir-fry','','Shredded_Potato_Stir-fry_img.jpg','2025-07-04 13:25:41','2025-07-05 20:44:47','15 min'),(9,'Hot and Sour Soup','A flavorful soup with a spicy and sour kick.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','40 min'),(10,'Twice-Cooked Pork','Sichuan-style pork boiled then stir-fried with vegetables.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','45 min'),(11,'Steamed Fish with Ginger and Scallion','Delicate steamed fish with aromatic toppings.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','25 min'),(12,'Cabbage Stir-fry','Quick stir-fry of cabbage with garlic and soy sauce.','','2025-06-30 21:49:36','2025-07-05 20:44:47','10 min'),(13,'Fried Rice with Egg','Simple and delicious egg fried rice.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','12 min'),(14,'Braised Chicken with Mushroom','Slow-cooked chicken with rich mushroom flavor.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','50 min'),(15,'Sour and Spicy Lotus Root','Lotus root slices stir-fried with chili and vinegar.',NULL,'2025-06-30 21:49:36','2025-07-05 20:44:47','18 min');
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
INSERT INTO `step` VALUES (1,1,'Crack the eggs into a bowl, add a pinch of salt, and beat them until well mixed. Wash the tomatoes and cut them into small or medium-sized chunks, depending on your texture preference. If using, finely chop the green onion.'),(2,1,'Heat some oil in a pan over medium heat. Pour in the beaten eggs and stir gently until they just set and become fluffy and golden. Be careful not to overcook. Remove the eggs from the pan and set them aside.'),(3,1,'Add a bit more oil to the same pan and sauté the green onions briefly if using. Add the tomato chunks and stir-fry for about 2–3 minutes. Optionally, add a small splash of water to help release the tomato juices. Season with salt and a pinch of sugar to balance the sourness.'),(4,1,'Return the scrambled eggs to the pan and mix them with the softened tomatoes. Stir-fry everything together for about 30 seconds to 1 minute, allowing the eggs to soak up the tomato flavor. Taste and adjust seasoning if needed, then serve hot.'),(1,2,'Slice the pork thinly into strips. Marinate it with soy sauce, cooking wine, a pinch of salt, an cornstarch for 10–15 minutes. Wash and deseed the green pepper, then cut into thin strips. Peel and slice the garlic if using.'),(2,2,'Heat some oil in a pan or wok over medium-high heat. Add the pork and stir-fry quickly until it changes color and firms up, about 1–2 minutes. Take it out and set aside.'),(3,2,'Add a little more oil to the pan if needed, then stir-fry the green pepper strips until slightly tender but still crisp, about 1–2 minutes. Add the garlic and stir briefly if using.'),(4,2,'Return the pork to the pan and stir everything together for another 30 seconds to 1 minute. Adjust salt or soy sauce to taste, then serve immediately.'),(5,2,'shjhjh'),(1,3,'Wash the eggplant and cut it into medium-sized strips. Soak them in salted water for 10 minutes to reduce bitterness and prevent browning, then drain well.'),(2,3,'Heat the oil in a pan or wok over medium-high heat. Add the eggplant strips and fry until they become soft and slightly browned, about 5–7 minutes. Remove and set aside.'),(3,3,'In the same pan, add a little more oil if needed, then sauté the minced garlic, ginger, and the white part of the green onion until fragrant. Stir in the soy sauce, vinegar, sugar, and water. Let it simmer briefly.'),(4,3,'Mix cornstarch with 2tbsp water thoroughly until smooth, set aside.'),(5,3,'Return the eggplant to the pan, toss to coat with the sauce, then slowly add the cornstarch slurry to thicken. Stir until the sauce clings to the eggplant and everything is heated through. Adjust salt if needed, garnish with green onion tops, and serve hot.'),(1,4,'Dice the chicken into bite-sized cubes and mix with soy sauce, cooking wine, and cornstarch. Let it sit for 10–15 minutes.'),(2,4,'In a small bowl, combine soy sauce, black vinegar, sugar, and water. Mix well and set aside.'),(3,4,'Heat oil in a wok or pan over medium-high heat. Add the marinated chicken and stir-fry until cooked through and lightly browned, about 3–4 minutes. Remove and set aside.'),(4,4,'Add a little more oil to the pan, then stir-fry the dried chilies, minced garlic, ginger, and white part of the green onion until fragrant, about 30 seconds.'),(5,4,'Return the chicken to the pan, add the sauce, and stir quickly. Pour in the cornstarch slurry gradually, stirring until the sauce thickens and coats the chicken evenly.'),(6,4,'Add the peanuts and green onion tops, stir briefly, then remove from heat and serve immediately.'),(1,5,'Cut tofu into cubes.'),(2,5,'Cook ground pork with Doubanjiang.'),(3,5,'Add tofu and simmer with sauce.'),(4,5,'Add garlic and serve hot.'),(1,6,'Cut pork into bite-sized pieces and fry.'),(2,6,'Prepare sweet and sour sauce.'),(3,6,'Stir-fry pork with pineapple and sauce.'),(1,7,'Slice beef thinly.'),(2,7,'Blanch broccoli.'),(3,7,'Stir-fry beef with garlic.'),(4,7,'Add broccoli and sauce, cook briefly.'),(1,8,'Shred potatoes and soak in water.'),(2,8,'Stir-fry chili and potato.'),(3,8,'Add vinegar and salt, cook until crisp.'),(1,9,'Prepare tofu and mushrooms.'),(2,9,'Boil ingredients with vinegar and pepper.'),(3,9,'Stir in cornstarch to thicken.'),(1,10,'Boil pork belly then slice thin.'),(2,10,'Stir-fry pork with bean paste.'),(3,10,'Add cabbage and garlic, stir-fry together.'),(1,11,'Clean the fish and make cuts.'),(2,11,'Place ginger and scallion on fish.'),(3,11,'Steam for 10 minutes and pour soy sauce.'),(1,12,'Chop cabbage and garlic.'),(2,12,'Stir-fry garlic and cabbage.'),(3,12,'Add soy sauce and cook until tender.'),(1,13,'Beat eggs and cook scrambled.'),(2,13,'Add rice and stir-fry.'),(3,13,'Add soy sauce and scallion, mix well.'),(1,14,'Cut chicken and clean mushrooms.'),(2,14,'Stir-fry chicken with ginger.'),(3,14,'Add mushrooms and simmer with sauce.'),(1,15,'Slice lotus root thinly.'),(2,15,'Stir-fry chili and garlic.'),(3,15,'Add lotus root and vinegar, cook briefly.');
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

-- Dump completed on 2025-07-05 21:50:26
