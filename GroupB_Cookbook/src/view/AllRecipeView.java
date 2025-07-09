package view;

import controller.AllRecipeController;
import entity.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.RecipeDAO;

/**
 * Displays a list of all recipes in a table view.
 * Provides options to view details (on double-click), delete a selected recipe, or go back to main view.
 */
public class AllRecipeView extends BorderPane {

    private final TableView<Recipe> recipeTable = new TableView<>();
    private final Button backButton = new Button("Back");
    private final Button deleteButton = new Button("Delete");
    private final AllRecipeController controller = new AllRecipeController();

    /**
     * Constructs the AllRecipeView UI, including table setup, button handlers, and close behavior.
     */
    public AllRecipeView() {
        // Set up table columns
        TableColumn<Recipe, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("recipeId"));

        TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

        recipeTable.getColumns().addAll(idCol, nameCol);
        recipeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        recipeTable.setItems(loadData());

        // Set row click behavior: single click to select, double click to open detail
        recipeTable.setRowFactory(tv -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty()) {
                    Recipe selected = row.getItem();
                    if (e.getClickCount() == 2) {
                        controller.openRecipeDetail(selected, row);
                    } else if (e.getClickCount() == 1) {
                        recipeTable.getSelectionModel().select(selected);
                    }
                }
            });
            return row;
        });

        // Back button returns to main view
        backButton.setOnAction(e -> controller.goBackToMain(backButton));

        // Delete button prompts for confirmation, deletes recipe if confirmed
        deleteButton.setOnAction(e -> {
            Recipe selected = recipeTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Confirm Deletion");
                confirm.setHeaderText("Are you sure you want to delete this recipe?");
                confirm.setContentText("Recipe: " + selected.getRecipeName());

                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        controller.deleteRecipe(selected);
                        recipeTable.setItems(loadData());
                    }
                });
            } else {
                showAlert("Please select a recipe to delete.");
            }
        });

        // Layout: button bar below table, scrollable center content
        HBox buttons = new HBox(20, backButton, deleteButton);
        buttons.setPadding(new Insets(10));

        VBox content = new VBox(20, recipeTable, buttons);
        content.setPadding(new Insets(20));
        content.setPrefWidth(600);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);

        // Handle window close: return to main view
        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obsWin, oldWin, newWin) -> {
                    if (newWin instanceof Stage) {
                        Stage stage = (Stage) newWin;
                        stage.setOnCloseRequest(e -> controller.goBackToMain(recipeTable));
                    }
                });
            }
        });
    }

    /**
     * Loads all recipes from the database via DAO.
     * @return observable list of Recipe objects
     */
    private ObservableList<Recipe> loadData() {
        RecipeDAO dao = new RecipeDAO();
        return FXCollections.observableArrayList(dao.getAllRecipes());
    }

    /**
     * Displays a warning alert with the given message.
     * @param msg the warning message to display
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
