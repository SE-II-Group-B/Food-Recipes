package view;

import controller.CurrentRecipeController;
import entity.Ingredient;
import entity.Recipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.RecipeDAO;

import java.io.File;
import java.util.List;

/**
 * 
 * Authorized and written by SE II Group B
 * 
 * View for displaying detailed information about a single recipe.
 */
public class CurrentRecipeView extends BorderPane {

    private final Spinner<Integer> servingsSpinner = new Spinner<>(1, 99, 1);
    private final VBox ingredientBox = new VBox(5);
    private final VBox stepBox = new VBox(5);
    private final CurrentRecipeController controller;

    public CurrentRecipeView(Recipe recipe) {
        this.controller = new CurrentRecipeController(this, recipe);
        RecipeDAO dao = new RecipeDAO();

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        // Title
        Label titleLabel = new Label(recipe.getRecipeName());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Image (if exists)
        loadImage(recipe, content);

        // Description
        Label descriptionLabel = new Label(recipe.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(400);
        descriptionLabel.setFont(Font.font("Arial", 14));

        // Servings
        HBox servingsRow = new HBox(10, new Label("Servings:"), servingsSpinner);
        servingsRow.setAlignment(Pos.CENTER);

        content.getChildren().addAll(titleLabel, descriptionLabel, servingsRow);

        // Prep Time
        String preptime = recipe.getPreptime();
        if (preptime != null && !preptime.isEmpty()) {
            Label preptimeLabel = new Label("Prep Time: " + preptime);
            preptimeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            content.getChildren().add(preptimeLabel);
        }

        // Ingredients
        Label ingTitle = new Label("Ingredients");
        ingTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        content.getChildren().addAll(ingTitle, ingredientBox);

        List<Ingredient> ingredients = dao.getIngredientsByRecipeId(recipe.getRecipeId());
        updateIngredientList(ingredients, 1);

        servingsSpinner.valueProperty().addListener((obs, oldVal, newVal) ->
                updateIngredientList(ingredients, newVal)
        );

        // Steps
        Label stepTitle = new Label("Steps");
        stepTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        content.getChildren().addAll(stepTitle, stepBox);

        loadSteps(dao.getStepsByRecipeId(recipe.getRecipeId()));

        // Buttons
        Button edit = new Button("Edit");
        edit.setOnAction(e -> controller.editRecipe(recipe, edit));

        Button back = new Button("Back");
        back.setOnAction(e -> controller.goBack(back));

        HBox buttons = new HBox(20, back, edit);
        buttons.setAlignment(Pos.CENTER);
        content.getChildren().add(buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);
    }

    /**
     * Updates the ingredient list according to the servings multiplier.
     */
    private void updateIngredientList(List<Ingredient> ingredients, int servings) {
        ingredientBox.getChildren().clear();
        for (Ingredient ing : ingredients) {
            int adjusted = ing.getQuantity() * servings;
            String text = String.format("%s %d %s", ing.getIngredientName(), adjusted, ing.getUnit());
            Label label = new Label(text);
            ingredientBox.getChildren().add(label);
        }
    }

    /**
     * Loads cooking steps into the view.
     */
    private void loadSteps(List<String> steps) {
        stepBox.getChildren().clear();
        for (int i = 0; i < steps.size(); i++) {
            String step = steps.get(i);
            Label stepLabel = new Label((i + 1) + ". " + step);
            stepLabel.setWrapText(true);
            stepLabel.maxWidthProperty().bind(stepBox.widthProperty().subtract(30));
            stepBox.getChildren().add(stepLabel);
        }
    }

    /**
     * Loads and displays the image if available.
     */
    private void loadImage(Recipe recipe, VBox container) {
        if (recipe.getImgPath() != null && !recipe.getImgPath().isEmpty()) {
            File imageFile = new File("resources/img/" + recipe.getImgPath());
            if (imageFile.exists()) {
                try {
                    Image img = new Image(imageFile.toURI().toString(), false);
                    if (img.isError()) {
                        System.err.println("Image load error: " + img.getException());
                        return;
                    }
                    ImageView imageView = new ImageView(img);
                    imageView.setFitWidth(400);
                    imageView.setPreserveRatio(true);
                    container.getChildren().add(imageView);
                } catch (Exception e) {
                    System.err.println("Failed to load image: " + imageFile.getAbsolutePath());
                    e.printStackTrace();
                }
            } else {
                System.err.println("Image file does not exist: " + imageFile.getAbsolutePath());
            }
        }
    }

    /**
     * Gets the controller associated with this view.
     */
    public CurrentRecipeController getController() {
        return controller;
    }
}
