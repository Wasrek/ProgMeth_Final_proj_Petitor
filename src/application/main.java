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
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
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
import sound.SoundManager;
import gui.MenuPane;
import gui.PausePane;
import gui.EndScene;
import gui.GameFieldPane;
import gui.LeftGamePane;
import gui.RightGamePane;
import sound.SoundManager;


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
		String imagePath = "../img/Logo.png";
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
				SoundManager.playSound("audio/wind-blow.mp3");
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
		String imagePath = "../img/Stars_bg.jpg";
		Image img = new Image(main.class.getResourceAsStream(imagePath));
		ImageView bgImg = new ImageView(img);
		bgImg.setFitWidth(2500);
		bgImg.setFitHeight(2000);
		menuRoot.getChildren().add(bgImg);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.minutes(0.75));
		pathTransition.setNode(bgImg);
		pathTransition.setPath(new Circle(1000, 700, 50));
		pathTransition.setCycleCount(Animation.INDEFINITE);
		Platform.runLater(
				  () -> {
					  pathTransition.play();
				  }
				);

		menuRoot.getChildren().add(menupane);

		SoundManager.setCurrentBGM("audio/MenuPane.mp3");

		Pane whitePane = new Pane();
		whitePane.setPrefSize(1280, 720);
		whitePane.setStyle("-fx-background-color: Black");
		whitePane.setMouseTransparent(true);
		menuRoot.getChildren().add(whitePane);

		FadeTransition fadeout = new FadeTransition(javafx.util.Duration.seconds(1), whitePane);
		fadeout.setFromValue(1);
		fadeout.setToValue(0);
		fadeout.setCycleCount(1);

		Scene menuScene = new Scene(menuRoot, 1280, 720);
		mainStage.setScene(menuScene);
		Platform.runLater(
				  () -> {
					  fadeout.play();
				  }
				);

	}
	
	static HBox gameRoot = new HBox();
	
	public static void showGameScene() {
		
		StackPane bgPane = new StackPane();
		String imagePath = "../img/Stars_bg.jpg";
		Image img = new Image(main.class.getResourceAsStream(imagePath));
		ImageView bgImg = new ImageView(img);
		bgImg.setFitWidth(1800);
		bgImg.setFitHeight(2000);
		bgPane.getChildren().add(bgImg);
		
//		Platform.runLater(
//				  () -> {
//					  	PathTransition pathTransition = new PathTransition();
//						pathTransition.setDuration(Duration.minutes(1));
//						pathTransition.setNode(bgImg);
//						pathTransition.setPath(new Circle(1000, 700, 50));
//						pathTransition.setCycleCount(Animation.INDEFINITE);
//						pathTransition.play();
//				  }
//				);
		
		gameRoot = new HBox();
//		BorderPane gamePane = new BorderPane();
		LeftGamePane leftPane = new LeftGamePane();
		RightGamePane rightPane = new RightGamePane();
		GameFieldPane gamePane = new GameFieldPane();
		gameRoot.getChildren().addAll(leftPane, gamePane, rightPane);
		System.out.println("gamePane added");
		gameRoot.setAlignment(javafx.geometry.Pos.CENTER);
		
		bgPane.getChildren().add(gameRoot);
		
		Scene gameScene = new Scene(bgPane, 1280, 720);
//		gamePane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		System.out.println("Set MainStage");
		mainStage.setScene(gameScene);

		SoundManager.setCurrentBGM("audio/GamePane.mp3");
	}
	
	public static void showPauseScene() {
		HBox test = new HBox();
		PausePane pausePane = new PausePane();
		test.getChildren().add(pausePane);
		Scene pauseScene = new Scene(test, 1280, 720);
		mainStage.setScene(pauseScene);
	}
	
	public static void showEndPage() {
		int winner = 1;
		if (GameLogic.getInstance().getPlayers().get(1).getHp() <= 0) winner =0;
		EndScene endPane = new EndScene(GameLogic.getInstance().getPlayers().get(winner));
		Scene endScene = new Scene(endPane, 1280, 720);
		mainStage.setScene(endScene);
		FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.seconds(1), endPane);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);
		fadeIn.play();
	}
	
	public static void showEndScene() {
		HBox splashPane = new HBox();
		splashPane.setAlignment(Pos.CENTER);
		splashPane.setStyle("-fx-background-color: Black");
		Text splashPaneText = new Text("GAME END");
		splashPaneText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		splashPaneText.setFill(Color.WHITE);
		splashPane.getChildren().add(splashPaneText);
		mainStage.setScene(new Scene(splashPane, 1280, 720));

		FadeTransition fadeIn = new FadeTransition(javafx.util.Duration.seconds(1), splashPane);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);

		FadeTransition fadeOut = new FadeTransition(javafx.util.Duration.seconds(1), splashPane);
		fadeOut.setFromValue(1);
		fadeOut.setToValue(0);
		fadeOut.setCycleCount(1);
		
		fadeIn.play();
		
		fadeIn.setOnFinished(e -> {
			try {
				SoundManager.playSound("audio/bell.mp3");
				fadeOut.play();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
//			new MenuBpane();
		});
		fadeOut.setOnFinished(e -> {
			try {
				showEndPage();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	public static HBox getGameRoot() {
		return gameRoot;
	}

	public static void setGameRoot(HBox gameRoot) {
		main.gameRoot = gameRoot;
	}
}
