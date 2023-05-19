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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.GameLogic;
import sound.SoundManager;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;

public class RightGamePane extends VBox{
	
	private MainButton chooseButton;
	private InfoPane monInfo;
	
	public RightGamePane() {
		super();
		String PhLabel = "Phrase "+Integer.toString(GameLogic.getInstance().getNowPhrase());
		Label PhText = new Label(PhLabel);
        PhText.setFont(new Font("Copperplate", 20));
        PhText.setPrefSize(250, 50);
        String curmoney = " / "+GameLogic.getInstance().getCur().getMoney();
        String PriceLabel = "Price: "+Integer.toString(GameLogic.getInstance().getSumPrice())+curmoney;
		Label PriceText = new Label(PriceLabel);
        PriceText.setFont(new Font("Copperplate", 20));
        PriceText.setPrefSize(250, 50);
        
        
        AnimatePane NotiPane = new AnimatePane();
        
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
        
        
        
        this.getChildren().addAll(pauseBox, PhText, PriceText, NotiPane, buttonHBox,  monInfo);
        
	}
	
	private void chooseClicked(MouseEvent e) {
		SoundManager.playSound("audio/select-click.mp3");
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
		SoundManager.playSound("audio/select-click.mp3");
		System.out.println("nextcall");
		GameLogic.getInstance().goToNextPhrase();
	}

	public void updateRightPane() {
		String curmoney = " / "+GameLogic.getInstance().getCur().getMoney();
		((Labeled) this.getChildren().get(2)).setText("Price "+Integer.toString(GameLogic.getInstance().getSumPrice())+curmoney);
		if(GameLogic.getInstance().getSumPrice() > GameLogic.getInstance().getCur().getMoney()) {
			((Labeled) this.getChildren().get(2)).setTextFill(Color.RED);
		}else {
			((Labeled) this.getChildren().get(2)).setTextFill(Color.BLACK);
		}
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
		playNotiPane();
	}
	
	public void playNotiPane() {
		Platform.runLater(
				  () -> {
					  String updgif = "../gif/Loading.gif";
					  ((AnimatePane) this.getChildren().get(3)).setupdate("yeah",updgif);
						FadeTransition fd = new FadeTransition();  
						
						//set the duration for the Fade transition   
						fd.setDuration(Duration.millis(1000));   
						fd.setFromValue(0);  
						fd.setToValue(1);  
						//set the cycle count for the Fade transition   
//			        	    fd.setCycleCount(3);  
						  
						//the transition will set to be auto reversed by setting this to true   
						//set Circle as the node onto which the transition will be applied  
						fd.setNode(this.getChildren().get(3));  
						  
						
						FadeTransition fdo = new FadeTransition();  
						
						//set the duration for the Fade transition   
						fdo.setDuration(Duration.millis(3000));   
						fdo.setFromValue(1);  
						fdo.setToValue(0);
						  
						//the transition will set to be auto reversed by setting this to true   
						//set Circle as the node onto which the transition will be applied  
						fdo.setNode(this.getChildren().get(3));  
						  
						  
						//playing the transition   
						fd.play();  
						fd.setOnFinished(e -> {
							try {
								fdo.play();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
//			        			new MenuBpane();
						});
						fdo.setOnFinished(e -> {
							try {
								((AnimatePane) this.getChildren().get(3)).setnormal();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						});
				  }
				);
	}
}
