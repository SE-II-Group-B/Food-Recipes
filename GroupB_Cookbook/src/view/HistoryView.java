package view;

import controller.HistoryController;
import entity.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * Authorized and written by SE II Group B
 * 
 * HistoryView displays the user's recent recipe search history in a table.
 * It allows users to view details, export the list, clear the history,
 * or return to the main view.
 */
public class HistoryView extends BorderPane {

    private final TableView<Recipe> historyTable = new TableView<>();
    private final Button backButton = new Button("Back");
    private final Button exportButton = new Button("Export");
    private final Button clearButton = new Button("Clear");
    private final HistoryController controller = new HistoryController();

    /**
     * Constructs the HistoryView.
     * Sets up the table with recipe name and search time columns,
     * binds row events and button actions,
     * and loads recent search history data.
     */
    public HistoryView() {
        // Table columns: Recipe Name & Search Time
        TableColumn<Recipe, String> nameCol = new TableColumn<>("Recipe Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("recipeName"));

        TableColumn<Recipe, String> timeCol = new TableColumn<>("Search Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("searchTime"));

        historyTable.getColumns().addAll(nameCol, timeCol);
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        historyTable.setItems(loadHistoryData());

        // Handle row click to show detail
        historyTable.setRowFactory(tv -> {
            TableRow<Recipe> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (!row.isEmpty()) {
                    Recipe selected = row.getItem();
                    controller.openRecipeDetail(selected, row);
                }
            });
            return row;
        });

        // Button actions
        backButton.setOnAction(e -> controller.goBackToMain(backButton));
        exportButton.setOnAction(e -> controller.exportHistoryToTxt(historyTable));
        clearButton.setOnAction(e -> controller.clearHistory(historyTable));

        // Layout
        VBox content = new VBox(20, historyTable, new HBox(20, exportButton, clearButton, backButton));
        content.setPadding(new Insets(20));
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);

        // Bind close event to back action
        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            newScene.windowProperty().addListener((obsWin, oldWin, newWin) -> {
                if (newWin instanceof Stage) {
                    Stage stage = (Stage) newWin;
                    stage.setOnCloseRequest(e -> controller.goBackToMain(historyTable));
                }
            });
        });
    }

    /**
     * Loads up to 10 recent unique searched recipes,
     * with their names and search timestamps.
     * This method assumes `Recipe` class has a `searchTime` field.
     *
     * @return observable list of recipe history items
     */
    private ObservableList<Recipe> loadHistoryData() {
        ObservableList<Recipe> list = FXCollections.observableArrayList();
        for (var entry : controller.getRecentSearchedRecipeEntries()) {
            String name = entry.getKey();
            String time = entry.getValue();
            Recipe recipe = controller.getRecipeByName(name);
            if (recipe != null) {
                recipe.setSearchTime(time); // Recipe must support setSearchTime()
                list.add(recipe);
            }
        }
        return list;
    }
}
