package controller;

import javafx.stage.Stage;
import model.*;
import view.CreateRecipeView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateRecipeController {

    private static final String RECIPE_NAME_EMPTY_WARNING = "Recipe name cannot be empty!";
    private static final String RECIPE_SAVED_INFO = "Recipe saved!";
    private static final String WARNING_TITLE = "Warning";
    private static final String INFO_TITLE = "Info";

    /**
     * Bind UI events and initialize data for the view
     */
    public void bindView(Stage stage, CreateRecipeView view) {
        Objects.requireNonNull(stage);
        Objects.requireNonNull(view);

        view.addIngredientRow();
        view.addIngredientRow();
        view.addStepRow();

        view.getAddIngredientBtn().setOnAction(e -> view.addIngredientRow());
        view.getAddStepBtn().setOnAction(e -> view.addStepRow());

        view.getSaveButton().setOnAction(e -> saveRecipe(stage, view));
    }

    /**
     * save recipe
     */
    private void saveRecipe(Stage stage, CreateRecipeView view) {
        String title = view.getTitleField().getText().trim();
        String imagePath = "";

        if (title.isBlank()) {
            showAlert(AlertType.WARNING, WARNING_TITLE, RECIPE_NAME_EMPTY_WARNING);
            return;
        }

        List<Ingredient> ingredients = extractIngredients(view.getIngredientListBox());
        List<String> steps = extractSteps(view.getStepsListBox());
        String description = view.getDescriptionBox().getText().trim();
        
        Recipe recipe = new Recipe(Model.getRecipeCount(), title, description, imagePath, steps, ingredients);

        Model.saveRecipe(recipe);

        showAlert(AlertType.INFORMATION, INFO_TITLE, RECIPE_SAVED_INFO);
        stage.close();
    }

    private List<Ingredient> extractIngredients(VBox ingredientListBox) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (var node : ingredientListBox.getChildren()) {
            if (node instanceof HBox row) {
                TextField nameField = (TextField) row.getChildren().get(0);
                TextField amountField = (TextField) row.getChildren().get(1);
                TextField unitField = (TextField) row.getChildren().get(2);

                String name = nameField.getText().trim();
                String amountText = amountField.getText().trim();
                String unit = unitField.getText().trim();

                if (!name.isEmpty() && !amountText.isEmpty()) {
                    try {
                        int amount = Integer.parseInt(amountText);
                        ingredients.add(new Ingredient(name, amount, unit));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number for amount: " + amountText);
                    }
                }
            }
        }
        return ingredients;
    }


    private List<String> extractSteps(VBox stepsListBox) {
        List<String> steps = new ArrayList<>();
        for (var node : stepsListBox.getChildren()) {
            if (node instanceof HBox row) {
                TextField stepField = (TextField) row.getChildren().get(0);
                String text = stepField.getText().trim();
                if (!text.isEmpty()) {
                    steps.add(text);
                }
            }
        }
        return steps;
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
