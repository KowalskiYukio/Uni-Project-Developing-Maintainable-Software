package main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The Controller class in the MVC pattern.<br>
 * This class is responsible for handling key presses and pausing the game.
 *
 * @author Hilman Hazizi
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * The method that initializes the variables used for the MVC pattern.
     *
     * @param model The Model class in the MVC pattern.
     * @param view The View class in the MVC pattern.
     */
    public void initialize(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * The method used to detect the keyboard input from the user.
     *
     * @param e The parameter used to determine which key was pressed.
     */
    public void keyPress(KeyEvent e) {
        model.snake.keyPressed(e);

        if(e.getCode() == KeyCode.ESCAPE)
            pauseHandler();
    }

    /**
     * The method used to handle the pausing and continuing of the game.
     */
    public void pauseHandler() {
        model.snake.paused = !model.snake.paused;

        if(!model.snake.paused) {
            view.animationTimer.start();
            view.removePause();
        }
        if(model.snake.paused) {
            view.animationTimer.stop();
            view.drawPause();
        }
    }
}
