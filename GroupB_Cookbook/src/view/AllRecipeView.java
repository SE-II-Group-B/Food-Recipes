package view;

import model.Model;
import database.RecipeDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import controller.AllRecipeController;
import entity.Recipe;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllRecipeView {
	private TableView<Recipe> table;
	
	public void show(Stage stage, AllRecipeController controller) {
		table = new TableView<>();
		
		table.setEditable(false);
		
		TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
		nameCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
		nameCol.setPrefWidth(200);
		
		TableColumn<Recipe, String> imageCol = new TableColumn<>("Image");
		imageCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getImagePath()));
		imageCol.setPrefWidth(200);
		
		table.getColumns().addAll(imageCol, nameCol);
		ObservableList<Recipe> recipes = FXCollections.observableArrayList(Model.getAllRecipes());
	    table.setItems(recipes);
	    
	    VBox root = new VBox(table);
	    Scene scene = new Scene(root, 600, 400);
	    stage.setScene(scene);
	    stage.setTitle("Recipe Table");
	    stage.show();
	}
	
	
}
