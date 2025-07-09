package view;

import controller.MainController;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * Authorized and written by SE II Group B
 *  
 * MainView is the entry screen of the cookbook application.
 * It provides navigation buttons to view all recipes, create a new recipe,
 * or view the search history. It also includes a search bar for quick access.
 */
public class MainView extends BorderPane {

    private final MainController controller = new MainController();

    /**
     * Constructs the MainView layout.
     * Sets up navigation buttons, title label, and search functionality.
     */
    public MainView() {
        this.setPadding(new Insets(20));

        // Top navigation buttons
        Button allRecipeButton = new Button("All Recipes");
        Button createRecipeButton = new Button("Create Recipe");
        Button historyButton = new Button("History");

        // Bind navigation events to controller
        allRecipeButton.setOnAction(e -> controller.openAllRecipe(allRecipeButton));
        createRecipeButton.setOnAction(e -> controller.openCreateRecipe(createRecipeButton));
        historyButton.setOnAction(e -> controller.openHistory(historyButton));

        HBox topButtons = new HBox(20, allRecipeButton, createRecipeButton, historyButton);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(20));

        // Title label
        Label titleLabel = new Label("Welcome to the Cookbook!");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        titleLabel.setAlignment(Pos.CENTER);

        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, 40, 0));

        // Search bar and button
        TextField searchField = new TextField();
        searchField.setPromptText("Search recipes...");
        searchField.setMaxWidth(300);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> controller.openSearchView(searchButton, searchField.getText()));

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(titleBox, searchBox);
        centerBox.setAlignment(Pos.CENTER);

        // Assemble layout
        this.setTop(topButtons);
        this.setCenter(centerBox);
    }
}
