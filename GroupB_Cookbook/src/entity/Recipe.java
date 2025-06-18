package entity;

import java.util.List;

public class Recipe {
	private String name;
	private List<String> ingredientList;
	private String instructions;
	private int servings;
	private String imagePath;

	public Recipe(String name, String imagePath, int servings, List<String> ingredientList, String instructions) {
		this.name = name;
		this.imagePath = imagePath;
		this.servings = servings;
		this.ingredientList = ingredientList;
		this.instructions = instructions;
	}

	public String getName() {
		return name;
	}
	
	public int getServings() {
		return servings;
	}
	
	public List getIngredientList() {
		return ingredientList;
	}

	public String getInstructions() {
		return instructions;
	}
	
	public String getImagePath() {
		return imagePath;
	}	
	
	// Clearer output
	@Override
	public String toString() {
	    return new StringBuilder(name)
	        .append(" (").append(servings).append(" servings)")
	        .toString();
	}
	
}
