package model;

import entity.Ingredient;
import entity.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cookbook";
	private static final String USER="root";
	private static final String PASS="12345678";
	
	/**
     * Establishes a connection to the database.
     * @return a valid SQL connection
     * @throws SQLException if connection fails
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
     
    public String getURL() {
		return DB_URL;
	}
    
    public String getUser() {
    	return USER;
    }
    
    public String getPass() {
		return PASS;
	}
    
    /**
     * Inserts a new recipe into the database.
     * @param recipeId the ID of the recipe
     * @param name the name of the recipe
     * @param description the description of the recipe
     * @param imgPath the image path for the recipe
     * @param preptime the preparation time
     */
    public void insertRecipe(int recipeId, String name, String description, String imgPath, String preptime) {
        String sql = "INSERT INTO recipe (recipe_id, recipe_name, description, img_path, preptime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setString(4, imgPath);
            stmt.setString(5, preptime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Inserts a new ingredient into the database for a specific recipe.
	 * @param recipeId the ID of the recipe
	 * @param name the name of the ingredient
	 * @param unit the unit of measurement for the ingredient
	 * @param quantity the quantity of the ingredient
	 */
    public void insertIngredient(int recipeId, String name, String unit, int quantity) {
        String sql = "INSERT INTO ingredient (recipe_id, ingredient_name, unit, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            stmt.setString(2, name);
            stmt.setString(3, unit);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Inserts a new step into the database for a specific recipe.
	 * @param recipeId the ID of the recipe
	 * @param step_id the step number (ID)
	 * @param instruction the instruction for this step
	 */
    public void insertStep(int recipeId, int step_id,String instruction) {
        String sql = "INSERT INTO step (recipe_id, step_id, instruction) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            stmt.setInt(2, step_id);
            stmt.setString(3, instruction);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Deletes a recipe and all its associated ingredients and steps by recipe ID.
	 * @param recipeId the ID of the recipe to delete
	 */
    public void deleteRecipeById(int recipeId) {
        String deleteStep = "DELETE FROM step WHERE recipe_id = ?";
        String deleteIngredient = "DELETE FROM ingredient WHERE recipe_id = ?";
        String deleteRecipe = "DELETE FROM recipe WHERE recipe_id = ?";
        try (Connection conn = getConnection()) {
            try (PreparedStatement psStep = conn.prepareStatement(deleteStep);
                 PreparedStatement psIngredient = conn.prepareStatement(deleteIngredient);
                 PreparedStatement psRecipe = conn.prepareStatement(deleteRecipe)) {
                psStep.setInt(1, recipeId);
                psStep.executeUpdate();

                psIngredient.setInt(1, recipeId);
                psIngredient.executeUpdate();

                psRecipe.setInt(1, recipeId);
                psRecipe.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Retrieves all recipes from the database.
	 * @return a list of Recipe objects
	 */
    public List<Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        String sql = "SELECT * FROM recipe ORDER BY recipe_id ASC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recipe r = new Recipe();
                r.setRecipeId(rs.getInt("recipe_id"));
                r.setRecipeName(rs.getString("recipe_name"));
                r.setDescription(rs.getString("description"));
                r.setImgPath(rs.getString("img_path"));
                r.setPreptime(rs.getString("preptime"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
	 * Retrieves all ingredients for a specific recipe by its ID.
	 * @param recipeId the ID of the recipe
	 * @return a list of Ingredient objects
	 */
    public List<Ingredient> getIngredientsByRecipeId(int recipeId) {
        List<Ingredient> list = new ArrayList<>();
        String sql = "SELECT * FROM ingredient WHERE recipe_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ingredient ing = new Ingredient();
                    ing.setIngredientId(rs.getInt("ingredient_id"));
                    ing.setRecipeId(recipeId);
                    ing.setIngredientName(rs.getString("ingredient_name"));
                    ing.setUnit(rs.getString("unit"));
                    ing.setQuantity(rs.getInt("quantity"));
                    list.add(ing);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
	 * Retrieves all steps for a specific recipe by its ID.
	 * @param recipeId the ID of the recipe
	 * @return a list of step instructions
	 */
    public List<String> getStepsByRecipeId(int recipeId) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT instruction FROM step WHERE recipe_id = ? ORDER BY step_id ASC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, recipeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("instruction"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
	 * Retrieves a recipe by its name.
	 * @param name the name of the recipe
	 * @return a Recipe object if found, otherwise null
	 */
    public Recipe getRecipeByName(String name) {
        String sql = "SELECT * FROM recipe WHERE recipe_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setRecipeId(rs.getInt("recipe_id"));
                    recipe.setRecipeName(rs.getString("recipe_name"));
                    recipe.setDescription(rs.getString("description"));
                    recipe.setImgPath(rs.getString("img_path"));
                    recipe.setPreptime(rs.getString("preptime"));
                    return recipe;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
	 * Retrieves the next available recipe ID by checking existing IDs in the database.
	 * @return the next available recipe ID
	 */
    public int getNextAvailableRecipeId() {
        int id = 1;
        String query = "SELECT recipe_id FROM recipe ORDER BY recipe_id";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                if (rs.getInt("recipe_id") == id) {
                    id++;
                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    /**
	 * Saves a new recipe to the database.
	 * @param recipe the Recipe object to save
	 */
    public void saveRecipe(Recipe recipe) {
        String sql = "INSERT INTO recipe (recipe_id, recipe_name, description, img_path, preptime) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipe.getRecipeId());
            stmt.setString(2, recipe.getRecipeName());
            stmt.setString(3, recipe.getDescription());
            stmt.setString(4, recipe.getImgPath());
            stmt.setString(5, recipe.getPreptime());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Updates an existing recipe in the database.
	 * @param recipe the Recipe object with updated information
	 */
    public void updateRecipe(Recipe recipe) {
        String sql = "UPDATE recipe SET recipe_name = ?, description = ?, img_path = ?, preptime = ? WHERE recipe_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recipe.getRecipeName());
            stmt.setString(2, recipe.getDescription());
            stmt.setString(3, recipe.getImgPath());
            stmt.setString(4, recipe.getPreptime());
            stmt.setInt(5, recipe.getRecipeId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * Retrieves a recipe by its ID.
	 * @param id the ID of the recipe
	 * @return a Recipe object if found, otherwise null
	 */
    public Recipe getRecipeById(int id) {
        String sql = "SELECT * FROM recipe WHERE recipe_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setRecipeId(rs.getInt("recipe_id"));
                    recipe.setRecipeName(rs.getString("recipe_name"));
                    recipe.setDescription(rs.getString("description"));
                    recipe.setImgPath(rs.getString("img_path"));
                    recipe.setPreptime(rs.getString("preptime"));
                    return recipe;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Gets the 10 most recently searched unique recipe names (by search time).
     * @return a list of recipe names
     */
    public List<String> getRecentSearchedRecipes() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT recipe_name FROM history GROUP BY recipe_name ORDER BY MAX(SearchTime) DESC LIMIT 10";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("recipe_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
	 * Searches for recipes by name using a keyword.
	 * @param keyword the keyword to search for in recipe names
	 * @return a list of Recipe objects matching the search criteria
	 */
    public List<Recipe> searchRecipesByName(String keyword) {
        List<Recipe> result = new ArrayList<>();
        String sql = "SELECT * FROM recipe WHERE recipe_name LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recipe r = new Recipe();
                    r.setRecipeId(rs.getInt("recipe_id"));
                    r.setRecipeName(rs.getString("recipe_name"));
                    r.setImgPath(rs.getString("img_path"));
                    result.add(r);
                    // 不再写入history
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
	 * Inserts a search history record for a recipe.
	 * If the recipe name already exists, updates the search time to now.
	 * @param recipeName the name of the recipe being searched
	 */
    public void insertHistory(String recipeName) {
        String sql = "INSERT INTO history (recipe_name, SearchTime) VALUES (?, NOW()) " +
                "ON DUPLICATE KEY UPDATE SearchTime = NOW()";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, recipeName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
	 * Retrieves the 10 most recent searched recipe entries with their search times.
	 * @return a list of Map.Entry pairs, where the key is the recipe name
	 *         and the value is the search time
	 */
    public List<java.util.Map.Entry<String, String>> getRecentSearchedRecipeEntries() {
        List<java.util.Map.Entry<String, String>> list = new java.util.ArrayList<>();
        String sql = "SELECT recipe_name, MAX(SearchTime) as SearchTime FROM history GROUP BY recipe_name ORDER BY SearchTime DESC LIMIT 10";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("recipe_name");
                String time = rs.getString("SearchTime");
                list.add(new java.util.AbstractMap.SimpleEntry<>(name, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
