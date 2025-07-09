package view;

import controller.SearchController;
import entity.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * SearchView provides a UI to search for recipes by name.
 * Supports entering a keyword to perform the search, displaying results,
 * and double-clicking to open recipe details.
 * Includes a back button to return to the main menu.
 */
public class SearchView extends BorderPane {

    private final SearchController searchController = new SearchController();
    private final TextField searchField = new TextField();
    private final TableView<Recipe> resultTable = new TableView<>();
    private final Button backButton = new Button("Back");

    /**
     * No-argument constructor:
     * Displays a search input field with a search button,
     * allows user to enter a keyword and perform a search.
     */
    public SearchView() {
        initTableColumns();
        setupSearchControls();

        VBox vbox = new VBox(10, searchField, createSearchButton(), resultTable, backButton);
        vbox.setPadding(new Insets(20));
        this.setCenter(vbox);

        // Set back button action to return to the main view
        backButton.setOnAction(e -> returnToMain());
    }

    /**
     * Constructor with keyword:
     * Displays search results for the given keyword directly.
     * Supports double-click on a recipe to open its details.
     * @param keyword the search keyword to display results for
     */
    public SearchView(String keyword) {
        initTableColumns();

        loadSearchResults(keyword);

        setupRowDoubleClick();

        backButton.setOnAction(e -> returnToMain());

        VBox vbox = new VBox(10, resultTable, backButton);
        vbox.setPadding(new Insets(20));
        this.setCenter(vbox);
    }

    /**
     * Initialize table columns to display recipe ID and name.
     */
    private void initTableColumns() {
        TableColumn<Recipe, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("recipeId"));

        TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

        resultTable.getColumns().addAll(idCol, nameCol);
        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Set up search input field prompt text and bind Enter key event to search.
     */
    private void setupSearchControls() {
        searchField.setPromptText("Enter recipe name to search...");
        searchField.setMaxWidth(300);

        // Perform search when pressing Enter in the text field
        searchField.setOnAction(e -> doSearch());
    }

    /**
     * Create the search button with click event bound to perform the search.
     * @return the search button instance
     */
    private Button createSearchButton() {
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(e -> doSearch());
        return searchBtn;
    }

    /**
     * Perform search using the text from the input field,
     * update the result table with matched recipes.
     */
    private void doSearch() {
        String keyword = searchField.getText().trim();
        loadSearchResults(keyword);
    }

    /**
     * Query the controller for recipes matching the keyword,
     * and display the results in the table.
     * @param keyword search keyword
     */
    private void loadSearchResults(String keyword) {
        List<Recipe> results = searchController.searchRecipes(keyword);
        ObservableList<Recipe> data = FXCollections.observableArrayList(results);
        resultTable.setItems(data);
    }

    /**
     * Configure double-click event on table rows.
     * On double-click, open the selected recipe's detail view
     * and add the search entry to the history.
     */
    private void setupRowDoubleClick() {
        resultTable.setRowFactory(tv -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Recipe selected = row.getItem();

                    // Save search history via DAO
                    model.RecipeDAO dao = new model.RecipeDAO();
                    dao.insertHistory(selected.getRecipeName());

                    // Open recipe detail view in a new stage
                    Stage currentStage = (Stage) resultTable.getScene().getWindow();
                    currentStage.hide();

                    Stage detailStage = new Stage();
                    CurrentRecipeView view = new CurrentRecipeView(selected);
                    detailStage.setScene(new Scene(view, 800, 600));
                    detailStage.setTitle(selected.getRecipeName());

                    // Show main window again after detail window closes
                    detailStage.setOnCloseRequest(e -> currentStage.show());

                    detailStage.show();
                }
            });
            return row;
        });
    }

    /**
     * Close current stage and open the main view.
     */
    private void returnToMain() {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();

        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(new MainView(), 800, 600));
        mainStage.setTitle("Main Menu");
        mainStage.show();
    }
}
