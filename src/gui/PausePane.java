package gui;

import application.main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameLogic;
import sound.SoundManager;

public class PausePane extends VBox{
	Text pauseText, restartText, quitText;
	SetButton resumeBtn, restartBtn, quitBtn;
	
	public PausePane() {
		// TODO Auto-generated constructor stub
		super();
		this.setPrefHeight(720);
		this.setPrefWidth(1280);
		this.setAlignment(Pos.CENTER);
		
		BackgroundSize backgroundSize = new BackgroundSize(1280,
    	        720,
    	        false,
    	        false,
    	        false,
    	        false);
    	Image img = new Image(getClass().getResourceAsStream("../img/Stars_bg.jpg"));
    	BackgroundImage image = new BackgroundImage(img,
    	        BackgroundRepeat.NO_REPEAT,
    	        BackgroundRepeat.NO_REPEAT,
    	        BackgroundPosition.CENTER,
    	        backgroundSize);

    	this.setBackground(new Background(image));
		
    	MainButton resumeBtn = new MainButton("Resume");
		MainButton restartBtn = new MainButton("Restart");
		MainButton quitBtn = new MainButton("Quit");
//		startButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				SoundManager.playSound("audio/select-click.mp3");
//				GameLogic gameInstance = GameLogic.getInstance();
//				gameInstance.initGame();
//				main.showGameScene();
//			}
//
//		});
    	
		this.pauseText = new Text("PAUSED");
		pauseText.setFont(Font.font("Verdana", FontWeight.BOLD, 55));
		pauseText.setFill(Color.WHITE);
		
//		this.resumeBtn = new SetButton("RESUME", 500, 500);
		resumeBtn.setOnAction(event -> {
			main.showGameScene();
		});

//		this.restartBtn = new SetButton("RESTART", 500, 500);
		restartBtn.setOnAction(event -> {
			GameLogic.clearInstance();
			GameLogic.getInstance().initGame();
			main.showGameScene();
		});

//		this.quitBtn = new SetButton("QUIT", 500, 500);
		quitBtn.setOnAction(event -> {
			main.showMainMenuScene();
			GameLogic.clearInstance();
		});
		this.setSpacing(10);
		this.getChildren().addAll(pauseText, resumeBtn, restartBtn, quitBtn);
	}
}