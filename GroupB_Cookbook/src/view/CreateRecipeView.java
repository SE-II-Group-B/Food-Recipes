package view;

import controller.CreateRecipeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

/**
 * CreateRecipeView is the UI layout for creating a new recipe.
 * It includes fields for recipe name, image, description, preparation time,
 * ingredients, and steps, with options to add new items dynamically.
 */
public class CreateRecipeView extends BorderPane {

    private final TextField recipeNameField = new TextField();
    private final TextField imageNameField = new TextField();
    private final TextArea descriptionArea = new TextArea();
    private final VBox ingredientBox = new VBox(5);
    private final VBox stepBox = new VBox(5);
    private final VBox root = new VBox(15);
    private final CreateRecipeController controller;
    private final TextField preptimeField = new TextField();
    private final ComboBox<String> preptimeUnitBox = new ComboBox<>();

    /**
     * Constructs the CreateRecipeView UI and binds it to its controller.
     * Initializes all form fields, layout sections, and default ingredient/step rows.
     */
    public CreateRecipeView() {
        controller = new CreateRecipeController(this);
        setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);

        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Title
        Label title = new Label("Create Recipe");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(title);

        // Basic Information
        Label infoLabel = new Label("Basic Information");
        infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        recipeNameField.setPromptText("Enter recipe name here...");
        recipeNameField.setPrefWidth(200);
        imageNameField.setPromptText("Enter image file name here...");
        imageNameField.setPrefWidth(200);
        descriptionArea.setPromptText("Enter recipe description here...");
        descriptionArea.setWrapText(true);
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

        Label descriptionLabel = new Label("Description:");
        HBox description = new HBox(10, descriptionLabel, descriptionArea);

        VBox infoBox = new VBox(10, infoLabel, nameRow, preptimeRow, description);
        infoBox.setPadding(new Insets(10));

        // Image Selection
        Button addPhotoButton = new Button("Add Photo");
        addPhotoButton.setPrefHeight(40);
        addPhotoButton.setPrefWidth(200);
        addPhotoButton.setOnAction(e -> controller.handleImageSelection());

        HBox imageRow = new HBox(10, addPhotoButton, imageNameField);
        imageRow.setAlignment(Pos.CENTER_LEFT);
        VBox imageBox = new VBox(10, imageRow);
        imageBox.setPadding(new Insets(10));

        // Ingredients Section
        Label ingLabel = new Label("Ingredients");
        ingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button addIngredient = new Button("+");
        addIngredient.setOnAction(e -> controller.addIngredientRow());

        VBox ingredientSection = new VBox(10, ingLabel, ingredientBox, addIngredient);
        ingredientSection.setPadding(new Insets(10));

        // Steps Section
        Label stepLabel = new Label("Steps");
        stepLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button addStep = new Button("+");
        addStep.setOnAction(e -> controller.addStepRow());

        VBox stepSection = new VBox(10, stepLabel, stepBox, addStep);
        stepSection.setPadding(new Insets(10));

        // Bottom Buttons
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> controller.saveRecipe(e));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> controller.cancel(e));

        HBox buttons = new HBox(20, saveButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        // Assemble all sections into root container
        root.getChildren().addAll(infoBox, imageBox, ingredientSection, stepSection, buttons);

        // Add initial empty fields
        controller.addInitialIngredients(3);
        controller.addInitialSteps(2);
    }

    /**
     * @return TextField for entering the recipe name.
     */
    public TextField getRecipeNameField() {
        return recipeNameField;
    }

    /**
     * @return TextField for entering the image file name.
     */
    public TextField getImageNameField() {
        return imageNameField;
    }

    /**
     * @return VBox container for dynamically added ingredient rows.
     */
    public VBox getIngredientBox() {
        return ingredientBox;
    }

    /**
     * @return VBox container for dynamically added step rows.
     */
    public VBox getStepBox() {
        return stepBox;
    }

    /**
     * @return TextArea for recipe description.
     */
    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    /**
     * @return TextField for entering preparation time value.
     */
    public TextField getPreptimeField() {
        return preptimeField;
    }

    /**
     * @return ComboBox for selecting preparation time unit (e.g., min/hour).
     */
    public ComboBox<String> getPreptimeUnitBox() {
        return preptimeUnitBox;
    }
}
