package gui;

import card.BaseCard;
import card.MonsterCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class InfoPane extends VBox{
	
	public InfoPane() {
		// TODO Auto-generated constructor stub
		super();
		this.setWidth(300);
		this.setMaxHeight(560);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.BASELINE_LEFT);
     
        Label nameLB = new Label("Name :");
        Label priceLB = new Label("Price :");
        Label atkLB = new Label("Atk :");
        Label defLB = new Label("Def :");
        Label effLB = new Label("Eff :");
        Image initimg = new Image(getClass().getResourceAsStream("../gif/yellow.gif"));
        ImageView img = new ImageView(initimg);
        img.setFitHeight(256);
        img.setFitWidth(256);
        img.setPreserveRatio(true);
        
        this.getChildren().addAll(img, nameLB, priceLB, atkLB, defLB, effLB);
	}
	
	
	public void rnImgLB(Image image) {
		((ImageView) this.getChildren().get(0)).setImage(image);
	}
	public void rnNameLB(String Text) {
		((Label) this.getChildren().get(1)).setText(Text);
	}
	public void rnPriceLB(String Text) {
		((Label) this.getChildren().get(2)).setText(Text);
	}
	public void rnAtkLB(String Text) {
		((Label) this.getChildren().get(3)).setText(Text);
	}
	public void rnDefLB(String Text) {
		((Label) this.getChildren().get(4)).setText(Text);
	}
	public void rnEffLB(String Text) {
		((Label) this.getChildren().get(5)).setText(Text);
	}
	
}