package gui;

import application.main;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class PausePane extends VBox{
	Text pauseText, restartText, quitText;
	SetButton resumeBtn, restartBtn, quitBtn;
	
	public PausePane() {
		// TODO Auto-generated constructor stub
		super();
		this.setPrefHeight(1920);
		this.setPrefWidth(1080);
		this.setAlignment(Pos.CENTER);
		
		this.pauseText = new Text("PAUSED");
		this.pauseText.setFont(new Font(36));
		
		this.resumeBtn = new SetButton("RESUME", 500, 500);
		resumeBtn.setOnAction(event -> {
			main.showGameScene();
		});

		this.restartBtn = new SetButton("RESTART", 500, 500);
		restartBtn.setOnAction(event -> {
			GameLogic.clearInstance();
			GameLogic.getInstance().initGame();
			main.showGameScene();
		});

		this.quitBtn = new SetButton("QUIT", 500, 500);
		quitBtn.setOnAction(event -> {
			main.showMainMenuScene();
			GameLogic.clearInstance();
		});
		
		this.getChildren().addAll(pauseText, resumeBtn, restartBtn, quitBtn);
	}
}