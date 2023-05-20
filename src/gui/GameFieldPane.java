package gui;

import javafx.animation.Animation;

import javafx.animation.PathTransition;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;

import java.util.ArrayList;
import java.util.List;

import application.main;
import card.BaseCard;
import card.BirdCard;
import card.MonsterCard;
import card.SpellCard;

/**
 * An pane showing the middle part of the gameScene including field and market
 * @author Petitor
 *
 */
public class GameFieldPane extends VBox {
	/**
	 * Monster market cards
	 */
	List<ImageView>  MM = new ArrayList<>();
    /**
     * Spell market cards
     */
    List<ImageView>  SM = new ArrayList<>();
    /**
     * Player 1 monster cards
     */
    List<ImageView>  P1M = new ArrayList<>();
    /**
     * Texts for player 1 monster card describe power for each
     */
    List<Label>  P1MT = new ArrayList<>();
    /**
     * Player 1 spell cards
     */
    List<ImageView>  P1S = new ArrayList<>();
    /**
     * Texts for player 1 spell card describe power for each
     */
    List<Label>  P1ST = new ArrayList<>();
    /**
     * Player 2 monster cards
     */
    List<ImageView>  P2M = new ArrayList<>();
    /**
     * Texts for player 2 monster card describe power for each
     */
    List<Label>  P2MT = new ArrayList<>();
    /**
     * Player 2 spell cards
     */
    List<ImageView>  P2S = new ArrayList<>();
    /**
     * Texts for player 2 spell card describe power for each
     */
    List<Label>  P2ST = new ArrayList<>();

	/**
	 * last clicked card
	 */
	BaseCard lastClick = new BirdCard();
    /**
     * Image using when the field box don't contain any card
     */
    Image nocard = new Image(getClass().getResourceAsStream("../img/nocard.jpg"));
    /**
     * Constructor for GameFieldPane and initializing part
     */
    public GameFieldPane() {
        // Monster Market label
    	super();
    	this.setPadding(new Insets(30,0,30,0));
		
        Label MM_Text = new Label("Monster Market");
        MM_Text.setFont(new Font("Copperplate", 14));
        MM_Text.setPrefSize(135, 20);
        MM_Text.setTextFill(Color.WHITE);

        // Top HBox
        HBox MM_TextHBox = new HBox(MM_Text);
        MM_TextHBox.setPrefSize(790, 25);
        MM_TextHBox.setPadding(new Insets(0, 0, 6, 0));
        MM_TextHBox.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
        
        int ImgMSize = 86, ImgGSize = 120, MSBoxW = 120, MSBoxH = 454, MSBoxSp = 5;
        int PyBoxW = 360, PyBoxH = 454, PyBoxSp = 40;
        int MKBoxW = 360, MKBoxH = 86, MKBoxSp = 50;
        
        MM = new ArrayList<>();
        SM = new ArrayList<>();
        P1M = new ArrayList<>();
        P1S = new ArrayList<>();
        P2M = new ArrayList<>();
        P2S = new ArrayList<>();
        
        String nStatus = " ";
        
        // Monster images
        for(int i=0; i<3; i++) {
        	System.out.println("Getting mm pic");
        	if(GameLogic.getInstance().getMonMarket().size()<=i) {
        		MM.add(new ImageView(nocard));
        	}else MM.add(new ImageView(GameLogic.getInstance().getMonMarket().get(i).getImg()));
        	MM.get(i).setFitWidth(ImgMSize);
        	MM.get(i).setFitHeight(ImgMSize);
        	int idx = i;
        	MM.get(i).setOnMouseClicked(e -> MMClicked(e, idx));
        }
        System.out.println(MM.size());
        // Center HBox
        HBox MonMarHBox = new HBox(MM.get(0), MM.get(1), MM.get(2));
        MonMarHBox.setPrefSize(MKBoxW, MKBoxH);
        MonMarHBox.setPadding(new Insets(0, 0, 10, 0));
        MonMarHBox.setSpacing(MKBoxSp);
        MonMarHBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Player 1 spell and monster images
        for(int i=0; i<3; i++) {
        	System.out.println(GameLogic.getInstance().getPlayers().get(0).getSpHand().size());
        	if(GameLogic.getInstance().getPlayers().get(0).getSpHand().size()<=i) {
        		P1S.add(new ImageView(nocard));
        	}else{
        		P1S.add(new ImageView(GameLogic.getInstance().getPlayers().get(0).getSpHand().get(i).getImg()));
        	}
        	P1S.get(i).setFitWidth(ImgGSize);
        	P1S.get(i).setFitHeight(ImgGSize);
        	int idx = i;
        	P1S.get(i).setOnMouseClicked(e -> FieldClicked(e, idx, 0, false));
        }
        
        VBox P1SpellBox = new VBox(P1S.get(0), P1S.get(1), P1S.get(2));
        P1SpellBox.setPrefSize(MSBoxW, MSBoxH);
        P1SpellBox.setSpacing(10);
        P1SpellBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        System.out.println("hi1");
        
        for(int i=0; i<3; i++) {
        	System.out.println(GameLogic.getInstance().getPlayers().get(0).getMonHand().size());
        	if(GameLogic.getInstance().getPlayers().get(0).getMonHand().size()<=i) {
        		P1M.add(new ImageView(nocard));
        		P1MT.add(new Label(""));
        	}else{
        		P1M.add(new ImageView(GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i).getImg()));
        		if(((MonsterCard) GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i)).getStatus()) {
        			nStatus = "Atk : ";
        		}else {
        			nStatus = "Def : ";
        		}
        		P1MT.add(new Label(nStatus+Integer.toString(((MonsterCard) GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i)).getVal())));
        	}
        	
        	P1M.get(i).setFitWidth(ImgGSize);
        	P1M.get(i).setFitHeight(ImgGSize);
        	int idx = i;
        	P1M.get(i).setOnMouseClicked(e -> FieldClicked(e, idx, 0, true));
        	
        	P1MT.get(P1M.size()-1).setFont(new Font("Copperplate", 12));
        	P1MT.get(P1M.size()-1).setTextFill(Color.WHITE);
        	P1MT.get(P1M.size()-1).setPrefSize(ImgMSize-10, 20);
        }

        VBox P1MonsterBox = new VBox(P1M.get(0), P1MT.get(0),P1M.get(1),P1MT.get(1), P1M.get(2), P1MT.get(2));
//        VBox P1MonsterBox = new VBox(P1M.get(0) ,P1M.get(1), P1M.get(2));
        P1MonsterBox.setPrefSize(MSBoxW, MSBoxH);
        P1MonsterBox.setSpacing(MSBoxSp);
        P1MonsterBox.setAlignment(javafx.geometry.Pos.CENTER);

        HBox player1HBox = new HBox(P1SpellBox, P1MonsterBox);
        player1HBox.setPrefSize(PyBoxW, PyBoxH);
        player1HBox.setSpacing(PyBoxSp);
        player1HBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        System.out.println("hi2");
        
        // Player 2 spell and monster images
        for(int i=0; i<3; i++) {
        	System.out.println(GameLogic.getInstance().getPlayers().get(1).getMonHand().size());
        	if(GameLogic.getInstance().getPlayers().get(1).getMonHand().size()<=i) {
        		P2M.add(new ImageView(nocard));
        		P2MT.add(new Label(""));
        	}else{
        		P2M.add(new ImageView(GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i).getImg()));
        		if(((MonsterCard) GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i)).getStatus()) {
        			nStatus = "Atk : ";
        		}else {
        			nStatus = "Def : ";
        		}
        		P2MT.add(new Label(nStatus+Integer.toString(((MonsterCard) GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i)).getVal())));
        	}
        	P2M.get(i).setFitWidth(ImgGSize);
        	P2M.get(i).setFitHeight(ImgGSize);
        	int idx = i;
        	P2M.get(i).setOnMouseClicked(e -> FieldClicked(e, idx, 1, true));
        	P2MT.get(P2M.size()-1).setFont(new Font("Copperplate", 12));
        	P2MT.get(P2M.size()-1).setTextFill(Color.WHITE);
//        	P2MT.get(P2M.size()-1).setStyle("-fx-background-color: #ff0000;");  // Red background color
        	P2MT.get(P2M.size()-1).setPrefSize(ImgMSize-10, 20);
        }
        VBox P2MonsterBox = new VBox(P2M.get(0), P2MT.get(0),P2M.get(1),P2MT.get(1), P2M.get(2), P2MT.get(2));
//        VBox P2MonsterBox = new VBox(P2M.get(0), P2M.get(1), P2M.get(2));
        P2MonsterBox.setPrefSize(MSBoxW, MSBoxH);
        P2MonsterBox.setSpacing(MSBoxSp);
        P2MonsterBox.setAlignment(javafx.geometry.Pos.CENTER);

        for(int i=0; i<3; i++) {
        	System.out.println(GameLogic.getInstance().getPlayers().get(1).getSpHand().size());
        	if(GameLogic.getInstance().getPlayers().get(1).getSpHand().size()<=i) {
        		P2S.add(new ImageView(nocard));
        	}else P2S.add(new ImageView(GameLogic.getInstance().getPlayers().get(1).getSpHand().get(i).getImg()));
        	P2S.get(i).setFitWidth(ImgGSize);
        	P2S.get(i).setFitHeight(ImgGSize);
        	int idx = i;
        	P2S.get(i).setOnMouseClicked(e -> FieldClicked(e, idx, 1, false));
        }

        VBox P2SpellBox = new VBox(P2S.get(0), P2S.get(1), P2S.get(2));
        P2SpellBox.setPrefSize(MSBoxW, MSBoxH);
        P2SpellBox.setSpacing(10);
        P2SpellBox.setAlignment(javafx.geometry.Pos.CENTER);

        HBox player2HBox = new HBox(P2MonsterBox, P2SpellBox);
        player2HBox.setPrefSize(PyBoxW, PyBoxH);
        player2HBox.setSpacing(PyBoxSp);
        player2HBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Bottom HBox
        for(int i=0; i<3; i++) {
        	System.out.println(GameLogic.getInstance().getSpMarket().size());
        	if(GameLogic.getInstance().getSpMarket().size()<=i) {
        		SM.add(new ImageView(nocard));
        	}else SM.add(new ImageView(GameLogic.getInstance().getSpMarket().get(i).getImg()));
        	SM.get(i).setFitWidth(ImgMSize);
        	SM.get(i).setFitHeight(ImgMSize);
        	int idx = i;
        	SM.get(i).setOnMouseClicked(e -> SMClicked(e, idx));
        }
        
        HBox playersHBox = new HBox(player1HBox, player2HBox);
        playersHBox.setPrefSize(790, 550);
        playersHBox.setPadding(new Insets(0, 30, 0, 30));
        playersHBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        HBox SpMarHBox = new HBox(SM.get(0), SM.get(1), SM.get(2));
        SpMarHBox.setPrefSize(MKBoxW, MKBoxH);
        SpMarHBox.setSpacing(MKBoxSp);
        SpMarHBox.setPadding(new Insets(10, 0, 0, 0));
        SpMarHBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Spell Market Label
        Label SM_Text = new Label("Spell Market");
        SM_Text.setFont(new Font("Copperplate", 14));
        SM_Text.setTextFill(Color.WHITE);
        SM_Text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        SM_Text.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);

        HBox SM_TextHBox = new HBox(SM_Text);
        SM_TextHBox.setPrefSize(790, 25);
        SM_TextHBox.setPadding(new Insets(6, 0, 0, 0));
        SM_TextHBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        
//        playersHBox.setStyle("-fx-background-color: red");
        
        // Main VBox
        this.getChildren().addAll(MM_TextHBox, MonMarHBox, playersHBox, SpMarHBox, SM_TextHBox);
        this.setPrefSize(790, 720);
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(0);

//        Scene scene = new Scene(mainVBox, 790, 720);
    }

    
    
    
	/**
	 * A function called when the card in monster market is clicked , setting values, setting opacity and check if the card can be chosen
	 * @param e	a MouseEvent
	 * @param i	the index of monster card in the market that's clicked 
	 */
	private void MMClicked(MouseEvent e, int i) {
		this.setLastClick(GameLogic.getInstance().getMonMarket().get(i));
		((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
		if(i>=GameLogic.getInstance().getMonMarket().size()) return;
    	if(GameLogic.getInstance().getNowPhrase()!=1) return ;
    	if(GameLogic.getInstance().getnowClick().size()>=1) {
    		if(!GameLogic.getInstance().getnowClick().contains(this.getLastClick())) {
    			System.out.println("not contain");
    			if(GameLogic.getInstance().getnowClick().size()!=1){
    				System.out.println("hi 2");
    				return ;
    			}else {
    				System.out.println("1 left");
    				if(GameLogic.getInstance().getnowClick().get(0) instanceof MonsterCard) {
    					System.out.println("same type");
    	    			return ;
    	    		}
    				System.out.println("dif type");
    			}
    		}
    	}
        ImageView imageView = (ImageView) e.getTarget();
        double currentOpacity = imageView.getOpacity();

        // Check the current opacity and toggle it
        if (currentOpacity == 1.0) {
            imageView.setOpacity(0.5);
            GameLogic.getInstance().getnowClick().add(GameLogic.getInstance().getMonMarket().get(i));
            GameLogic.getInstance().setSumPrice(GameLogic.getInstance().getSumPrice()+GameLogic.getInstance().getMonMarket().get(i).getPrice());
            ((MonsterCard) GameLogic.getInstance().getMonMarket().get(i)).setStatus(true);
        	((ImageView) e.getTarget()).setImage(GameLogic.getInstance().getMonMarket().get(i).getImg());     
        } else if (((MonsterCard) GameLogic.getInstance().getMonMarket().get(i)).getStatus()==true){
        	((MonsterCard) GameLogic.getInstance().getMonMarket().get(i)).setStatus(false);
        	((ImageView) e.getTarget()).setImage(GameLogic.getInstance().getMonMarket().get(i).getImg());
        }else {
        	imageView.setOpacity(1.0);
            GameLogic.getInstance().getnowClick().remove(GameLogic.getInstance().getMonMarket().get(i));
            GameLogic.getInstance().setSumPrice(GameLogic.getInstance().getSumPrice()-GameLogic.getInstance().getMonMarket().get(i).getPrice());
        }
        ((RightGamePane) main.getGameRoot().getChildren().get(2)).updateRightPane();
        ((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
    }
    
	
	
	
    /**
     * A function called when the card in Spell market is clicked , setting values, setting opacity and check if the card can be chosen
     * @param e	a MouseEvent
     * @param i	the index of Spell card in the market that's clicked 
     */
    private void SMClicked(MouseEvent e, int i) {
    	this.setLastClick(GameLogic.getInstance().getSpMarket().get(i));
    	((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
    	if(i>=GameLogic.getInstance().getSpMarket().size()) return;
    	if(GameLogic.getInstance().getNowPhrase()!=1) return ;
    	if(GameLogic.getInstance().getnowClick().size()>=1) {
    		if(!GameLogic.getInstance().getnowClick().contains(GameLogic.getInstance().getSpMarket().get(i))) {
    			if(GameLogic.getInstance().getnowClick().size()!=1){
    				return ;
    			}else {
    				if(GameLogic.getInstance().getnowClick().get(0) instanceof SpellCard) {
    	    			return ;
    	    		}
    			}
    		}
    	}
    	ImageView imageView = (ImageView) e.getTarget();
        double currentOpacity = imageView.getOpacity();

        // Check the current opacity and toggle it
        if (currentOpacity == 1.0) {
            imageView.setOpacity(0.5);
            GameLogic.getInstance().getnowClick().add(GameLogic.getInstance().getSpMarket().get(i));
            GameLogic.getInstance().setSumPrice(GameLogic.getInstance().getSumPrice()+GameLogic.getInstance().getSpMarket().get(i).getPrice());
        } else {
            imageView.setOpacity(1.0);
            GameLogic.getInstance().getnowClick().remove(GameLogic.getInstance().getSpMarket().get(i));
            GameLogic.getInstance().setSumPrice(GameLogic.getInstance().getSumPrice()-GameLogic.getInstance().getSpMarket().get(i).getPrice());
        }
        ((RightGamePane) main.getGameRoot().getChildren().get(2)).updateRightPane();
        ((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
    }
    
    /**
     * Check if the card can be chose according to the effect strategy type of the card and the card chosen before
     * @param e	a MouseEvent
     * @param i	the index of the card in player p hand
     * @param p	player who own the clicked card
     * @param m set to true if clicked card is a monster card otherwise set to false
     * @return	true if the card can be clicked and false otherwise
     */
    private boolean checkEffCard(MouseEvent e, int i,int p,boolean m) {
    	if(GameLogic.getInstance().getnowClick().size()==0) {
    		return true;
    	}
    	//func EffType
    	int ty =GameLogic.getInstance().getnowClick().get(0).getEffType();
    	System.out.println(ty);
//    	int ty = 1;
    	//one mon
    	if(ty == 1) {
    		return false;
    	}
    	//one mon one mon
    	if(ty == 2) {
    		if(!m) return false;
    		if(GameLogic.getInstance().getCurrentPlayer()==p) return false;
    	}
    	//one spell
    	if(ty == 3) {
    		return false;
    	}
    	//one spell my mon
    	if(ty == 4) {
    		if(!m) return false;
    		if(GameLogic.getInstance().getCurrentPlayer()!=p) return false;
    	}
    	//one spell his mon
    	if(ty == 5) {
    		if(!m) return false;
    		System.out.println("monster check");
    		if(GameLogic.getInstance().getCurrentPlayer()==p) return false;
    		System.out.println("cur play check");
    	}
    	//one spell his spell
    	if(ty == 6) {
    		if(m) return false;
    		if(GameLogic.getInstance().getCurrentPlayer()==p) return false;
    	}
    	System.out.println("pass");
    	return true;
    }
    
    /**
     * A function called when the effect card in the field clicked , setting values, setting opacity and check if the card can be chosen
     * @param e	a MouseEvent
     * @param i	the index of the card in player p hand
     * @param p	player who own the clicked card
     * @param m set to true if clicked card is a monster card otherwise set to false
     */
    private void EffClicked(MouseEvent e, int i,int p,boolean m) {
    	if(GameLogic.getInstance().getNowPhrase()!=2) return ;
    	if(m && i>=GameLogic.getInstance().getPlayers().get(p).getMonHand().size()) return;
    	if(!m && i>=GameLogic.getInstance().getPlayers().get(p).getSpHand().size()) return;
    	if(GameLogic.getInstance().getnowClick().size()==0 && GameLogic.getInstance().getCurrentPlayer()!=p) {
    		System.out.println("select cur play eff");
    		return ;
    	}
    	BaseCard nowCard;
    	if(m) {
    		nowCard = GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i);
    	}else nowCard = GameLogic.getInstance().getPlayers().get(p).getSpHand().get(i);
    	System.out.println((GameLogic.getInstance().getnowClick().contains(nowCard))+"");
    	if(!(GameLogic.getInstance().getnowClick().contains(nowCard))) {
    		System.out.println("not contain eff checking");
    		if(!nowCard.isEffectable()) {
    			System.out.println("not Effectable");
    		}
    		if(!this.checkEffCard(e, i, p, m)) {
    			System.out.println("Wrong card pick");
    		}
    		if(!nowCard.isEffectable() || GameLogic.getInstance().getnowClick().size()>=2 || !this.checkEffCard(e, i, p, m)) return ;
    	}
        ImageView imageView = (ImageView) e.getTarget();
        double currentOpacity = imageView.getOpacity();

        // Check the current opacity and toggle it
        if (currentOpacity == 1.0) {
            imageView.setOpacity(0.5);
            GameLogic.getInstance().getnowClick().add(nowCard);
        } else {
            imageView.setOpacity(1.0);
            GameLogic.getInstance().getnowClick().remove(nowCard);
        }
    }
    /**
      * A function called when the card in the field clicked, it will call other functions such as AtkClicked, EffClicked, etc.
     * @param e	a MouseEvent
     * @param i	the index of the card in player p hand
     * @param p	player who own the clicked card
     * @param m set to true if clicked card is a monster card otherwise set to false
     */
    private void FieldClicked(MouseEvent e, int i, int p, boolean m) {
    	if(m && i>=GameLogic.getInstance().getPlayers().get(p).getMonHand().size()) return;
    	if(!m && i>=GameLogic.getInstance().getPlayers().get(p).getSpHand().size()) return;
    	if(m) this.setLastClick(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i));
    	else this.setLastClick(GameLogic.getInstance().getPlayers().get(p).getSpHand().get(i));
    	((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
    	System.out.print("Field");
    	System.out.println(p);
    	if(m) {
    		UpdStatus(e, i, p);
    		AtkClicked(e, i, p);
        	EffClicked(e, i, p, true);
    	}else {
    		EffClicked(e, i, p, false);
    	}
    	((RightGamePane) main.getGameRoot().getChildren().get(2)).updateCardInfo();
    }
    
    /**
     * Update monster card image showed according to its status
     * @param e	a MouseEvent
     * @param i	the index of the card in player p hand
     * @param p	player who own the clicked card
     */
    private void UpdStatus(MouseEvent e, int i,int p) {
    	if(GameLogic.getInstance().getNowPhrase()!=1) return ;
    	if(GameLogic.getInstance().getCurrentPlayer()!=p) return;
        ImageView imageView = (ImageView) e.getTarget();

        // Check the current opacity and toggle it
        if (((MonsterCard) GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i)).getStatus() == false) {
        	((MonsterCard) GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i)).setStatus(true);
        	imageView.setImage(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i).getImg());     
        } else {
        	((MonsterCard) GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i)).setStatus(false);
        	imageView.setImage(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i).getImg());     
        }
    }
    
    /**
     * A function called when the effect card in the field clicked , setting values, setting opacity and check if the card can be chosen
     * @param e	a MouseEvent
     * @param i	the index of the card in player p hand
     * @param p	player who own the clicked card
     */
    private void AtkClicked(MouseEvent e, int i, int p) {
    	if(GameLogic.getInstance().getNowPhrase()!=3) return ;
    	if(GameLogic.getInstance().getnowClick().size()>=1) {
    		if(!GameLogic.getInstance().getnowClick().contains(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i))) {
    			System.out.println("not contain self");
    			if(GameLogic.getInstance().getnowClick().size()!=1){
    				System.out.println("2 mons chose");
    				return ;
    			}else {
    				if(GameLogic.getInstance().getnowClick().get(0).getOwner()==p) {
    					System.out.println("same player");
    					System.out.println(p);
    					System.out.println(GameLogic.getInstance().getnowClick().get(0).getOwner());
    	    			return ;
    	    		}
    			}
    		}
    	}
        ImageView imageView = (ImageView) e.getTarget();
        double currentOpacity = imageView.getOpacity();

        // Check the current opacity and toggle it
        if (currentOpacity == 1.0) {
	            imageView.setOpacity(0.5);
		        GameLogic.getInstance().getnowClick().add(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i));
            } else {
	            imageView.setOpacity(1.0);
		        GameLogic.getInstance().getnowClick().remove(GameLogic.getInstance().getPlayers().get(p).getMonHand().get(i));
            }
	}
    
    /**
     * Update card field images and texts
     */
    public void updateField() {
    	String nStatus;
        // Update Monster Market images
        for (int i = 0; i < MM.size(); i++) {
            if (GameLogic.getInstance().getMonMarket().size() <= i) {
                MM.get(i).setImage(nocard);
            } else {
                MM.get(i).setImage(GameLogic.getInstance().getMonMarket().get(i).getImg());
            }
            MM.get(i).setOpacity(1);
        }

        // Update Player 1 spell images
        for (int i = 0; i < P1S.size(); i++) {
            if (GameLogic.getInstance().getPlayers().get(0).getSpHand().size() <= i) {
                P1S.get(i).setImage(nocard);
            } else {
                P1S.get(i).setImage(GameLogic.getInstance().getPlayers().get(0).getSpHand().get(i).getImg());
            }
            P1S.get(i).setOpacity(1);
        }

        // Update Player 1 monster images
        for (int i = 0; i < P1M.size(); i++) {
            if (GameLogic.getInstance().getPlayers().get(0).getMonHand().size() <= i) {
                P1M.get(i).setImage(nocard);
                P1MT.get(i).setText("");
            } else {
                P1M.get(i).setImage(GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i).getImg());
                if(((MonsterCard) GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i)).getStatus()) {
        			nStatus = "Atk : ";
        		}else {
        			nStatus = "Def : ";
        		}
        		P1MT.get(i).setText(nStatus+Integer.toString(((MonsterCard) GameLogic.getInstance().getPlayers().get(0).getMonHand().get(i)).getVal()));
        	}
            P1M.get(i).setOpacity(1);
        }

        // Update Player 2 spell images
        for (int i = 0; i < P2S.size(); i++) {
            if (GameLogic.getInstance().getPlayers().get(1).getSpHand().size() <= i) {
                P2S.get(i).setImage(nocard);
            } else {
                P2S.get(i).setImage(GameLogic.getInstance().getPlayers().get(1).getSpHand().get(i).getImg());
            }
            P2S.get(i).setOpacity(1);
        }

        // Update Player 2 monster images
        for (int i = 0; i < P2M.size(); i++) {
            if (GameLogic.getInstance().getPlayers().get(1).getMonHand().size() <= i) {
                P2M.get(i).setImage(nocard);
                P2MT.get(i).setText("");
            } else {
                P2M.get(i).setImage(GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i).getImg());
                if(((MonsterCard) GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i)).getStatus()) {
        			nStatus = "Atk : ";
        		}else {
        			nStatus = "Def : ";
        		}
        		P2MT.get(i).setText(nStatus+Integer.toString(((MonsterCard) GameLogic.getInstance().getPlayers().get(1).getMonHand().get(i)).getVal()));
        	}
            P2M.get(i).setOpacity(1);
        }

        // Update Spell Market images
        for (int i = 0; i < SM.size(); i++) {
            if (GameLogic.getInstance().getSpMarket().size() <= i) {
                SM.get(i).setImage(nocard);
            } else {
                SM.get(i).setImage(GameLogic.getInstance().getSpMarket().get(i).getImg());
            }
            SM.get(i).setOpacity(1);
        }
    }
    
    /**
     * Getter for lastClick
     * @return lastClick
     */
    public BaseCard getLastClick() {
		return lastClick;
	}




	/**
	 * Setter for lastClick
	 * @param lastClick
	 */
	public void setLastClick(BaseCard lastClick) {
		this.lastClick = lastClick;
	}


}
