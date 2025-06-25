package model;

import entity.Recipe;
import database.RecipeDAO;
import entity.Ingredient;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Model {
	
	List<Recipe> recipes = RecipeDAO.getAllRecipes();
	
	public static int getRecipeCount() {
		return RecipeDAO.getAllRecipes().size();
	}

	public static ImageView getImageViewFromPath(String imagePath) {
	    ImageView imageView = new ImageView();
	    try {
	        Image image = new Image(imagePath);
	        imageView.setImage(image);
	        imageView.setFitWidth(50);    
	        imageView.setFitHeight(50);   
	        imageView.setPreserveRatio(true); 
	    } catch (Exception e) {
	        imageView.setImage(null);
	    }
	    return imageView;
	}
	
	public static List<Recipe> getAllRecipes() {
		return RecipeDAO.getAllRecipes();
	}
	
	public static void saveRecipe(Recipe recipe) {
		RecipeDAO.insertRecipe(recipe);
		System.out.println("Recipe saved: " + recipe.getName());
	}
	
	public static void insertRecipe(Recipe recipe) {
		RecipeDAO.insertRecipe(recipe);
		System.out.println("Inserted recipe: " + recipe.getName());
	}
	
	public static void updateRecipe(Recipe recipe) {
		RecipeDAO.updateRecipe(recipe);
		System.out.println("Updated recipe: " + recipe.getName());
	}
	
	public static void deleteRecipe(int recipeId) {
		RecipeDAO.deleteRecipe(recipeId);
		System.out.println("Deleted recipe with ID: " + recipeId);
	}
	
	


}
