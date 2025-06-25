// entity/Recipe.java
package entity;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String imagePath;
    private List<String> steps;      
    private List<Ingredient> ingredients;  

    public Recipe(int id, String name, String description, String imagePath, List<String> steps, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.steps = steps;
        this.ingredients = ingredients;
    }
    
    public Recipe(int id,String name, String description, String imagePath) {
		this.id = id;
    	this.name = name;
		this.description = description;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

    
}
