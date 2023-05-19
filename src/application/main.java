package application;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logic.GameLogic;
import gui.MenuPane;
import gui.PausePane;
import gui.EndScene;
import gui.GameFieldPane;
import gui.LeftGamePane;
import gui.RightGamePane;


public class main extends Application {
	
	/**
	 * Main Stage
	 */
	public static Stage mainStage;
	
	
	/**
	 * JavaFX Start Function
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainStage.setTitle("Petitor");
		mainStage.setResizable(false);
		String imagePath = "../img/Duck.png";
		mainStage.getIcons().add(new Image(getClass().getResourceAsStream(imagePath)));
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		showSplashScreen();
		mainStage.show();
	}

	/**
	 * Main Entry Point
	 * 
	 * @param args arguments
	 */
	public static void main(String args[]) {
//		System.out.println("Hi");
		launch(args);
	}
	
	public static void showSplashScreen() {
		HBox splashPane = new HBox();
		splashPane.setAlignment(Pos.CENTER);
		Text splashPaneText = new Text("Final Project for PROG METH 2565");
		splashPaneText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		splashPane.getChildren().add(splashPaneText);
		mainStage.setScene(new Scene(splashPane, 1280, 720));

		FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.seconds(2), splashPane);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);

		FadeTransition fadeOut = new FadeTransition(javafx.util.Duration.seconds(2), splashPane);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);

		fadeIn.play();
		fadeIn.setOnFinished(e -> {
			try {
				// new SoundManager().playSound("sound/splash.wav");
				fadeOut.play();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			new MenuBpane();
		});
		fadeOut.setOnFinished(e -> {
			try {
				showMainMenuScene();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	
	/**
	 * Set menuScene in mainStage
	 * 
	 */
	public static void showMainMenuScene() {
		Pane menuRoot = new Pane();

		MenuPane menupane = new MenuPane();
		// BackgroundSize bgSize = new BackgroundSize(1280, 720, false, false, false,
		// false);
//		Image img = new Image(main.class.getClassLoader().getResourceAsStream("../img/Duck.png"));
		WritableImage img = new WritableImage(3000, 2000);
        PixelWriter pixelWriter = img.getPixelWriter();
        for (int x = 0; x < 1920; x++) {
            for (int y = 0; y < 1080; y++) {
                pixelWriter.setArgb(x, y, 0x00000000); // Set each pixel to white color
            }
        }
		// BackgroundImage bgImg = new BackgroundImage(img, null, null, null, bgSize);
		// menuRoot.setBackground(new Background(bgImg));

		ImageView bgImg = new ImageView(img);
		bgImg.setFitWidth(1920);
		bgImg.setFitHeight(1080);
		menuRoot.getChildren().add(bgImg);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.minutes(1));
		pathTransition.setNode(bgImg);
		pathTransition.setPath(new Circle(1000, 700, 50));
		pathTransition.setCycleCount(Animation.INDEFINITE);
		pathTransition.play();

		menuRoot.getChildren().add(menupane);

//		menuRoot.getChildren().add(tutorialBackPane);

//		SoundManager.setCurrentBGM("audio/Kool_Kats.mp3");

		Pane whitePane = new Pane();
		whitePane.setPrefSize(1280, 720);
		whitePane.setStyle("-fx-background-color: white");
		whitePane.setMouseTransparent(true);
		menuRoot.getChildren().add(whitePane);

		FadeTransition fadeout = new FadeTransition(javafx.util.Duration.seconds(1), whitePane);
		fadeout.setFromValue(1);
		fadeout.setToValue(0);
		fadeout.setCycleCount(1);

		Scene menuScene = new Scene(menuRoot, 1280, 720);
		mainStage.setScene(menuScene);
		fadeout.play();

//		menuScene.setOnKeyPressed(value -> {
//			if (value.getCode() == KeyCode.ESCAPE) {
//				tutorialBackPane.setVisible(false);
//			}
//		});
	}
	
	static HBox gameRoot = new HBox();
	
	public static void showGameScene() {
		gameRoot = new HBox();
//		BorderPane gamePane = new BorderPane();
		LeftGamePane leftPane = new LeftGamePane();
		RightGamePane rightPane = new RightGamePane();
		GameFieldPane gamePane = new GameFieldPane();
		gameRoot.getChildren().addAll(leftPane, gamePane, rightPane);
		System.out.println("gamePane added");
		gameRoot.setAlignment(javafx.geometry.Pos.CENTER);
//		
//		if (pausePane == null)
//			initializePauseScreen();
//		else {
//			pausePane.setVisible(false);
//		}
//
//		if (tutorialBackPaneInGame == null)
//			tutorialBackPaneInGame = new TutorialPane();
//		else {
//			tutorialBackPaneInGame.setVisible(false);
//		}
//
//		gameRoot.getChildren().add(pausePane);
//		gameRoot.getChildren().add(tutorialBackPaneInGame);

		Scene gameScene = new Scene(gameRoot, 1280, 720);
		gamePane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//		gamePane.setPadding(new Insets(10));

//		MapPane mapPane = new MapPane();
//		gamePane.setTop(mapPane);


//		GameLogic.setPrepCard(prepCard);
//		GameLogic.setLocateCard(locateCard);
//		GameLogic.setGoDeepCard(goDeepCard);


//		gamePane.setBottom(actionHBox);
		System.out.println("Set MainStage");
		mainStage.setScene(gameScene);

//		SoundManager.setCurrentBGM("audio/SuddenDeath.mp3");
//
//		gameScene.setOnKeyPressed(value -> {
//			if (value.getCode() == KeyCode.ESCAPE) {
//				if (tutorialBackPaneInGame.isVisible())
//					tutorialBackPaneInGame.setVisible(false);
//				else
//					pausePane.setVisible(pausePane.isVisible() ? false : true);
//				;
//			}
//		});
	}
	
	public static void showPauseScene() {
		HBox test = new HBox();
		PausePane pausePane = new PausePane();
		test.getChildren().add(pausePane);
		Scene pauseScene = new Scene(test, 1280, 720);
		mainStage.setScene(pauseScene);
	}
	
	public static void showEndScene() {
		HBox test1 = new HBox();
		int winner = 1;
		if (GameLogic.getInstance().getPlayers().get(1).getHp() <= 0) winner =0;
		EndScene endPane = new EndScene(GameLogic.getInstance().getPlayers().get(winner));
		test1.getChildren().add(endPane);
		Scene endScene = new Scene(test1, 1280, 720);
		mainStage.setScene(endScene);
		
	}

	public static HBox getGameRoot() {
		return gameRoot;
	}

	public static void setGameRoot(HBox gameRoot) {
		main.gameRoot = gameRoot;
	}
}
