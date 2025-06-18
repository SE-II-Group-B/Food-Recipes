
module GroupB_Cookbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires java.sql;

    exports main;
    exports view;
    exports model;
    exports controller;
    exports entity;

    opens view to javafx.fxml;
    opens model to javafx.fxml;
    opens controller to javafx.fxml;
    opens entity to javafx.fxml;
}
