package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import media.ImageUtil;
import entity.Snake;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * The View class in the MVC pattern.<br> This class is responsible for
 * everything that is shown on the window of the game such as drawing
 * elements on the screen.
 *
 * @author Hilman Hazizi - modified
 */
public class View {
	private Model model;

    private Snake snake;
    private final Image background = ImageUtil.images.get("game-background");
	private Group root;

	/** The scene that runs the "game" part of the whole application. */
    public Scene scene;

	private GraphicsContext gc;
	private Stage stage;

	/**
	 * This AnimationTimer variable controls the "game loop" which allows
	 * the snake to be animated.
	 */
	public AnimationTimer animationTimer;

	/** This sets the width of the window. */
	public int screenWidth = 870;

	/** This sets the height of the window. */
	public int screenHeight = 560;

    private Rectangle pauseBg;
	private Label pausedLbl;
	private Button resumeBtn;
	private Button mainMenuBtn;

    private MediaPlayer mediaPlayer;

	/**
	 * The method that initializes variables (including those used for the
	 * MVC pattern) and the "game" part of the application.
	 *
	 * @param model The Model class in the MVC pattern.
	 * @param controller The Controller class in the MVC pattern.
	 * @param stage The stage taken from the Play class.
	 */
	public void initialize(Model model, Controller controller, Stage stage) {
		this.model = model;
        this.snake = model.snake;
		this.stage = stage;

		this.root = new Group();
        Canvas canvas = new Canvas(screenWidth, screenHeight);
		root.getChildren().add(canvas);
		this.scene = new Scene(root, screenWidth, screenHeight);

		int btnWidth = 200;

        Label pauseHintLbl = new Label("Press ESC to pause");
		pauseHintLbl.setFont(Font.font("Impact", 30));
		pauseHintLbl.setTextFill(Paint.valueOf("WHITE"));
		pauseHintLbl.setLayoutX(620.0);
		pauseHintLbl.setLayoutY(10.0);
		root.getChildren().add(pauseHintLbl);

		this.pauseBg = new Rectangle();
		pauseBg.setWidth(screenWidth);
		pauseBg.setHeight(screenHeight);
		pauseBg.setFill(Color.color(0, 0, 0, 0.20));

		this.pausedLbl = new Label("GAME PAUSED");
		pausedLbl.setFont(Font.font("GGfont_STRIVE", 64));
		pausedLbl.setTextFill(Paint.valueOf("WHITE"));
		pausedLbl.setLayoutX(250.0);
		pausedLbl.setLayoutY(86.0);

		this.resumeBtn = new Button("RESUME");
		resumeBtn.setFont(Font.font("GGfont_STRIVE", 24));
		resumeBtn.setTextFill(Paint.valueOf("#01dd1b"));
		resumeBtn.setPrefWidth(btnWidth);
		resumeBtn.setLayoutX((double) screenWidth / 2 - (double) btnWidth / 2);
		resumeBtn.setLayoutY(245.0);

		resumeBtn.setOnAction(event -> controller.pauseHandler());

		this.mainMenuBtn = new Button("MAIN MENU");
		mainMenuBtn.setFont(Font.font("GGfont_STRIVE", 24));
		mainMenuBtn.setTextFill(Paint.valueOf("#01dd1b"));
		mainMenuBtn.setLayoutX((double) screenWidth / 2 - (double) btnWidth / 2);
		mainMenuBtn.setPrefWidth(btnWidth);
		mainMenuBtn.setLayoutY(307.0);
		mainMenuBtn.setOnAction(event -> {
			try {
				switchToMainMenu();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		scene.setOnKeyPressed(controller::keyPress);

		gc = canvas.getGraphicsContext2D();

		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				drawElements(gc);
			}
		};

		animationTimer.start();

        String gameMusic =
				"src/main/resources/music/03-Blue Beating [Character select screen].mp3";
        Media sound = new Media(new File(gameMusic).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
		mediaPlayer.play();
	}

	/**
	 * The method that draws the elements of the game.
	 *
	 * @param g The GraphicsContext object used to draw on the canvas.
	 */
	public void drawElements(GraphicsContext g) {
		g.drawImage(background, 0, 0);
		model.stateHandler(g);
		drawScore(g);
	}

	/**
	 * The method that draws the score in the game.
	 *
	 * @param g The GraphicsContext object used to draw on the canvas.
	 */
	public void drawScore(GraphicsContext g) {
		g.setFont(Font.font("Impact", 30));
		g.setFill(Color.WHITE);
		g.fillText("SCORE : " + snake.score, 20, 40);
	}

	/**
	 * The method that switches the scene to the Fail Screen when
	 * the snake "dies".
	 *
	 * @throws IOException Throws an exception if the given file is not found.
	 */
	public void switchToFail() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull
				(getClass().getResource("/scenes/FailScreen.fxml")));
		Scene failScene = new Scene(root);
		stage.setScene(failScene);
		stage.show();
		mediaPlayer.stop();
	}

	/**
	 * The method that switches the scene to the Main Menu when
	 * the Main Menu button is clicked on the Pause Menu.
	 *
	 * @throws IOException Throws an exception if the given file is not found.
	 */
	public void switchToMainMenu() throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull
				(getClass().getResource("/scenes/MainMenu.fxml")));
		Scene failScene = new Scene(root);
		stage.setScene(failScene);
		stage.show();
		mediaPlayer.stop();
	}

	/**
	 * The method that draws the Pause Menu when ESC is pressed
	 * while the game is not paused.
	 */
	public void drawPause() {
		root.getChildren().add(pauseBg);
		root.getChildren().add(pausedLbl);
		root.getChildren().add(resumeBtn);
		root.getChildren().add(mainMenuBtn);
		mediaPlayer.setVolume(0.1);
	}

	/**
	 * The method that removes the Pause menu when ESC is pressed
	 * while the game is paused or when the Resume button is clicked
	 * on the Pause Menu.
	 */
	public void removePause() {
		root.getChildren().remove(pauseBg);
		root.getChildren().remove(pausedLbl);
		root.getChildren().remove(resumeBtn);
		root.getChildren().remove(mainMenuBtn);
		mediaPlayer.setVolume(0.3);
	}
}
