package entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The abstract class for entities in the game.
 *
 * @author Hilman Hazizi
 */

public abstract class Entity {
    /** The x-coordinate of the entity. */
    public int x;
    /** The y-coordinate of the entity. */
    public int y;
    /** The image of the entity. */
    public Image i;
    /** The width of the entity. */
    public int w;
    /** The height of the entity. */
    public int h;
    /** Variable that determines whether the entity is alive or not. */
    public boolean live;

    /**
     * The method used to draw the entity.
     *
     * @param g The GraphicsContext object used to draw on the canvas.
     */
    public abstract void draw(GraphicsContext g);

    /**
     * The method that returns the rectangle of the entity.
     *
     * @return The rectangle of the entity.
     */
    public Rectangle2D getRectangle() {
        return new Rectangle2D(x, y, w, h);
    }
}
