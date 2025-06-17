package controller;

import javafx.collections.*;
import javafx.stage.Stage;
import model.*;
import view.CreateRecipeView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.List;
import entity.*;

public class CreateRecipeController {

    /**
     * Bind UI events and initialize data for the view
     * @param stage the primary stage
     * @param view the CreateRecipeView instance
     */
    public void bindView(Stage stage, CreateRecipeView view) {
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
        view.getSaveButton().setOnAction(e -> {
            String title = view.getTitleField().getText().trim();
            int servings = view.getServingsSpinner().getValue();
            String instructions = view.getInstructionsArea().getText().trim();
            String imagePath = ""; 

            // Validate recipe name input
            if (title.isEmpty()) {            	
				showWarning(stage,"Recipe name cannot be empty!");
                return;
            }

            // Collect non-empty ingredients from table
            List<String> ingredientList = view.getTable().getItems().stream()
                    .filter(ing -> !ing.getName().isEmpty() && !ing.getAmount().isEmpty())
                    .map(ing -> ing.getName() + ": " + ing.getAmount())
                    .toList();

            // Create a Recipe object and save it
            Recipe recipe = new Recipe(title, imagePath, servings, ingredientList, instructions);
            Model.saveRecipe(recipe);

            // Show info popup on successful save
            showInfoPopup(stage, "Recipe saved!");
            stage.close();
        });
    }

    /**
     * Show a simple information popup window
     * @param owner the owner stage
     * @param message the message to show
     */
    private void showInfoPopup(Stage owner, String message) {
        Stage popup = new Stage();
        popup.setTitle("Info");

        Label label = new Label(message);
        label.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        Button okButton = new Button("OK");
        okButton.setOnAction(ev -> popup.close());

        VBox layout = new VBox(10, label, okButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 200, 100);
        popup.setScene(scene);
        popup.initOwner(owner);
        popup.showAndWait();
    }
    
    /**
	 * Show a warning alert dialog
	 * @param message the warning message to display
	 */
    private void showWarning(Stage stage,String message) {
	    Stage dialog = new Stage();
	    VBox box = new VBox(10);
	    box.setPadding(new Insets(15));
	    box.setAlignment(Pos.CENTER);

	    Label label = new Label(message);
	    Button okButton = new Button("OK");
	    okButton.setOnAction(e -> dialog.close());

	    box.getChildren().addAll(label, okButton);

	    Scene scene = new Scene(box, 300, 100);
	    dialog.setScene(scene);
	    dialog.setTitle("Warning");
	    dialog.initOwner(stage);  
	    dialog.showAndWait();
	}
}
