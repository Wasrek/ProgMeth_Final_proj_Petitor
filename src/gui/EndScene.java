package gui;

import application.main;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameLogic;
import sound.SoundManager;
import user.Player;

/**
 * An ending scene pane
 * @author Petitor
 *
 */
public class EndScene extends VBox{
	/**
	 * The text showing in end scene
	 */
	Text endText;
	/**
	 * Back to home page button
	 */
	MainButton backHome;
	
	/**
	 * Constructor for Endscene
	 * @param player	winner player
	 */
	public EndScene(Player player) {
		// TODO Auto-generated constructor stub
		super();
		this.minHeight(720);
		this.setAlignment(javafx.geometry.Pos.CENTER);
		BackgroundSize backgroundSize = new BackgroundSize(1280,
    	        720,
    	        false,
    	        false,
    	        false,
    	        false);
    	Image img = new Image(getClass().getResourceAsStream("../img/end.jpg"));
    	BackgroundImage image = new BackgroundImage(img,
    	        BackgroundRepeat.NO_REPEAT,
    	        BackgroundRepeat.NO_REPEAT,
    	        BackgroundPosition.CENTER,
    	        backgroundSize);

    	this.setBackground(new Background(image));
		
    	StackPane p = new StackPane();
    	
    	String imgpath = "../gif/blueobs.gif";
		Image gifimg = new Image(getClass().getResourceAsStream(imgpath));;
		ImageView ani = new ImageView(gifimg);
		ani.setFitHeight(400);
		ani.setPreserveRatio(true);
		
		p.getChildren().add(ani);
		
		this.endText = new Text("The winner is " + player.toString());
		endText.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
		endText.setFill(Color.WHITE);
//		this.backHome = new SetButton("Back Home", 500, 500);
		this.backHome = new MainButton("Back Home");
		this.backHome.setOnAction(event -> {
			SoundManager.playSound("audio/select-click.mp3");
			FadeTransition fadeOu = new FadeTransition(javafx.util.Duration.seconds(1), this);
			fadeOu.setFromValue(0);
			fadeOu.setToValue(1);
			fadeOu.setCycleCount(1);
			fadeOu.play();
			GameLogic.clearInstance();
			main.showMainMenuScene();
		});
		this.setSpacing(30);
		p.getChildren().add(endText);
		this.getChildren().addAll(p, backHome);
	}
}