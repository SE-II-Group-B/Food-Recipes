package view;

import model.Model;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import controller.HistoryController;
import entity.Recipe;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HistoryView {
    private TableView<Recipe> table;
    
    public void show(Stage stage, HistoryController controller) {
        table = new TableView<>();
        table.setEditable(false);
        
        TableColumn<Recipe, String> imageCol = new TableColumn<>("Image");
        imageCol.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getImagePath()));
        imageCol.setPrefWidth(200);
        
        TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
        nameCol.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getName()));
        nameCol.setPrefWidth(200);
        
        TableColumn<Recipe, Integer> servingsCol = new TableColumn<>("Servings");
        servingsCol.setCellValueFactory(cellData -> 
            new ReadOnlyObjectWrapper<>(cellData.getValue().getServings()));
        servingsCol.setPrefWidth(100);
        
        table.getColumns().addAll(imageCol, nameCol, servingsCol);
        
        // get data (tbd)
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        table.setItems(recipes);
        
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Recipe History");
        stage.show();
    }
}