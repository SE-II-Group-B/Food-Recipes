package view;

import controller.EditRecipeController;
import entity.Ingredient;
import entity.Recipe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

/**
 * EditRecipeView provides the user interface for editing an existing recipe.
 * It allows modification of basic information, image, preparation time,
 * ingredients, and cooking steps.
 */
public class EditRecipeView extends BorderPane {

    private final TextField recipeNameField = new TextField();
    private final TextField imageNameField = new TextField();
    private final TextArea descriptionArea = new TextArea();
    private final VBox ingredientBox = new VBox(5);
    private final VBox stepBox = new VBox(5);
    private final EditRecipeController controller;
    private final Recipe recipe;
    private final TextField preptimeField = new TextField();
    private final ComboBox<String> preptimeUnitBox = new ComboBox<>();

    /**
     * Constructs the EditRecipeView for the given recipe entity.
     * Initializes the layout, binds UI events to controller actions,
     * and populates form fields with recipe data.
     *
     * @param recipe the recipe to be edited
     */
    public EditRecipeView(Recipe recipe) {
        this.recipe = recipe;
        this.controller = new EditRecipeController(this, recipe);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Title
        Label title = new Label("Edit Recipe");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.getChildren().add(title);

        // Basic Information Section
        Label infoLabel = new Label("Basic Information");
        infoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        recipeNameField.setPrefWidth(200);
        imageNameField.setPrefWidth(200);
        descriptionArea.setPrefHeight(60);
        descriptionArea.setPrefWidth(400);

        preptimeField.setPromptText("Time");
        preptimeField.setPrefWidth(80);
        preptimeUnitBox.getItems().addAll("min", "hour");
        preptimeUnitBox.setValue("min");

        HBox preptimeRow = new HBox(10, new Label("Prep Time:"), preptimeField, preptimeUnitBox);
        preptimeRow.setAlignment(Pos.CENTER_LEFT);

        HBox nameRow = new HBox(10, new Label("Recipe Name:"), recipeNameField);
        nameRow.setAlignment(Pos.CENTER_LEFT);

        HBox description = new HBox(10, new Label("Description:"), descriptionArea);

        VBox infoBox = new VBox(10, infoLabel, nameRow, preptimeRow, description);
        infoBox.setPadding(new Insets(10));
        root.getChildren().add(infoBox);

        // Image Selection Section
        Button addPhotoButton = new Button("Add Photo");
        addPhotoButton.setPrefHeight(40);
        addPhotoButton.setPrefWidth(200);
        addPhotoButton.setOnAction(e -> controller.handleImageSelection());

        HBox imageRow = new HBox(10, addPhotoButton, imageNameField);
        imageRow.setAlignment(Pos.CENTER_LEFT);
        VBox imageBox = new VBox(10, imageRow);
        imageBox.setPadding(new Insets(10));
        root.getChildren().add(imageBox);

        // Ingredient Section
        Label ingLabel = new Label("Ingredients");
        ingLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Button addIngredient = new Button("+");
        addIngredient.setOnAction(e -> controller.addIngredientRow());
        VBox ingredientSection = new VBox(10, ingLabel, ingredientBox, addIngredient);
        ingredientSection.setPadding(new Insets(10));
        root.getChildren().add(ingredientSection);

        // Step Section
        Label stepLabel = new Label("Steps");
        stepLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Button addStep = new Button("+");
        addStep.setOnAction(e -> controller.addStepRow());
        VBox stepSection = new VBox(10, stepLabel, stepBox, addStep);
        stepSection.setPadding(new Insets(10));
        root.getChildren().add(stepSection);

        // Bottom Buttons
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> controller.saveEdit(e));
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> controller.cancel(e));
        HBox buttons = new HBox(20, saveButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));
        root.getChildren().add(buttons);

        // Scrollable Container
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);

        // Load recipe data into fields
        controller.fillFields();
    }

    /**
     * @return TextField for editing recipe name.
     */
    public TextField getRecipeNameField() { return recipeNameField; }

    /**
     * @return TextField for editing image file name.
     */
    public TextField getImageNameField() { return imageNameField; }

    /**
     * @return TextArea for editing recipe description.
     */
    public TextArea getDescriptionArea() { return descriptionArea; }

    /**
     * @return VBox container holding ingredient input rows.
     */
    public VBox getIngredientBox() { return ingredientBox; }

    /**
     * @return VBox container holding step input rows.
     */
    public VBox getStepBox() { return stepBox; }

    /**
     * @return The recipe being edited.
     */
    public Recipe getRecipe() { return recipe; }

    /**
     * @return The controller managing this view.
     */
    public EditRecipeController getController() { return controller; }

    /**
     * @return TextField for preparation time value.
     */
    public TextField getPreptimeField() { return preptimeField; }

    /**
     * @return ComboBox for preparation time unit.
     */
    public ComboBox<String> getPreptimeUnitBox() { return preptimeUnitBox; }
}
