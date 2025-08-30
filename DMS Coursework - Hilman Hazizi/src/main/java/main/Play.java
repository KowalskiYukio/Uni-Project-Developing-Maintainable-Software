package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The main class that starts the game.
 *
 * @author Hilman Hazizi - modified
 */
public class Play extends Application {

    /**
     * The main method which launches the game
     *
     * @param args The game will launch with arguments given, if any.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The method that is overridden from the Application class and sets up
     * the window to be launched.
     *
     * @param stage The primary stage for the game, onto which scenes are set.
     * @throws IOException Throws an exception if the given file is not found.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull
                (getClass().getResource("/scenes/MainMenu.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Snake");
        stage.getIcons().add(new Image("snake-logo.png"));
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}
