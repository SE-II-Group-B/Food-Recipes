package controller;

import entity.Recipe;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.RecipeDAO;
import view.CreateRecipeView;
import view.CurrentRecipeView;
import view.MainView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateRecipeController {

    private static final int MAX_PREP_TIME = 9999;
    private static final int MIN_PREP_TIME = 1;
    private static final int MAX_INGREDIENT_QUANTITY = 999999;
    private static final int MIN_INGREDIENT_QUANTITY = 1;

    private static final String IMAGE_RESOURCES_PATH = "resources/img/";

    private final CreateRecipeView view;
    private final RecipeDAO recipeDAO = new RecipeDAO();

    private File selectedImageSource = null;
    private Stage returnStage;

    /**
     * Constructor binds the view.
     * @param view The CreateRecipeView instance.
     */
    public CreateRecipeController(CreateRecipeView view) {
        this.view = view;
    }

    // ===== UI METHODS =====

    /**
     * Add initial ingredient input rows.
     * @param count Number of rows to add.
     */
    public void addInitialIngredients(int count) {
        for (int i = 0; i < count; i++) {
            addIngredientRow();
        }
    }

    /**
     * Add one ingredient input row.
     */
    public void addIngredientRow() {
        HBox row = new HBox(10);
        TextField name = new TextField();
        TextField quantity = new TextField();
        TextField unit = new TextField();
        Button removeBtn = new Button("-");

        name.setPromptText("Name");
        quantity.setPromptText("Quantity");
        unit.setPromptText("Unit");

        // Allow only digits in quantity field
        quantity.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantity.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });

        removeBtn.setOnAction(e -> view.getIngredientBox().getChildren().remove(row));
        row.getChildren().addAll(name, quantity, unit, removeBtn);
        view.getIngredientBox().getChildren().add(row);
    }

    /**
     * Add initial step input rows.
     * @param count Number of rows to add.
     */
    public void addInitialSteps(int count) {
        for (int i = 0; i < count; i++) {
            addStepRow();
        }
    }

    /**
     * Add one cooking step input row.
     */
    public void addStepRow() {
        HBox row = new HBox(10);
        TextArea instruction = new TextArea();
        Button removeBtn = new Button("-");

        instruction.setPromptText("Instruction");
        instruction.setPrefRowCount(3);
        instruction.setWrapText(true);
        instruction.setPrefWidth(500);

        removeBtn.setOnAction(e -> view.getStepBox().getChildren().remove(row));
        row.getChildren().addAll(instruction, removeBtn);
        view.getStepBox().getChildren().add(row);
    }

    /**
     * Open file chooser to select an image and set image name field.
     */
    public void handleImageSelection() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Recipe Image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selected = fileChooser.showOpenDialog(null);
        if (selected != null) {
            selectedImageSource = selected;
            String recipeName = view.getRecipeNameField().getText().trim().replaceAll("\\s+", "_");
            String extension = selected.getName().substring(selected.getName().lastIndexOf('.'));
            view.getImageNameField().setText(recipeName + "_img" + extension);
        }
    }

    // ===== MAIN LOGIC =====

    /**
     * Save the recipe data to the database.
     * @param event The ActionEvent from the save button.
     */
    public void saveRecipe(javafx.event.ActionEvent event) {
        try {
            RecipeInput input = extractRecipeInput();
            if (input == null) return;

            int recipeId = recipeDAO.getNextAvailableRecipeId();

            if (selectedImageSource != null && !input.imagePath.isEmpty()) {
                saveImageWithOverwriteCheck(input.imagePath);
            }

            recipeDAO.insertRecipe(recipeId, input.name, input.description, input.imagePath, input.prepTime);

            for (IngredientEntry ingredient : input.ingredients) {
                recipeDAO.insertIngredient(recipeId, ingredient.name, ingredient.unit, ingredient.quantity);
            }

            int stepIndex = 1;
            for (String step : input.steps) {
                recipeDAO.insertStep(recipeId, stepIndex++, step);
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Recipe created successfully.");

            closeCurrentWindow(event);
            openMainView();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input or database error.");
        }
    }

    // ===== INPUT EXTRACTION & VALIDATION =====

    private RecipeInput extractRecipeInput() {
        String name = getTrimmedText(view.getRecipeNameField());
        if (name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Recipe name cannot be empty.");
            return null;
        }

        if (recipeDAO.getRecipeByName(name)!= null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Recipe name already exists. Please choose another.");
            return null;
        }

        String imagePath = getTrimmedText(view.getImageNameField());
        String description = getTrimmedText(view.getDescriptionArea());
        String prepTime = parsePreparationTime();
        if (prepTime == null) return null;

        List<IngredientEntry> ingredients = parseIngredients();
        if (ingredients == null) return null;

        List<String> steps = parseSteps();
        if (steps.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "At least one cooking step is required.");
            return null;
        }

        return new RecipeInput(name, description, imagePath, prepTime, ingredients, steps);
    }

    private String parsePreparationTime() {
        String timeStr = getTrimmedText(view.getPreptimeField());
        String unit = view.getPreptimeUnitBox().getValue();

        if (timeStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Preparation time cannot be empty.");
            return null;
        }

        try {
            int time = Integer.parseInt(timeStr);
            if (time < MIN_PREP_TIME || time > MAX_PREP_TIME) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Preparation time must be between " + MIN_PREP_TIME + " and " + MAX_PREP_TIME + ".");
                return null;
            }
            return time + " " + unit;
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Preparation time must be a valid number.");
            return null;
        }
    }

    private List<IngredientEntry> parseIngredients() {
        List<IngredientEntry> list = new ArrayList<>();
        for (Node node : view.getIngredientBox().getChildren()) {
            if (node instanceof HBox row) {
                TextField nameField = (TextField) row.getChildren().get(0);
                TextField quantityField = (TextField) row.getChildren().get(1);
                TextField unitField = (TextField) row.getChildren().get(2);

                String name = getTrimmedText(nameField);
                String quantityStr = getTrimmedText(quantityField);
                String unit = getTrimmedText(unitField);

                // skip empty ingredient rows
                if (name.isEmpty() && quantityStr.isEmpty() && unit.isEmpty()) continue;

                if (name.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Ingredient name cannot be empty.");
                    return null;
                }

                if (quantityStr.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Ingredient quantity cannot be empty.");
                    return null;
                }

                if (unit.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Ingredient unit cannot be empty.");
                    return null;
                }

                int quantity;
                quantity = Integer.parseInt(quantityStr);


                if (quantity < MIN_INGREDIENT_QUANTITY || quantity > MAX_INGREDIENT_QUANTITY) {
                    showAlert(Alert.AlertType.ERROR, "Validation Error", "Ingredient quantity must be between " + MIN_INGREDIENT_QUANTITY + " and " + MAX_INGREDIENT_QUANTITY + ".");
                    return null;
                }

                list.add(new IngredientEntry(name, unit, quantity));
            }
        }
        if (list.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "At least one ingredient is required.");
            return null;
        }
        return list;
    }

    private List<String> parseSteps() {
        List<String> list = new ArrayList<>();
        for (Node node : view.getStepBox().getChildren()) {
            if (node instanceof HBox row) {
                TextArea instructionArea = (TextArea) row.getChildren().get(0);
                String instruction = getTrimmedText(instructionArea);
                if (!instruction.isEmpty()) {
                    list.add(instruction);
                }
            }
        }
        return list;
    }

    // ===== FILE OPERATIONS =====

    private void saveImageWithOverwriteCheck(String imagePath) throws IOException {
        File destFile = new File(IMAGE_RESOURCES_PATH + imagePath);
        if (destFile.exists()) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "You are about to overwrite an existing image. Continue?",
                    ButtonType.OK, ButtonType.CANCEL);
            confirm.setTitle("Confirm Overwrite");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    if (!destFile.delete()) {
                        showAlert(Alert.AlertType.ERROR, "File Error", "Failed to delete existing image.");
                        return;
                    }
                    try {
                        copyFile(selectedImageSource, destFile);
                    } catch (IOException e) {
                        showAlert(Alert.AlertType.ERROR, "File Error", "Failed to overwrite image.");
                    }
                }
            });
        } else {
            copyFile(selectedImageSource, destFile);
        }
    }

    private void copyFile(File source, File dest) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }

    // ===== WINDOW MANAGEMENT =====

    private void closeCurrentWindow(javafx.event.ActionEvent event) {
        if (event != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void openMainView() {
        Stage mainStage = new Stage();
        mainStage.setTitle("Main Menu");
        mainStage.setScene(new Scene(new MainView(), 800, 600));
        mainStage.show();
    	Stage currentRecipeStage = new Stage();
    }

    /**
     * Handle cancel operation with confirmation prompt for unsaved changes.
     * @param event The event triggering the cancel.
     */
    public void cancel(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText("You have unsaved changes.");
        alert.setContentText("Do you want to save your changes before leaving?");

        ButtonType saveBtn = new ButtonType("Save");
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType discardBtn = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(saveBtn, cancelBtn, discardBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == saveBtn) {
                saveRecipe(event);
            } else if (response == discardBtn) {
                closeCurrentWindow(event);
                openMainOrReturnStage();
            }
            // Cancel button just closes the alert and does nothing
        });
    }

    /**
     * Set window close request handler to use cancel logic.
     * @param stage The window stage to bind.
     */
    public void setWindowCloseHandler(Stage stage) {
        stage.setOnCloseRequest(e -> {
            e.consume();  // prevent default close
            cancel(null);
        });
    }

    private void openMainOrReturnStage() {
        if (returnStage != null) {
            returnStage.show();
        } else {
            Stage mainStage = new Stage();
            mainStage.setTitle("Main Menu");
            mainStage.setScene(new Scene(new MainView(), 800, 600));
            mainStage.show();
        }
    }

    public void setReturnStage(Stage stage) {
        this.returnStage = stage;
    }

    // ===== UTILS =====

    private String getTrimmedText(TextInputControl control) {
        String text = control.getText();
        return text == null ? "" : text.trim();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // ===== INTERNAL DATA CLASSES =====

    private static class RecipeInput {
        String name;
        String description;
        String imagePath;
        String prepTime;
        List<IngredientEntry> ingredients;
        List<String> steps;


        RecipeInput(String name, String description, String imagePath, String prepTime,
                    List<IngredientEntry> ingredients, List<String> steps) {
            this.name = name;
            this.description = description;
            this.imagePath = imagePath;
            this.prepTime = prepTime;
            this.ingredients = ingredients;
            this.steps = steps;

        }
    }

    private static class IngredientEntry {
        String name;
        String unit;
        int quantity;

        IngredientEntry(String name, String unit, int quantity) {
            this.name = name;
            this.unit = unit;
            this.quantity = quantity;
        }
    }
}
