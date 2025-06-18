package entity;

import java.util.List;
import javafx.beans.property.StringProperty;

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
	
	public StringProperty imagePathProperty() {
		return imagePath;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty servingsProperty() {
		return String.valueOf(servings);
	}
}
