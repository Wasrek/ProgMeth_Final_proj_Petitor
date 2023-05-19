package gui;

import application.main;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameLogic;
import user.Player;

public class EndScene extends VBox{
	Text endText;
	SetButton backHome;
	
	public EndScene(Player player) {
		// TODO Auto-generated constructor stub
		super();
		this.endText = new Text("The winner is " + player.toString());
		this.backHome = new SetButton("Back Home", 500, 500);
		this.backHome.setOnAction(event -> {
			GameLogic.clearInstance();
			main.showMainMenuScene();
		});
		
		this.getChildren().addAll(endText, backHome);
	}
}