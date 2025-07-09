package controller;

import entity.Recipe;
import model.RecipeDAO;

import java.util.List;

public class SearchController {
    
		/**
	 * Searches for recipes by keyword in their names.
	 * @param keyword the keyword to search for
	 * @return a list of recipes that match the keyword
	 */
    public List<Recipe> searchRecipes(String keyword) {
        RecipeDAO dao = new RecipeDAO();
        return dao.searchRecipesByName(keyword);
    }
}
