package controller;

import entity.Ingredient;
import entity.Recipe;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.RecipeDAO;
import view.EditRecipeView;
import view.CurrentRecipeView;
import view.MainView;
import javafx.scene.Node;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Authorized and written by SE II Group B
 *  
 **/

public class EditRecipeController {
    private final EditRecipeView view;
    private final RecipeDAO recipeDAO = new RecipeDAO();
    private final Recipe recipe;

    public EditRecipeController(EditRecipeView view, Recipe recipe) {
        this.view = view;
        this.recipe = recipe;
    }

    // fill all fields with recipe data
    public void fillFields() {
        view.getRecipeNameField().setText(recipe.getRecipeName());
        view.getImageNameField().setText(recipe.getImgPath());
        view.getDescriptionArea().setText(recipe.getDescription());
        view.getPreptimeField().setText(recipe.getPreptime().replaceAll("\\D+", ""));

        // ingredients
        List<Ingredient> ingredients = recipeDAO.getIngredientsByRecipeId(recipe.getRecipeId());
        for (int i = 0; i < ingredients.size(); i++) {
            if (i >= view.getIngredientBox().getChildren().size()) addIngredientRow();
            Ingredient ing = ingredients.get(i);
            HBox row = (HBox) view.getIngredientBox().getChildren().get(i);
            ((TextField) row.getChildren().get(0)).setText(ing.getIngredientName());
            ((TextField) row.getChildren().get(1)).setText(String.valueOf(ing.getQuantity()));
            ((TextField) row.getChildren().get(2)).setText(ing.getUnit());
        }

        // steps
        List<String> steps = recipeDAO.getStepsByRecipeId(recipe.getRecipeId());
        for (int i = 0; i < steps.size(); i++) {
            if (i >= view.getStepBox().getChildren().size()) addStepRow();
            HBox row = (HBox) view.getStepBox().getChildren().get(i);
            ((TextArea) row.getChildren().get(0)).setText(steps.get(i));
        }
    }

    public void addIngredientRow() {
        HBox row = new HBox(10);
        TextField name = new TextField();
        TextField quantity = new TextField();
        TextField unit = new TextField();
        Button remove = new Button("-");
        name.setPromptText("Name");
        quantity.setPromptText("Quantity");
        unit.setPromptText("Unit");
        quantity.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                quantity.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
        remove.setOnAction(e -> view.getIngredientBox().getChildren().remove(row));
        row.getChildren().addAll(name, quantity, unit, remove);
        view.getIngredientBox().getChildren().add(row);
    }

    public void addStepRow() {
        HBox row = new HBox(10);
        TextArea instruction = new TextArea();
        Button remove = new Button("-");
        instruction.setPromptText("Instruction");
        instruction.setPrefRowCount(3);
        instruction.setWrapText(true);
        instruction.setPrefWidth(500);
        remove.setOnAction(e -> view.getStepBox().getChildren().remove(row));
        row.getChildren().addAll(instruction, remove);
        view.getStepBox().getChildren().add(row);
    }

    public void saveEdit(javafx.event.ActionEvent event) {
        try {
            String name = view.getRecipeNameField().getText();
            if (name == null || name.trim().isEmpty()) {
                showAlert("Recipe name cannot be empty.");
                return;
            }
            name = name.trim();
            String imagePath = view.getImageNameField().getText();
            if (imagePath == null) imagePath = "";
            imagePath = imagePath.trim();
            String description = view.getDescriptionArea().getText();
            if (description == null) description = "";
            description = description.trim();
            int recipeId = recipe.getRecipeId();

            // Preparation time
            String preptimeNum = view.getPreptimeField().getText().trim();
            String preptimeUnit = view.getPreptimeUnitBox().getValue();
            String preptime;
            

            if (!preptimeNum.isEmpty()) {
                try {
                    int prepVal = Integer.parseInt(preptimeNum);
                    if (prepVal <= 0) {
                        showAlert("Preparation time cannot be zero or negative.");
                        return;
                    }else if (prepVal > 9999) {
						showAlert("Preparation time is too large.");
						return;
					}
                    preptime = prepVal + " " + preptimeUnit;
                } catch (NumberFormatException e) {
                    showAlert("Preparation time must be a number.");
                    return;
                }
            } else {
            	showAlert("Preparation time cannot be empty.");
				return;
			}
            
            int ingredientCount = 0;
            
            for (javafx.scene.Node node : view.getIngredientBox().getChildren()) {
            	
                if (node instanceof HBox row) {
                    TextField ingName = (TextField) row.getChildren().get(0);
                    TextField qty = (TextField) row.getChildren().get(1);
                    TextField unit = (TextField) row.getChildren().get(2);
                    String ing = ingName.getText().trim();
                    String unitStr = unit.getText().trim();
                    String qtyStr = qty.getText().trim();
                    if (ing.isEmpty() && qtyStr.isEmpty() && unitStr.isEmpty()) {
						continue; 
                    }else {
                    	ingredientCount++; 
                    }
                    
                    if (ing.isEmpty()) {
                        showAlert("Ingredient name cannot be empty.");
                        return;
                    }
                    if (qtyStr.isEmpty()) {
                        showAlert("Ingredient quantity cannot be empty.");
                        return;
                    }
                    int qtyVal = 0;
                    qtyVal = Integer.parseInt(qtyStr);
                    if (qtyVal <= 0 || qtyVal > 999999) {
                        showAlert("Ingredient quantity must be between 0 and 999999.");
                        return;
                    }
                    if (unitStr.isEmpty()) {
                        showAlert("Ingredient unit cannot be empty.");
                        return;
                    }
                }
            }
            
            //System.out.println("Ingredient count: " + ingredientCount);
            if (ingredientCount == 0) {
				showAlert("At least one ingredient is required.");
				return;
			}
            // At least one step is required
            int stepCount = 0;
            for (javafx.scene.Node node : view.getStepBox().getChildren()) {
                if (node instanceof HBox row) {
                    TextArea instruction = (TextArea) row.getChildren().get(0);
                    String text = instruction.getText().trim();
                    if (!text.isEmpty()) {
                        stepCount++;
                    }
                }
            }
            if (stepCount == 0) {
                showAlert("At least one step is required.");
                return;
            }

            // update recipe details
            Recipe updatedRecipe = new Recipe();
            updatedRecipe.setRecipeId(recipeId);
            updatedRecipe.setRecipeName(name);
            updatedRecipe.setDescription(description);
            updatedRecipe.setImgPath(imagePath);
            updatedRecipe.setPreptime(preptime);
            recipeDAO.updateRecipe(updatedRecipe);

            // delete existing ingredients and steps
            String deleteIngredient = "DELETE FROM ingredient WHERE recipe_id = ?";
            String deleteStep = "DELETE FROM step WHERE recipe_id = ?";
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection(recipeDAO.getURL(), recipeDAO.getUser(), recipeDAO.getPass())) {
                try (java.sql.PreparedStatement psIng = conn.prepareStatement(deleteIngredient)) {
                    psIng.setInt(1, recipeId);
                    psIng.executeUpdate();
                }
                try (java.sql.PreparedStatement psStep = conn.prepareStatement(deleteStep)) {
                    psStep.setInt(1, recipeId);
                    psStep.executeUpdate();
                }
            }

            for (javafx.scene.Node node : view.getIngredientBox().getChildren()) {
                if (node instanceof HBox row) {
                    TextField ingName = (TextField) row.getChildren().get(0);
                    TextField qty = (TextField) row.getChildren().get(1);
                    TextField unit = (TextField) row.getChildren().get(2);
                    String ing = ingName.getText();
                    if (ing == null) ing = "";
                    ing = ing.trim();
                    String unitStr = unit.getText();
                    if (unitStr == null) unitStr = "";
                    unitStr = unitStr.trim();
                    String qtyStr = qty.getText();
                    if (qtyStr == null) qtyStr = "";
                    int quantity = qtyStr.isEmpty() ? 0 : Integer.parseInt(qtyStr.trim());
                    if (!ing.isEmpty() && !unitStr.isEmpty() && quantity > 0) {
                        recipeDAO.insertIngredient(recipeId, ing, unitStr, quantity);
                    }
                }
            }
            int step_id = 0;
            for (javafx.scene.Node node : view.getStepBox().getChildren()) {
                if (node instanceof HBox row) {
                    TextArea instruction = (TextArea) row.getChildren().get(0);
                    String text = instruction.getText();
                    if (text == null) text = "";
                    text = text.trim();
                    step_id++;
                    if (!text.isEmpty()) {
                        recipeDAO.insertStep(recipeId, step_id, text);
                    }
                }
            }

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success");
            success.setHeaderText(null);
            success.setContentText("Successfully edited.");
            success.showAndWait();

            Stage currentStage = null;
            if (event != null) {
                currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (view != null && view.getScene() != null) {
                currentStage = (Stage) view.getScene().getWindow();
            }
            if (currentStage != null) currentStage.close();

            Recipe updated = recipeDAO.getRecipeByName(name);
            Stage newStage = new Stage();
            newStage.setTitle("Recipe Detail");
            newStage.setScene(new Scene(new CurrentRecipeView(updated), 800, 600));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Invalid input or database error.");
        }
    }

    public void handleImageSelection() {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Select Recipe Image");
        fileChooser.getExtensionFilters().add(
                new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        java.io.File selected = fileChooser.showOpenDialog(null);
        if (selected != null) {
            String recipeName = view.getRecipeNameField().getText().trim().replaceAll("\\s+", "_");
            String extension = selected.getName().substring(selected.getName().lastIndexOf("."));
            String suggestedName = recipeName + "_img" + extension;
            view.getImageNameField().setText(suggestedName);

            // 复制文件到 resources/img 文件夹
            File destDir = new File("resources/img");
            if (!destDir.exists()) destDir.mkdirs();

            File destFile = new File(destDir, suggestedName);
            try {
                Files.copy(selected.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied to: " + destFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Failed to copy image file.");
            }
        }
    }

    public void cancel(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText("You have unsaved changes.");
        alert.setContentText("Do you want to save your changes before leaving?");
        
        ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType continueBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType discardBtn = new ButtonType("Don't save", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(saveBtn, continueBtn, discardBtn);

        alert.showAndWait().ifPresent(response -> {
            if (response == saveBtn) {
                saveEdit(event);
            } else if (response == continueBtn) {
                // do nothing, continue editing
            } else if (response == discardBtn) {
                // close current window
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

                // open recipe detail view
                Stage detailStage = new Stage();
                Recipe original = recipeDAO.getRecipeById(recipe.getRecipeId());
                detailStage.setScene(new Scene(new view.CurrentRecipeView(original), 800, 600));
                detailStage.setTitle("Recipe Detail");
                detailStage.show();
            }
        });
    }


    public void setWindowCloseHandler(Stage stage) {
        stage.setOnCloseRequest(e -> {
            e.consume(); // prevent default close
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText("You have unsaved changes.");
            alert.setContentText("Do you want to save your changes before leaving?");
            ButtonType saveBtn = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            ButtonType continueBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType discardBtn = new ButtonType("Don't save", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(saveBtn, continueBtn, discardBtn);
            alert.showAndWait().ifPresent(response -> {
                if (response == saveBtn) {
                    saveEdit(null);
                } else if (response == continueBtn) {
                    // continue editing, do nothing
                } else if (response == discardBtn) {
                	stage.close();
                    Stage detailStage = new Stage();
                    Recipe original = recipeDAO.getRecipeById(recipe.getRecipeId()); 
                    detailStage.setScene(new Scene(new view.CurrentRecipeView(original), 800, 600));
                    detailStage.setTitle("Recipe Detail");
                    detailStage.show();
                }
            });
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
