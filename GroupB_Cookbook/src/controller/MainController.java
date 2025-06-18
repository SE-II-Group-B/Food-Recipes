package controller;

import javafx.stage.Stage;
import view.AllRecipeView;
import view.CreateRecipeView;

public class MainController {
    private Stage primaryStage;
    private Stage createRecipeStage;
    private Stage allRecipeStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onCreateRecipeClicked() {
        if (createRecipeStage == null || !createRecipeStage.isShowing()) {
            createRecipeStage = new Stage();
            new CreateRecipeView().show(createRecipeStage, new CreateRecipeController());
        } else {
            flashWindow(createRecipeStage);
        }
    }

    public void onViewRecipesClicked() {
    	if (allRecipeStage == null || !allRecipeStage.isShowing()) {
    		allRecipeStage = new Stage();
    		new AllRecipeView().show(allRecipeStage, new AllRecipeController());
    		} else {
			flashWindow(allRecipeStage);
		}
        
    }

    public void onSearchClicked(String keyword) {
        System.out.println("Search for: " + keyword);
    }

    private void flashWindow(Stage stage) {
        final String originalTitle = stage.getTitle();
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
            new javafx.animation.KeyFrame(javafx.util.Duration.seconds(0), ev -> stage.setTitle("** Attention **")),
            new javafx.animation.KeyFrame(javafx.util.Duration.seconds(0.5), ev -> stage.setTitle(originalTitle))
        );
        timeline.setCycleCount(6);
        timeline.play();
        stage.toFront();
        stage.requestFocus();
    }
}
