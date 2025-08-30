package media;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.paint.Color.TRANSPARENT;

/**
 * The class used to access images for the snake, food, and background.
 *
 * @author Hilman Hazizi - modified
 */
public class ImageUtil {
    /** The map variable used to store the images. */
    public static Map<String, Image> images = new HashMap<>();

    static {
        // snake
        images.put("snake-head", new Image("snake-head-top.png"));
        images.put("snake-body", new Image("snake-body.png"));
        // food
        images.put("0", new Image("food-kiwi.png"));
        images.put("1", new Image("food-lemon.png"));
        images.put("2", new Image("food-litchi.png"));
        images.put("3", new Image("food-mango.png"));
        images.put("4", new Image("food-apple.png"));
        images.put("5", new Image("food-banana.png"));
        images.put("6", new Image("food-blueberry.png"));
        images.put("7", new Image("food-cherry.png"));
        images.put("8", new Image("food-durian.png"));
        images.put("9", new Image("food-grape.png"));
        images.put("10", new Image("food-grapefruit.png"));
        images.put("11", new Image("food-peach.png"));
        images.put("12", new Image("food-pear.png"));
        images.put("13", new Image("food-orange.png"));
        images.put("14", new Image("food-pineapple.png"));
        images.put("15", new Image("food-strawberry.png"));
        images.put("16", new Image("food-watermelon.png"));
        // background
        images.put("game-background", new Image("game-background.png"));
    }

    /**
     * The method used to rotate images.
     *
     * @param image The image to be rotated.
     * @param degree The amount of rotation in degrees.
     * @return The rotated image.
     */
	public static Image rotateImage(Image image, int degree) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(degree);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(TRANSPARENT);

		int width = (int) image.getWidth();
		int height = (int) image.getHeight();

		WritableImage writableImage = new WritableImage(width, height);

		return imageView.snapshot(params, writableImage);
	}
}
