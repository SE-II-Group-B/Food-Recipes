package controller;

import entity.Recipe;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.RecipeDAO;
import view.CurrentRecipeView;
import view.MainView;

import java.util.List;
import java.util.Map;

/**
 * Authorized and written by SE II Group B
 *  
 **/

public class HistoryController {
	
	/**
     * Opens the recipe detail view, closes current window, and inserts the recipe to history.
     * @param recipe the selected recipe entity
     * @param source the UI node triggering the event (to get current window)
     */
    public void openRecipeDetail(Recipe recipe, Node source) {

        Stage current = (Stage) source.getScene().getWindow();
        current.close();


        RecipeDAO dao = new RecipeDAO();
        dao.insertHistory(recipe.getRecipeName());


        CurrentRecipeView detailView = new CurrentRecipeView(recipe);
        Stage stage = new Stage();
        stage.setTitle("Recipe Detail");
        stage.setScene(new Scene(detailView, 800, 600));
        stage.show();
    }
    
    /**
     * Returns to the main menu by closing current window and opening main view.
     * @param source the UI node triggering the event (to get current window)
     */
    public void goBackToMain(Node source) {

        Stage current = (Stage) source.getScene().getWindow();
        current.close();


        MainView mainView = new MainView();
        Stage stage = new Stage();
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(mainView, 800, 600));
        stage.show();
    }

    /**
     * Gets a list of recent searched recipes (no duplicates, limited to 10).
     * @return list of recipe names
     */
    public List<String> getRecentSearchedRecipes() {
        RecipeDAO dao = new RecipeDAO();
        return dao.getRecentSearchedRecipes();
    }

    /**
     * Returns a Recipe object by its name.
     *
     * @param name the name of the recipe to search for
     * @return the Recipe object matching the given name, or null if not found
     */
    public Recipe getRecipeByName(String name) {
        RecipeDAO dao = new RecipeDAO();
        return dao.getRecipeByName(name);
    }

    /**
     * Retrieves a list of recently searched recipe entries.
     * @return a list of Map.Entry pairs, where the key is the recipe name
     *         and the value is related information
     */
    public List<Map.Entry<String, String>> getRecentSearchedRecipeEntries() {
        RecipeDAO dao = new RecipeDAO();
        return dao.getRecentSearchedRecipeEntries();
    }
    
    /**
     * Generates a summary report text file for given recipe usage data.
     * @param usageData map of recipe names to usage counts
     */
    public void exportHistoryToTxt(javafx.scene.control.TableView<entity.Recipe> table) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Export History");
        fileChooser.setInitialFileName("history.txt");
        java.io.File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (java.io.PrintWriter writer = new java.io.PrintWriter(file, "UTF-8")) {
                int timeStart = 60;
                String header = String.format("%-" + timeStart + "s%s", "Recipe Name", "Search Time");
                writer.println(header);
                for (entity.Recipe recipe : table.getItems()) {
                    String name = recipe.getRecipeName() != null ? recipe.getRecipeName() : "";
                    String time = recipe.getSearchTime() != null ? recipe.getSearchTime() : "";
                    
                    int pad = Math.max(1, timeStart - name.length());
                    String gapStr = " ".repeat(pad);
                    writer.println(name + gapStr + time);
                }
                System.out.println("Saved to: " + file.getAbsolutePath());
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "Export successful!\n Saved to: " + file.getAbsolutePath(), javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Export failed!", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
            }
        }
    }
    
    /**
     * Clears all history records from database.
     */
    public void clearHistory(javafx.scene.control.TableView<entity.Recipe> table) {
       
        try {
            RecipeDAO dao = new RecipeDAO();
            java.sql.Connection conn = java.sql.DriverManager.getConnection(dao.getURL(), dao.getUser(), dao.getPass());
            try (java.sql.Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM history");
            }
            conn.close();
            
            //refresh the table view
            table.getItems().clear();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION, "History cleared!", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Clear failed!", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
        }
    }
}
