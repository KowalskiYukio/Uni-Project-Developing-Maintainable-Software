/**
 * Defines the modules dependencies.
 */
module com.snake.dms_courseworkhilman_hazizi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports entity;
    opens entity to javafx.fxml;
    exports main;
    opens main to javafx.fxml;
    exports media;
    opens media to javafx.fxml;
}