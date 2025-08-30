package entity;

import media.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * The class for the snake entity.
 *
 * @author Hilman Hazizi
 */
public class Snake extends Entity implements movable {
    // Game changing variables.
    private final int speed;
    private int length;
    private final int bodyDistance; // Distance between body points

    /** The score the player has achieved */
    public int score = 0;

    private final Image IMG_SNAKE_HEAD = ImageUtil.images.get("snake-head");

    /** A linked list of all the body points of the snake */
    public List<Point2D> bodyPoints = new LinkedList<>();

    private Image newImgSnakeHead;
    private boolean up, down, left, right = true;

    /** Boolean that determines whether the snake can move or not. */
    public boolean paused = false;

    /**
     * The constructor for Snake.
     *
     * @param x The x-coordinate from where the snake appears.
     * @param y The y-coordinate from where the snake appears.
     */
    public Snake(int x, int y) {
        this.live = true;
        this.x = x;
        this.y = y;
        this.i = ImageUtil.images.get("snake-body");
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();

        this.speed = 2;
        this.length = 1;

        this.bodyDistance = w / speed;
        newImgSnakeHead = IMG_SNAKE_HEAD;
    }

    /**
     * The method that returns the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return length;
    }

    /**
     * The method that changes the length of the snake.
     *
     * @param length The new length of the snake.
     */
    public void changeLength(int length) {
        this.length = length;
    }

    /**
     * The method that determines what happens when keyboard input is received.
     *
     * @param e The key that is pressed.
     */
    public void keyPressed(KeyEvent e) {
        // Checking key presses
        if(!paused) {
            switch (e.getCode()) {
                case UP:
                    if (!down) {
                        up = true;
                        left = false;
                        right = false;

                        newImgSnakeHead = ImageUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                    }
                    break;

                case DOWN:
                    if (!up) {
                        down = true;
                        left = false;
                        right = false;

                        newImgSnakeHead = ImageUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                    }
                    break;

                case LEFT:
                    if (!right) {
                        up = false;
                        down = false;
                        left = true;

                        newImgSnakeHead = ImageUtil.rotateImage(IMG_SNAKE_HEAD, -180);
                    }
                    break;

                case RIGHT:
                    if (!left) {
                        up = false;
                        down = false;
                        right = true;

                        newImgSnakeHead = IMG_SNAKE_HEAD;
                    }
                    break;

                default:
                    break;
            }
        }
    }


    /**
     * The method that allows the snake to move.
     */
    public void move() {
        if (up) {
            y -= speed;
        } else if (down) {
            y += speed;
        } else if (left) {
            x -= speed;
        } else if (right) {
            x += speed;
        }
    }

    /**
     * {@inheritDoc}
     * Draws the snake.
     *
     * @param g The GraphicsContext object used to draw on the canvas.
     */
    @Override
    public void draw(GraphicsContext g) {
        outOfBounds();
        eatBody();

        bodyPoints.add(new Point2D(x, y));

        if (bodyPoints.size() == (this.length + 1) * bodyDistance) {
            bodyPoints.removeFirst();
            // Comment (or remove) Line 161 if the game gives an error.

            // bodyPoints.remove(0);
            // Uncomment line 164 if the game gives an error.
        }
        g.drawImage(newImgSnakeHead, x, y);
        drawBody(g);

        move();
    }

    /** The method that handles the snake eating itself. */
    public void eatBody() {
        for (Point2D point : bodyPoints) {
            for (Point2D point2 : bodyPoints) {
                if (point.equals(point2) && point != point2 && length > 1) {
                    this.live = false;
                    break;
                }
            }
        }
    }

    /**
     * The method that draws the body.
     *
     * @param g The GraphicsContext object used to draw on the canvas.
     */
    public void drawBody(GraphicsContext g) {
        int snakeLength = bodyPoints.size() - 1 - bodyDistance;

        for (int j = snakeLength; j >= bodyDistance; j -= bodyDistance) {
            Point2D point = bodyPoints.get(j);
            g.drawImage(this.i, point.getX(), point.getY());
        }
    }

    /** The method that handles the snake going out of bounds. */
    private void outOfBounds() {
        boolean xOut = (x <= 0 || x >= (870 - w));
        boolean yOut = (y <= 40 || y >= (560 - h));
        if (xOut || yOut) {
            live = false;
        }
    }
}