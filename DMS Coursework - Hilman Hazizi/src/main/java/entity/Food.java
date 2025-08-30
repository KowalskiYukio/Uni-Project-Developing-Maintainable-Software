package entity;

import media.ImageUtil;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

/**
 * The class for the food entity.
 *
 * @author Hilman Hazizi - modified
 */
public class Food extends Entity {

    /** The constructor for Food. */
    public Food() {
        this.live = true;

        this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(17)));

        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();

        this.x = (int) (Math.random() * (870 - w));
        this.y = (int) (Math.random() * (560 - h - 40) + 40);
    }

    /**
     * The method that handles the food being eaten.
     *
     * @param snake The snake object that would eat the food.
     */
    public void eaten(Snake snake) {
        if (snake.getRectangle().intersects(this.getRectangle()) && live && snake.live) {
            this.live = false;
            snake.changeLength(snake.getLength() + 1);
            snake.score += 521;
        }
    }

    /**
     * {@inheritDoc}
     * Draws the food.
     *
     * @param g The GraphicsContext object used to draw on the canvas.
     */
    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(i, x, y);
    }
}
