module com.lifegame.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    opens com.lifegame.app to javafx.fxml;
    exports com.lifegame.app;
    exports com.lifegame.app.controller;
    opens com.lifegame.app.controller to javafx.fxml;
}