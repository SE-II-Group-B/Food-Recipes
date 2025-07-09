package controller;

import entity.Recipe;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RecipeDAO;
import view.CurrentRecipeView;
import view.MainView;

import java.io.File;

/**
 * Controller for managing all recipe-related navigation and actions.
 */
public class AllRecipeController {

    private final RecipeDAO dao = new RecipeDAO();

    /**
     * Opens the detail view of a given recipe.
     *
     * @param recipe the recipe to display
     * @param source the UI node that triggered this action
     */
    public void openRecipeDetail(final Recipe recipe, final Node source) {
        // Write to history
        dao.insertHistory(recipe.getRecipeName());

        // Create and configure recipe detail view
        CurrentRecipeView recipeView = new CurrentRecipeView(recipe);
        Stage stage = new Stage();
        stage.setTitle("Recipe Detail");
        recipeView.getController().setWindowCloseHandler(stage);

        // Close the current stage
        Stage current = (Stage) source.getScene().getWindow();
        current.close();

        // Show new stage
        stage.setScene(new Scene(recipeView, 800, 600));
        stage.show();
    }

    /**
     * Navigates back to the main view.
     *
     * @param source the UI node that triggered this action
     */
    public void goBackToMain(final Node source) {
        // Close the current stage
        Stage current = (Stage) source.getScene().getWindow();
        current.close();

        // Open the main view
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(new MainView(), 800, 600));
        mainStage.setTitle("Main Menu");
        mainStage.show();
    }

    /**
     * Deletes the specified recipe and its associated image file.
     *
     * @param recipe the recipe to delete
     */
    public void deleteRecipe(final Recipe recipe) {
        // Delete from database
        dao.deleteRecipeById(recipe.getRecipeId());

        // Delete the image file
        File image = new File("resources/img/" + recipe.getImgPath());
        if (image.exists()) {
            boolean deleted = image.delete();
            // Optionally log: System.out.println("Image deleted: " + deleted);
        }
    }
}
