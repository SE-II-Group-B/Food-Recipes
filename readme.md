# JavaFX Cookbook Application

A desktop application built with JavaFX for managing cooking recipes.  
Users can create, edit, search, and view recipe details. The application also tracks search history and supports exporting records.

## Features

- Search recipes by name  
- Create and edit recipes (name, description, prep time, ingredients, steps, image)  
- Attach images to recipes   
- View full recipe details in a separate window  
- Track recent searches (up to 10 latest, non-repeating)  
- Export search history to a text file  
- Persistent data storage via RecipeDAO class (file-based or SQLite)  

## Project Structure

- src/  
- controller/ - UI logic (MainController, EditRecipeController, etc.)  
- entity/ - Data model classes (Recipe, Ingredient)  
- model/ - Data access and storage logic (RecipeDAO)  
- view/ - JavaFX UI views (MainView, CreateRecipeView, etc.)  
- Main.java - Program entry point  

## Main Views

- **MainView:** Homepage with navigation and search bar  
- **CreateRecipeView:** For creating a new recipe  
- **EditRecipeView:** Modify existing recipes  
- **CurrentRecipeView:** View full details of a selected recipe  
- **SearchView:** Display search results  
- **HistoryView:** Show recent 10 search records (clickable list)  

## Recipe Structure

Each recipe includes:  
- Recipe name  
- Preparation time (value + unit: min or hour)  
- Description(optional)  
- List of ingredients (name, quantity, unit)  
- List of steps  
- Image path (optional)  

## How to Run

1. Install Java 17 or later  
2. Download and setup JavaFX SDK  
3. Add JavaFX libraries to your classpath (or use a supported IDE)
4. Add the mysql-connector.jar which is involved in the project folder to the build path as external JAR  
5. Start up your database service at 3306 port with username "root" and password "12345678"( or you can modify the database access value in model.recipeDAO.java )  
6. In your database software, run the given sql file to insert all data.  
6. Run `Main.java` in main package  

## Example Usage

- Open the app and click **Create Recipe** to add a new recipe  
- Fill in the name, time, ingredients, steps, and image path  
- Click **Save** to store the recipe  
- Use the search bar to find a recipe by name  
- Double-click a result to view its details  
- View previous searches under the **History** tab

## Notes

- The project assumes recipes are **uniquely** identified by name  
- Search history updates only when you **search** for a recipe and **open** a recipe detail window  
- Data persistence can be customized in the `RecipeDAO` class  
- For many numeric value columns, it is restricted to only numeric values, no other datatype is allowed.  

## Planned Improvements

- Add category or tags to recipes  
- Improve image file preview  
- Sort/filter recipes in table views  

