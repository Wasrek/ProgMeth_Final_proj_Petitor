package gui;

import application.main;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import card.BaseCard;
import card.BirdCard;
import card.MonsterCard;
import card.SpellCard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
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

/**
 * 
 * A pane showing on the right part of the gameScene
 * @author Wishmeluck
 *
 */
public class RightGamePane extends VBox{
	
	/**
	 * the choose button contain functions depends on current phrase
	 */
	private MainButton chooseButton;
	/**
	 * Cards information pane
	 */
	private InfoPane monInfo;
	
	/**
	 * Constructor for RightGamePane
	 */
	public RightGamePane() {
		super();
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), getInsets())));
		this.setPadding(new Insets(13,15,20,10));
		String PhLabel = "Phrase "+Integer.toString(GameLogic.getInstance().getNowPhrase());
		Label PhText = new Label(PhLabel);
        PhText.setFont(new Font("Copperplate", 20));
        PhText.setPrefSize(250, 50);
        PhText.setTextFill(Color.WHITE);
        String curmoney = " / "+GameLogic.getInstance().getCur().getMoney();
        String PriceLabel = "Price: "+Integer.toString(GameLogic.getInstance().getSumPrice())+curmoney;
		Label PriceText = new Label(PriceLabel);
        PriceText.setFont(new Font("Copperplate", 20));
        PriceText.setPrefSize(250, 50);
        PriceText.setTextFill(Color.WHITE);
        
        
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
        
        buttonHBox.setPadding(new Insets(0,0,10,0));
        
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
        	SoundManager.playSound("audio/select-click.mp3");
        	main.showPauseScene();
        });
        HBox pauseBox = new HBox();
        pauseBox.setPrefSize(25, 25);
        pauseBox.getChildren().add(pauseBtn);
        pauseBox.setAlignment(Pos.TOP_RIGHT);
        
        this.monInfo = new InfoPane();
        
        this.getChildren().addAll(pauseBox, PhText, PriceText, NotiPane, buttonHBox,  monInfo);
        
	}
	
	/**
	 * A method calling functions when the choose button is clicked 
	 * @param e	a MouseEvent
	 */
	private void chooseClicked(MouseEvent e) {
		SoundManager.playSound("audio/select-click.mp3");
		int nPhrase = GameLogic.getInstance().getNowPhrase();
		if(GameLogic.getInstance().getnowClick().size()==0) return ;
		if(nPhrase == 1) {
			if(GameLogic.getInstance().getCur().isBuyable(GameLogic.getInstance().getnowClick())) {
				System.out.println("Buy");
				GameLogic.getInstance().buyClick();
			}else {
				System.out.println("Can't buy, check in player");
			}
		}else if(nPhrase == 2) {
			GameLogic.getInstance().useEffClick();
		}else if(nPhrase == 3) {
			GameLogic.getInstance().AtkClick();
		}
	}
	
	/**
	 * A method calling functions when the next button is clicked, changing phrase.
	 * @param e	a MouseEvent
	 */
	private void nextClicked(MouseEvent e) {
		SoundManager.playSound("audio/select-click.mp3");
		System.out.println("nextcall");
		GameLogic.getInstance().goToNextPhrase();
	}

	/**
	 * Update RightGamePane information showed
	 */
	public void updateRightPane() {
		String curmoney = " / "+GameLogic.getInstance().getCur().getMoney();
		((Labeled) this.getChildren().get(2)).setText("Price "+Integer.toString(GameLogic.getInstance().getSumPrice())+curmoney);
		if(GameLogic.getInstance().getSumPrice() > GameLogic.getInstance().getCur().getMoney()) {
			((Labeled) this.getChildren().get(2)).setTextFill(Color.RED);
		}else {
			((Labeled) this.getChildren().get(2)).setTextFill(Color.WHITE);
		}
		((Labeled) this.getChildren().get(1)).setText("Phrase "+Integer.toString(GameLogic.getInstance().getNowPhrase()));
		chooseButton.setText(GameLogic.getInstance().getPhraseText());
	}

	/**
	 * Update Card information showed
	 */
	public void updateCardInfo() {
//		Platform.runLater(
//				  () -> {
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
//				  }
//				);
		System.out.println("1");
	}
	
}
