package gui;

import application.main;
import card.BaseCard;
import card.BirdCard;
import card.MonsterCard;
import card.SpellCard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class RightGamePane extends VBox{
	
	private MainButton chooseButton;
	private InfoPane monInfo;
	
	public RightGamePane() {
		super();
		String PhLabel = "Phrase "+Integer.toString(GameLogic.getInstance().getNowPhrase());
		Label PhText = new Label(PhLabel);
        PhText.setFont(new Font("Copperplate", 20));
        PhText.setPrefSize(250, 50);
        String PriceLabel = "Price: "+Integer.toString(GameLogic.getInstance().getSumPrice());
		Label PriceText = new Label(PriceLabel);
        PriceText.setFont(new Font("Copperplate", 20));
        PriceText.setPrefSize(250, 50);
        String chooseText = GameLogic.getInstance().getPhraseText();
		chooseButton = new MainButton(chooseText);
		chooseButton.setPrefWidth(100);
		chooseButton.setPrefHeight(30);
		chooseButton.setFont(Font.font("Verdana", 18));
		chooseButton.setOnMouseClicked(e -> chooseClicked(e));
		MainButton nextButton = new MainButton("Next");
		nextButton.setPrefWidth(100);
		nextButton.setPrefHeight(30);
		nextButton.setFont(Font.font("Verdana", 18));
		nextButton.setOnMouseClicked(e -> nextClicked(e));
		HBox buttonHBox = new HBox(chooseButton, nextButton);
        buttonHBox.setPrefSize(300, 300);
        buttonHBox.setSpacing(20);
        buttonHBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        
        
        ImageView pauseView = new ImageView(new Image(getClass().getResourceAsStream("../img/pause.png")));
        pauseView.setFitWidth(25);
        pauseView.setFitHeight(25);
        pauseView.setPreserveRatio(false);
        
        Button pauseBtn = new Button();
        pauseBtn.setGraphic(pauseView);
        pauseBtn.setPrefSize(25, 25);
        pauseBtn.setStyle("-fx-background-color: transparent; " +
                "-fx-background-radius: 25em; " +
                "-fx-min-width: 25px; " +
                "-fx-min-height: 25px; " +
                "-fx-max-width: 25px; " +
                "-fx-max-height: 25px;");
        pauseBtn.setAlignment(Pos.TOP_RIGHT);
        pauseBtn.setOnAction(event -> {
        	main.showPauseScene();
        });
        HBox pauseBox = new HBox();
        pauseBox.setPrefSize(25, 25);
        pauseBox.getChildren().add(pauseBtn);
        pauseBox.setAlignment(Pos.TOP_RIGHT);
        
        this.monInfo = new InfoPane();
        
        
        
        this.getChildren().addAll(pauseBox, PhText, PriceText, buttonHBox,  monInfo);
        
	}
	
	private void chooseClicked(MouseEvent e) {
		int nPhrase = GameLogic.getInstance().getNowPhrase();
		if(nPhrase == 1) {
			if(GameLogic.getInstance().getCur().isBuyable(GameLogic.getInstance().getnowClick()))GameLogic.getInstance().buyClick();
		}else if(nPhrase == 2) {
			GameLogic.getInstance().useEffClick();
		}else if(nPhrase == 3) {
			GameLogic.getInstance().AtkClick();
		}
	}
	
	private void nextClicked(MouseEvent e) {
		System.out.println("nextcall");
		GameLogic.getInstance().goToNextPhrase();
	}

	public void updateRightPane() {
		((Labeled) this.getChildren().get(2)).setText("Price "+Integer.toString(GameLogic.getInstance().getSumPrice()));
		((Labeled) this.getChildren().get(1)).setText("Phrase "+Integer.toString(GameLogic.getInstance().getNowPhrase()));
		chooseButton.setText(GameLogic.getInstance().getPhraseText());
	}

	public void updateCardInfo() {
		System.out.println("1");
		BaseCard card  = ((GameFieldPane) main.getGameRoot().getChildren().get(1)).getLastClick();
		System.out.println("0");
		this.monInfo.rnImgLB(card.getImg());
		this.monInfo.rnNameLB("Name : " + card.getName());
		this.monInfo.rnPriceLB("Price : " + card.getPrice());
		if (card instanceof MonsterCard) {
			System.out.println("2");
			this.monInfo.rnEffLB("Eff : " + ((MonsterCard) card).performEffect());
			this.monInfo.rnAtkLB("Atk : " + ((MonsterCard) card).getAtkVal());
			this.monInfo.rnDefLB("Def : " + ((MonsterCard) card).getDefVal());
		}else {
			System.out.println("3");
			this.monInfo.rnEffLB("Eff : " + card.toString());
			this.monInfo.rnAtkLB("Atk : -");
			this.monInfo.rnDefLB("Def : -");
		}
	}
}
