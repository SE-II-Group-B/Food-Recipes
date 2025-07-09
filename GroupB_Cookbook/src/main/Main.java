package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainView; 

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView root = new MainView(); 
        primaryStage.setTitle("Cookbook");
        primaryStage.setScene(new Scene(root, 800, 600)); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
