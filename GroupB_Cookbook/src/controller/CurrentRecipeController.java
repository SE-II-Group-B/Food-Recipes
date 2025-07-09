package controller;

import entity.Ingredient;
import entity.Recipe;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.RecipeDAO;
import view.CreateRecipeView;
import view.CurrentRecipeView;

import java.util.List;

public class CurrentRecipeController {

    private final Recipe recipe;

    public CurrentRecipeController(CurrentRecipeView view, Recipe recipe) {
        this.recipe = recipe;
    }
    
    /**
	 * Opens the current recipe detail view, closes the current window, and inserts the recipe to history.
	 * @param source the UI node triggering the event (to get current window)
	 */
    public void editRecipe(Recipe recipe, javafx.scene.Node source) {
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();

        view.EditRecipeView editView = new view.EditRecipeView(recipe);
        Stage stage = new Stage();
        stage.setTitle("Edit Recipe");
        stage.setScene(new Scene(editView, 800, 600));
        
        editView.getController().setWindowCloseHandler(stage);
        
        stage.show();
    }
    
    /**
	 * Opens the current recipe detail view, closes the current window, and inserts the recipe to history.
	 * @param source the UI node triggering the event (to get current window)
	 */
    public void goBack(javafx.scene.Node source) {
        Stage current = (Stage) source.getScene().getWindow();
        current.close();

        Stage allRecipeStage = new Stage();
        allRecipeStage.setTitle("ALL Recipes");
        allRecipeStage.setScene(new Scene(new view.AllRecipeView(), 800, 600));
        allRecipeStage.show();
    }
    
    
    public void setWindowCloseHandler(Stage stage) {
		stage.setOnCloseRequest(event -> {
			Stage mainStage = new Stage();
            mainStage.setScene(new Scene(new view.MainView(), 800, 600));
            mainStage.setTitle("Main Menu");
            mainStage.show();
			stage.close();
			
		});
    }
}
