package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.CreateRecipeController;


public class MainView extends Application {
    private Stage primaryStage;
    private BorderPane root;
    private Stage createRecipeStage; 

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new BorderPane();

        showMainPage();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Digital Cookbook");
        primaryStage.show();
    }

    public void showMainPage() {
        Button createButton = new Button("Create Recipe");
        Button viewButton = new Button("View Recipes");
        HBox topBar = new HBox(10, createButton, viewButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-background-color: #ececec;");
        root.setTop(topBar);

        TextField searchField = new TextField();
        searchField.setPromptText("Enter recipe name...");
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(20, searchBox);
        centerBox.setAlignment(Pos.CENTER);

        root.setCenter(centerBox);

        createButton.setOnAction(e -> {
            if (createRecipeStage == null || !createRecipeStage.isShowing()) {
                createRecipeStage = new Stage();
                new CreateRecipeView().show(createRecipeStage, new CreateRecipeController());
                createRecipeStage.setOnHidden(ev -> createRecipeStage = null);
            } else {
                flashWindow(createRecipeStage);
            }
        });

        viewButton.setOnAction(e -> {
            System.out.println("View Recipes Clicked");
        });

        searchButton.setOnAction(e -> {
            String keyword = searchField.getText().trim();
            System.out.println("Search for: " + keyword);
        });
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
