package view;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import entity.Ingredient;
import controller.CreateRecipeController;

public class CreateRecipeView {

    private TableView<Ingredient> table;
    private TextField titleField;
    private TextArea descriptionBox;
    private Button saveButton;
    private VBox ingredientListBox;
    private Button addIngredientBtn;
    private VBox stepsListBox;
    private Button addStepBtn;



    /**
     * Show the Create Recipe window and setup the UI
     * @param stage the main stage
     * @param controller controller that handles logic and event binding
     */
    public void show(Stage stage, CreateRecipeController controller) {
        // Recipe title label and text field
        Label titleLabel = new Label("Recipe name:");
        titleField = new TextField();
        titleField.setPromptText("Enter recipe name here");
        titleField.setMaxWidth(300);
        HBox titleBox = new HBox(10, titleLabel, titleField);
        titleBox.setAlignment(Pos.CENTER);
        
        // Recipe description label and text area
        Label descriptionLabel = new Label("Description:");
        descriptionBox = new TextArea();
        descriptionBox.setPromptText("Enter recipe description here");
        descriptionBox.setWrapText(true);
        descriptionBox.setMaxWidth(300);
        descriptionBox.setMaxHeight(100);

        // Ingredients label
        Label ingredientsLabel = new Label("Ingredients:");
        ingredientListBox = new VBox(5);
        ingredientListBox.setPadding(new Insets(5));
        ingredientListBox.setAlignment(Pos.TOP_CENTER);

        addIngredientRow();

        // 添加按钮 "+" 添加新行
        addIngredientBtn = new Button("+");
        addIngredientBtn.setOnAction(e -> addIngredientRow());
        HBox addBtnBox = new HBox(addIngredientBtn);
        addBtnBox.setAlignment(Pos.CENTER);
        addBtnBox.setPadding(new Insets(5));

        // 整个配料部分容器
        VBox ingredientSection = new VBox(5, ingredientsLabel, ingredientListBox, addBtnBox);
        ingredientSection.setAlignment(Pos.CENTER);
        ingredientSection.setPadding(new Insets(5));

        Label instructionsLabel = new Label("Steps:");
        stepsListBox = new VBox(5);
        stepsListBox.setPadding(new Insets(5));
        stepsListBox.setAlignment(Pos.TOP_CENTER);

        addStepRow();
        
        addStepBtn = new Button("+");
        addStepBtn.setOnAction(e -> addStepRow());

        HBox addStepBtnBox = new HBox(addStepBtn);
        addStepBtnBox.setAlignment(Pos.CENTER);
        addStepBtnBox.setPadding(new Insets(5));

        VBox stepsSection = new VBox(5, instructionsLabel, stepsListBox, addStepBtnBox);
        stepsSection.setAlignment(Pos.CENTER);
        stepsSection.setPadding(new Insets(10));

        // Save recipe button
        saveButton = new Button("Save Recipe");

        VBox root = new VBox(10,
                titleBox,
                descriptionBox,
                ingredientSection,
                stepsSection,
                saveButton
        );


        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Create Recipe");
        stage.show();

        controller.bindView(stage, this);
    }
    
    public void addIngredientRow() {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setPrefWidth(120);

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");
        amountField.setPrefWidth(80);

        TextField unitField = new TextField();
        unitField.setPromptText("Unit");
        unitField.setPrefWidth(80);

        Button deleteBtn = new Button("-");
        deleteBtn.setOnAction(e -> ingredientListBox.getChildren().remove(deleteBtn.getParent()));

        HBox row = new HBox(10, nameField, amountField, unitField, deleteBtn);
        row.setAlignment(Pos.CENTER);
        ingredientListBox.getChildren().add(row);
    }
    
    public void addStepRow() {
        TextField stepField = new TextField();
        stepField.setPromptText("Describe this step");
        stepField.setPrefWidth(280);

        Button deleteBtn = new Button("-");
        deleteBtn.setOnAction(e -> stepsListBox.getChildren().remove(deleteBtn.getParent()));

        HBox stepRow = new HBox(10, stepField, deleteBtn);
        stepRow.setAlignment(Pos.CENTER_LEFT);
        stepsListBox.getChildren().add(stepRow);
    }


    public TableView<Ingredient> getTable() {
		return table;
	}
    
    public TextField getTitleField() {
		return titleField;
	}

	public TextArea getDescriptionBox() {
		return descriptionBox;
	}

	public Button getSaveButton() {
		return saveButton;
	}
	
	public VBox getIngredientListBox() {
	    return ingredientListBox;
	}

	public VBox getStepsListBox() {
	    return stepsListBox;
	}

	public Button getAddStepBtn() {
	    return addStepBtn;
	}
	
	public Button getAddIngredientBtn() {
	    return addIngredientBtn;
	}
	
}
