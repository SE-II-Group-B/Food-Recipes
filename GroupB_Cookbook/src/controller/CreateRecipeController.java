package controller;

import javafx.collections.*;
import javafx.stage.Stage;
import model.*;
import view.CreateRecipeView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import entity.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateRecipeController {

    private static final String RECIPE_NAME_EMPTY_WARNING = "Recipe name cannot be empty!";
    private static final String RECIPE_SAVED_INFO = "Recipe saved!";
    private static final String WARNING_TITLE = "Warning";
    private static final String INFO_TITLE = "Info";

    /**
     * Bind UI events and initialize data for the view
     * @param stage the primary stage
     * @param view the CreateRecipeView instance
     */
    public void bindView(Stage stage, CreateRecipeView view) {
        // Validate input parameters
        Objects.requireNonNull(stage, "Stage cannot be null");
        Objects.requireNonNull(view, "View cannot be null");
        
        // Initialize the ingredients table with some empty rows
        ObservableList<Ingredient> data = FXCollections.observableArrayList(
                new Ingredient("", ""),
                new Ingredient("", ""),
                new Ingredient("", "")
        );
        view.getTable().setItems(data);

        // Add row button action
        view.getAddRowBtn().setOnAction(e -> view.getTable().getItems().add(new Ingredient("", "")));

        // Delete selected row button action
        view.getDelRowBtn().setOnAction(e -> {
            Ingredient selected = view.getTable().getSelectionModel().getSelectedItem();
            if (selected != null) {
                view.getTable().getItems().remove(selected);
            }
        });

        // Save recipe button action
        view.getSaveButton().setOnAction(e -> saveRecipe(stage, view));
    }

    /**
     * Handles the recipe saving logic
     */
    private void saveRecipe(Stage stage, CreateRecipeView view) {
        String title = view.getTitleField().getText().trim();
        int servings = view.getServingsSpinner().getValue();
        String instructions = view.getInstructionsArea().getText().trim();
        String imagePath = ""; 

        // Validate recipe name input
        if (title.isBlank()) {
            showAlert(AlertType.WARNING, WARNING_TITLE, RECIPE_NAME_EMPTY_WARNING);
            return;
        }

        // Collect non-empty ingredients from table
        List<String> ingredientList = view.getTable().getItems().stream()
                .filter(ing -> !ing.getName().isBlank() && !ing.getAmount().isBlank())
                .map(ing -> String.format("%s: %s", ing.getName(), ing.getAmount()))
                .collect(Collectors.toList());

        // Create a Recipe object and save it
        Recipe recipe = new Recipe(title, imagePath, servings, ingredientList, instructions);
        Model.saveRecipe(recipe);

        // Show info popup on successful save
        showAlert(AlertType.INFORMATION, INFO_TITLE, RECIPE_SAVED_INFO);
        stage.close();
    }

    /**
     * Shows a standardized alert dialog
     * @param type the alert type (e.g., WARNING, INFORMATION)
     * @param title the dialog title
     * @param message the content message
     */
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}