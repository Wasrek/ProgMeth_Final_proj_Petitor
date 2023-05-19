package gui;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;

public class AnimatePane extends StackPane{
	public AnimatePane(){
		super();
//		this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(20), getInsets())));
		this.setMinHeight(180);
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(20,0,0,0));
//		vBox.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), getInsets())));
		
		String imgpath = "../gif/Loading.gif";
		Image image = new Image(getClass().getResourceAsStream(imgpath));;
		ImageView ani = new ImageView(image);
		ani.setFitHeight(70);
		ani.setPreserveRatio(true);
		
		String Noti = "Buying Phrase";
		Label NotiT = new Label(Noti);
	    NotiT.setFont(new Font("Copperplate", 20));
	    NotiT.setPrefSize(250, 50);
	    NotiT.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
	    vBox.getChildren().addAll(ani, NotiT);
	    vBox.setAlignment(javafx.geometry.Pos.CENTER);
	    this.getChildren().add(vBox);
	    this.setAlignment(javafx.geometry.Pos.CENTER);
	}
	
	public void setnormal() {
		String nmtext;
		if(GameLogic.getInstance().getNowPhrase()==1) {
			nmtext = "Buying Phrase";
		}else if(GameLogic.getInstance().getNowPhrase()==2) {
			nmtext = "Effects Phrase";
		}else {
			nmtext = "Attack Phrase";
		}
		((Labeled) this.getvBox().getChildren().get(1)).setText(nmtext);
		String imgpath = "../gif/Loading.gif";
		((ImageView) this.getvBox().getChildren().get(0)).setImage(new Image(getClass().getResourceAsStream(imgpath)));
		((ImageView) this.getvBox().getChildren().get(0)).setFitHeight(70);
		((ImageView) this.getvBox().getChildren().get(0)).setPreserveRatio(true);
		Platform.runLater(
				  () -> {
						FadeTransition fd = new FadeTransition();  
						
						//set the duration for the Fade transition   
						fd.setDuration(Duration.millis(1000));   
						fd.setFromValue(0);  
						fd.setToValue(1);  
						//set the cycle count for the Fade transition   
//			        	    fd.setCycleCount(3);  
						  
						//the transition will set to be auto reversed by setting this to true   
						//set Circle as the node onto which the transition will be applied  
						fd.setNode(this);  
						fd.play();
				  }
				);
	}
	
	public void setupdate(String str, String gifp) {
		String anitext = str;
		((Labeled) this.getvBox().getChildren().get(1)).setText(anitext);
		((ImageView) this.getvBox().getChildren().get(0)).setImage(new Image(getClass().getResourceAsStream(gifp)));
	}
	private VBox getvBox() {
		return (VBox) this.getChildren().get(0);
	}
}