package gui;

import card.BaseCard;
import card.BirdCard;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class LeftGamePane extends VBox{
	
	private Label Player1_Text, Player2_Text, CPlayer_Text;
	Text turnPlay;
	Rectangle turnFrame;
	
	public LeftGamePane() {
    	super();
        CPlayer_Text = new Label("Current player: "+Integer.toString(GameLogic.getInstance().getCurrentPlayer()+1));
        CPlayer_Text.setFont(new Font("Copperplate", 15));
        CPlayer_Text.setPrefSize(150, 50);
        Player1_Text = new Label("Player1(Tokens) : "+Integer.toString(GameLogic.getInstance().getPlayers().get(0).getMoney())+'\n'+Integer.toString(GameLogic.getInstance().getPlayers().get(0).getHp()));
        Player1_Text.setFont(new Font("Copperplate", 15));
        Player1_Text.setPrefSize(150, 50);
        Player2_Text = new Label("Player2(Tokens) : "+Integer.toString(GameLogic.getInstance().getPlayers().get(1).getMoney())+'\n'+Integer.toString(GameLogic.getInstance().getPlayers().get(1).getHp()));
        Player2_Text.setFont(new Font("Copperplate", 15));
        Player2_Text.setPrefSize(150,50);
        
        this.turnPlay = new Text("Ready!!!");
		
//		this.turnFrame = new Rectangle();
//		this.turnFrame.setWidth(this.turnPlay.getBoundsInLocal().getWidth());
//		this.turnFrame.setHeight(this.turnPlay.getBoundsInLocal().getHeight());
//		this.turnFrame.setFill(Color.WHITE);
//		this.turnFrame.setStroke(Color.BLACK);
//		this.turnFrame.setArcHeight(40);
//		this.turnFrame.setArcWidth(40);
		
		this.getChildren().addAll(turnPlay);
        
        this.getChildren().addAll(CPlayer_Text, Player1_Text, Player2_Text);
        this.setPrefSize(200, 720);
        this.setSpacing(30);
//        this.setStyle("-fx-background-color: red");
        this.setAlignment(javafx.geometry.Pos.CENTER);
	}
	
	public void updateStatus() {
		CPlayer_Text.setText("Current player: "+Integer.toString(GameLogic.getInstance().getCurrentPlayer()+1));
		Player1_Text.setText("Player1: "+Integer.toString(GameLogic.getInstance().getPlayers().get(0).getMoney())+'\n'+Integer.toString(GameLogic.getInstance().getPlayers().get(0).getHp()));
		Player2_Text.setText("Player2: "+Integer.toString(GameLogic.getInstance().getPlayers().get(1).getMoney())+'\n'+Integer.toString(GameLogic.getInstance().getPlayers().get(1).getHp()));
		this.turnPlay.setText(Integer.toString(GameLogic.getInstance().getTurnCount()));
	}
}
