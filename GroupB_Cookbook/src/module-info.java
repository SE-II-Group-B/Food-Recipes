module GroupB_Cookbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens main to javafx.graphics;
    opens controller to javafx.fxml;
    opens view to javafx.fxml;

    exports main;
    exports controller;
    exports view;
    exports model;
    exports entity;
}
