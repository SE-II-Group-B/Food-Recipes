package entity;

public class Recipe {
    private int recipeId;
    private String recipeName;
    String description; 
    private String imgPath;
    private String searchTime; 
    private String preptime; 

    public Recipe(int recipeId, String recipeName, String description, String imgPath, String preptime) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.description = description;
        this.imgPath = imgPath;
        this.preptime = preptime;
    }

    public Recipe() {}

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

	@Override
	public String toString() {
		return "Recipe{" +
				"recipeId=" + recipeId +
				", recipeName='" + recipeName + '\'' +
				", description='" + description + '\'' +
				", imgPath='" + imgPath + '\'' +
				", preptime='" + preptime + '\'' +
				", searchTime='" + searchTime + '\'' +
				'}';
	}
}
