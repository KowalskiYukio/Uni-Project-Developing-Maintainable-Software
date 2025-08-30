package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * The controller that is used in the FXML files to switch scenes.
 *
 * @author Hilman Hazizi
 */
public class SceneController {
    private Stage stage;

    private MediaPlayer mediaPlayer;

    /**
     * The method that starts up the music played on the menu screens. This
     * method is run whenever each FXML file is displayed on the application.
     */
    public void initialize() {
        String menuMusic = "src/main/resources/music/34-Altar [Story mode BGM].mp3";
        Media sound = new Media(new File(menuMusic).toURI().toString());
        this.mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    /**
     * The method used to start the game.
     *
     * @param event This parameter is used to detect a button being pressed.
     */
    public void startGame(ActionEvent event) {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        model.initialize(view, view.screenWidth / 2, view.screenHeight / 2);
        controller.initialize(model, view);
        view.initialize(model, controller, stage);

        stage.setScene(view.scene);
        stage.show();

        mediaPlayer.stop();
    }

    /**
     * The method used to switch to the Main Menu.
     *
     * @param event This parameter is used to detect a button being pressed.
     * @throws IOException Throws an exception if the given file is not found.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull
                (getClass().getResource("/scenes/MainMenu.fxml")));
        Scene failScene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(failScene);
        stage.show();

        mediaPlayer.stop();
    }

    /**
     * The method used to switch to the Credits Screen.
     *
     * @param event This parameter is used to detect a button being pressed.
     * @throws IOException Throws an exception if the given file is not found.
     */
    public void switchToCredits(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull
                (getClass().getResource("/scenes/CreditsScreen.fxml")));
        Scene failScene = new Scene(root);
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(failScene);
        stage.show();

        mediaPlayer.stop();
    }

    /** The method used to exit to desktop when the button is pressed. */
    public void quitGame() {
        Platform.exit();
    }

}
