package view;

import model.Model;
import controller.CreateRecipeController;
import entity.Recipe;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AllRecipeView<T extends Recipe> {
	private TableView<T> table;
	
	public void show(Stage stage, CreateRecipeController controller) {
		table = new TableView<>();
		
		table.setEditable(false);
		
		TableColumn<T, String> nameCol = new TableColumn<>("Recipe Name");
		nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		TableColumn<T, String> imageCol = new TableColumn<>("Image");
		imageCol.setCellValueFactory(cellData -> cellData.getValue().imagePathProperty());
		TableColumn<T, Integer> servingsCol = new TableColumn<>("Servings");
		servingsCol.setCellValueFactory(cellData -> cellData.getValue().servingsProperty().asObject());
		
		table.getColumns().addAll(nameCol, imageCol, servingsCol);
		
	}
	
	
}
