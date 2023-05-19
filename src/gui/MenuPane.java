package gui;

import application.main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import sound.SoundManager;

/**
 * Main menu pane
 */
public class MenuPane extends BorderPane {
	/**
	 * MenuBpane Main Constructor
	 */
	public MenuPane() {
		super();
		this.setPrefHeight(720);
		this.setPrefWidth(1280);
		Image image = new Image(getClass().getResourceAsStream("../img/Logo.png"));;
		ImageView Logo = new ImageView(image);
		Logo.setFitHeight(150);
		Logo.setPreserveRatio(true);
		
		BorderPane bPane = new BorderPane();
		bPane.setPrefWidth(150);
		bPane.setPrefHeight(150);
		bPane.setCenter(Logo);
		bPane.setPadding(new Insets(130, 0, 0, 0));
		this.setTop(bPane);

		VBox vBox = new VBox(50);
		vBox.setAlignment(Pos.BASELINE_CENTER);
		Text title = new Text("Game start!");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
		title.setFill(Color.WHITE);

		MainButton startButton = new MainButton("Start");
		MainButton exitButton = new MainButton("Exit");
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				SoundManager.playSound("audio/select-click.mp3");
				GameLogic gameInstance = GameLogic.getInstance();
				gameInstance.initGame();
				main.showGameScene();
			}

		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
				System.exit(0);
			}
		});
		vBox.getChildren().addAll(title, startButton, exitButton);
		this.setCenter(vBox);

		HBox hBox_base = new HBox();
		hBox_base.setAlignment(Pos.CENTER);
		
		Image KBimg = new Image(getClass().getResourceAsStream("../img/Logo_KB.png"));;
		ImageView KB = new ImageView(KBimg);
		KB.setFitHeight(30);
		KB.setPreserveRatio(true);
		
		Text description = new Text(
				"\"Welcome our Petitor, enjoy your battle.\"");
		description.setFill(Color.WHITE);
		description.setTextAlignment(TextAlignment.CENTER);
		hBox_base.getChildren().addAll(KB, description);
		hBox_base.setSpacing(10);
		hBox_base.setPrefHeight(50);
		this.setBottom(hBox_base);
	}
}