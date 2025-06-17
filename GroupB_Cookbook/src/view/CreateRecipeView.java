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
    private Spinner<Integer> servingsSpinner;
    private TextArea instructionsArea;
    private Button addRowBtn;
    private Button delRowBtn;
    private Button saveButton;

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

        // TableView setup for ingredients list
        table = new TableView<>();
        table.setEditable(true);
        table.setPrefHeight(200);
        table.setMaxHeight(300);
        table.setMaxWidth(300);
        table.setRowFactory(tv -> new TableRow<Ingredient>() {
            @Override
            protected void updateItem(Ingredient item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("-fx-background-color: white;"); 
                }
                else if (!item.getName().isEmpty() && !item.getAmount().isEmpty()){
                    setStyle("-fx-background-color: #e6f0ff;"); 
                }
            }
        });



        // Ingredient name column
        TableColumn<Ingredient, String> nameCol = new TableColumn<>("Ingredient");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setPrefWidth(180);

        // Ingredient amount column
        TableColumn<Ingredient, String> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        amountCol.setPrefWidth(120);

        table.getColumns().addAll(nameCol, amountCol);

        // Buttons for adding and deleting rows
        addRowBtn = new Button("Add Row");
        delRowBtn = new Button("Delete Selected");
        HBox btnBox = new HBox(10, addRowBtn, delRowBtn);
        btnBox.setPadding(new Insets(10));
        btnBox.setAlignment(Pos.CENTER);

        // Servings label and spinner
        Label servingsLabel = new Label("Servings:");
        servingsSpinner = new Spinner<>(1, 20, 1);
        servingsSpinner.setEditable(true);
        HBox servingsBox = new HBox(10, servingsLabel, servingsSpinner);
        servingsBox.setAlignment(Pos.CENTER);

        // Instructions label and text area
        Label instructionsLabel = new Label("Instructions:");
        instructionsArea = new TextArea();
        instructionsArea.setPromptText("Describe the cooking steps here");
        instructionsArea.setPrefRowCount(6);

        // Save recipe button
        saveButton = new Button("Save Recipe");

        // Main layout container
        VBox root = new VBox(10,
                titleBox,
                table,
                btnBox,
                servingsBox,
                instructionsLabel,
                instructionsArea,
                saveButton
        );
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Create Recipe");
        stage.show();

        controller.bindView(stage, this);
    }

    public TableView<Ingredient> getTable() {
		return table;
	}
    
    public TextField getTitleField() {
		return titleField;
	}

	public Spinner<Integer> getServingsSpinner() {
		return servingsSpinner;
	}

	public TextArea getInstructionsArea() {
		return instructionsArea;
	}

	public Button getAddRowBtn() {
		return addRowBtn;
	}

	public Button getDelRowBtn() {
		return delRowBtn;
	}

	public Button getSaveButton() {
		return saveButton;
	}
	
}
