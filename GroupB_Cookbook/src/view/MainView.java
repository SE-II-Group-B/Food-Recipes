package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Digital Cookbook");

        Button createButton = new Button("Create Recipe");
        Button viewButton = new Button("View Recipes");
        HBox topBar = new HBox(10, createButton, viewButton);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-background-color: #ececec;");

        TextField searchField = new TextField();
        searchField.setPromptText("Enter recipe name...");
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(20, searchBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(centerBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        createButton.setOnAction(e -> {
            System.out.println("Create Recipe Clicked");
        });

        viewButton.setOnAction(e -> {
            System.out.println("View Recipes Clicked");
        });

        searchButton.setOnAction(e -> {
            String keyword = searchField.getText().trim();
            System.out.println("Search for: " + keyword);
        });
    }

    
}
