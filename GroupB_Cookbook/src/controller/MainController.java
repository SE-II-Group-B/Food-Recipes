package controller;

import entity.Recipe;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AllRecipeView;
import view.CreateRecipeView;
import view.EditRecipeView;
import view.HistoryView;
import view.SearchView;

/**
 * Controller responsible for handling main menu navigation actions.
 */
public class MainController {

    /**
     * Opens the "All Recipes" view and hides the main stage.
     *
     * @param triggerNode the node that triggered this action
     */
    public void openAllRecipe(final Node triggerNode) {
        Stage mainStage = (Stage) triggerNode.getScene().getWindow();
        mainStage.hide();

        Stage newStage = new Stage();
        AllRecipeView view = new AllRecipeView();
        newStage.setScene(new Scene(view, 800, 600));
        newStage.setTitle("All Recipes");
        newStage.setOnCloseRequest(e -> mainStage.show());
        newStage.show();
    }

    /**
     * Opens the "Create Recipe" view and hides the main stage.
     *
     * @param triggerNode the node that triggered this action
     */
    public void openCreateRecipe(final Node triggerNode) {
        Stage mainStage = (Stage) triggerNode.getScene().getWindow();
        mainStage.hide();

        Stage createStage = new Stage();
        CreateRecipeView view = new CreateRecipeView();
        CreateRecipeController controller = new CreateRecipeController(view);
        controller.setReturnStage(mainStage);
        controller.setWindowCloseHandler(createStage);

        createStage.setScene(new Scene(view, 800, 600));
        createStage.setTitle("Create Recipe");
        createStage.show();
    }

    /**
     * Opens the "History" view and hides the main stage.
     *
     * @param triggerNode the node that triggered this action
     */
    public void openHistory(final Node triggerNode) {
        Stage mainStage = (Stage) triggerNode.getScene().getWindow();
        mainStage.hide();

        Stage newStage = new Stage();
        HistoryView view = new HistoryView();
        newStage.setScene(new Scene(view, 800, 600));
        newStage.setTitle("History");
        newStage.setOnCloseRequest(e -> mainStage.show());
        newStage.show();
    }

    /**
     * Opens the "Search Results" view for a given keyword.
     *
     * @param triggerNode the node that triggered this action
     * @param keyword     the search keyword
     */
    public void openSearchView(final Node triggerNode, final String keyword) {
        Stage mainStage = (Stage) triggerNode.getScene().getWindow();
        mainStage.hide();

        Stage newStage = new Stage();
        SearchView view = new SearchView(keyword);
        newStage.setScene(new Scene(view, 800, 600));
        newStage.setTitle("Search Results");
        newStage.setOnCloseRequest(e -> mainStage.show());
        newStage.show();
    }

    /**
     * Opens the "Edit Recipe" view for the given recipe.
     *
     * @param triggerNode the node that triggered this action
     * @param recipe      the recipe to edit
     */
    public void openEditRecipe(final Node triggerNode, final Recipe recipe) {
        Stage mainStage = (Stage) triggerNode.getScene().getWindow();
        mainStage.hide();

        Stage newStage = new Stage();
        EditRecipeView view = new EditRecipeView(recipe);
        newStage.setScene(new Scene(view, 800, 600));
        newStage.setTitle("Edit Recipe");
        newStage.setOnCloseRequest(e -> mainStage.show());
        newStage.show();
    }
}
