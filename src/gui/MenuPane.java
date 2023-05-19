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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;

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
		this.setStyle("-fx-background-color:rgba(0,0,0,1)");
		Image image = new Image(getClass().getResourceAsStream("../img/Dog.png"));;
		ImageView imageView = new ImageView(image);
		
//		imageView.setEffect(neonblend());
		BorderPane bPane = new BorderPane();
		bPane.setPrefWidth(150);
		bPane.setPrefHeight(150);
		bPane.setCenter(imageView);
		bPane.setPadding(new Insets(70, 0, 0, 0));
		this.setTop(bPane);

		VBox vBox = new VBox(50);
		vBox.setAlignment(Pos.BASELINE_CENTER);
		Text title = new Text("Petitor");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
		title.setFill(Color.WHITE);

		title.setEffect(neonblend());

		MainButton startButton = new MainButton("Start");
		MainButton exitButton = new MainButton("Exit");
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//				SoundManager.playSound("audio/select-click.mp3");
				GameLogic gameInstance = GameLogic.getInstance();
				gameInstance.initGame();
				main.showGameScene();
			}

		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// SoundManager.playSound("audio/select-click.mp3");
				// try {
				// Thread.sleep(200);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
				Platform.exit();
				System.exit(0);
			}
		});
		vBox.getChildren().addAll(title, startButton, exitButton);
		this.setCenter(vBox);

		Text description = new Text(
				"\"Welcome our Petitor, enjoy your battle.\"");
		description.setFill(Color.WHITE);
		description.setWrappingWidth(500);
		description.setTextAlignment(TextAlignment.CENTER);
		this.setBottom(description);
	}

	/**
	 * create neon blend effect
	 * 
	 * @return neon blend effect
	 */
	public Blend neonblend() {
		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.rgb(0, 0, 0, 0));
		ds.setOffsetX(2);
		ds.setOffsetY(2);
		ds.setRadius(2);
		ds.setSpread(0.1);

		blend.setBottomInput(ds);

		DropShadow ds1 = new DropShadow();
		ds1.setColor(Color.web("#000000"));
		ds1.setRadius(5);
		ds1.setSpread(0.1);

		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.MULTIPLY);

		InnerShadow is = new InnerShadow();
		is.setColor(Color.web("#ffffff"));
		is.setRadius(2);
		is.setChoke(0.2);
		blend2.setBottomInput(is);

		InnerShadow is1 = new InnerShadow();
		is1.setColor(Color.web("#000000"));
		is1.setRadius(2);
		is1.setChoke(0.1);
		blend2.setTopInput(is1);

		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(ds1);
		blend1.setTopInput(blend2);

		blend.setTopInput(blend1);
		return blend;
	}
}