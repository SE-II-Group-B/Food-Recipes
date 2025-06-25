package database;

import entity.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/cookbook?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		String query = "SELECT * FROM recipe";

		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String imagePath = resultSet.getString("image_path");

				Recipe recipe = new Recipe(id, name, description, imagePath);
				recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
    
    public static void insertRecipe(Recipe recipe) {
		String query = "INSERT INTO recipe (recipe_name, description, img_path) VALUES (?, ?, ?)";

		try (Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, recipe.getName());
			preparedStatement.setString(2, recipe.getDescription());
			preparedStatement.setString(3, recipe.getImagePath());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void updateRecipe(Recipe recipe) {
    	String query = "UPDATE recipe SET recipe_name = ?, description = ?, img_path = ? WHERE id = ?";
    	try (Connection connection = getConnection();
    	PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    		preparedStatement.setString(1, recipe.getName());
    		preparedStatement.setString(2, recipe.getDescription());
    		preparedStatement.setString(3, recipe.getImagePath());
    		preparedStatement.setInt(4, recipe.getId());
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void deleteRecipe(int recipeId) {
    	String query = "DELETE FROM recipe WHERE recipe_name = ?";
    	try (Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, recipeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
}
