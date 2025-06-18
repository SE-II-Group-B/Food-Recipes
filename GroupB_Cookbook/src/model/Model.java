package model;

import entity.Recipe;
import entity.Ingredient;
import java.util.List;

public class Model {
	
	public static void saveRecipe(Recipe recipe) {
		System.out.println("Saving recipe: " + recipe.getName());
	}
	
	public static List<Recipe> getAllRecipes() {
		List<Recipe> recipes = List.of(
			new Recipe("Pasta", "pasta.jpg", 4, List.of("Pasta: 200g", "Tomato Sauce: 100ml"), "Boil pasta and add sauce."),
			new Recipe("Salad", "salad.jpg", 2, List.of("Lettuce: 100g", "Tomato: 50g"), "Mix ingredients.")
		);
		return recipes;
	}
}
