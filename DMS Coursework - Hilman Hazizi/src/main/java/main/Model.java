package main;

import entity.Food;
import entity.Snake;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

/**
 * The Model class in the MVC pattern.<br> This class is responsible for
 * creating the snake and food, and determining whether the snake is
 * still alive or not.
 *
 * @author Hilman Hazizi
 */

public class Model {
    private View view;

    /** The main snake variable that is used in the game. */
    public Snake snake;

    /** The main food variable that used in the game. */
    public Food food;

    /**
     * The method that initializes the variables used for the game.
     *
     * @param view The View class in the MVC pattern.
     * @param startX The x-coordinate for where the snake starts from.
     * @param startY The y-coordinate for where the snake starts from.
     */
    public void initialize(View view, int startX, int startY) {
        this.view = view;
        this.snake = new Snake(startX, startY);
        this.food = new Food();
    }

    /**
     * The method that determines the state of the game.
     *
     * @param g The GraphicsContext object used to draw on the canvas.
     */
    public void stateHandler(GraphicsContext g) {
        if (snake.live) {
            snake.draw(g);
            if (food.live) {
                food.draw(g);
                food.eaten(snake);
            } else {
                food = new Food();
            }
        } else {
            view.animationTimer.stop();
            try {
                view.switchToFail();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
