package model;

import entity.Recipe;
import entity.Ingredient;

public class Model {
	
	public static void saveRecipe(Recipe recipe) {
		System.out.println("Saving recipe: " + recipe.getName());
	}
	
	public void getAllRecipes() {
		// Logic to retrieve all recipes
	}
}
