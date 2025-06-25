package view;

import model.Model;
import javafx.scene.image.ImageView;
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

        imageCol.setCellFactory(column -> new TableCell<Recipe, String>() {
            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    ImageView imageView = Model.getImageViewFromPath(imagePath);
                    setGraphic(imageView);
                }
            }
        });

        
        TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
        nameCol.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue().getName()));
        nameCol.setPrefWidth(200);
    
        
        table.getColumns().addAll(imageCol, nameCol);
        
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        table.setItems(recipes);
        
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Recipe History");
        stage.show();
    }
}