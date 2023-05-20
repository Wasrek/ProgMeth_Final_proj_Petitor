package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A button for using in the main menu
 */
public class MainButton extends Button {
	/**
	 * Button's name
	 */
	private String buttonName;

	/**
	 * MenuBpane Main Constructor
	 * 
	 * @param buttonName button's name
	 */
	public MainButton(String buttonName) {
		this.setButtonName(buttonName);
		this.setText(buttonName);
		this.setPrefWidth(300);
		this.setPrefHeight(40);
		this.setFont(Font.font("Verdana", 30));
		this.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(20), getInsets())));
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				hover(true);
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				hover(false);
			}

		});
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(17), new BorderWidths(3))));
	}

	/**
	 * MenuBpane Main Constructor
	 * 
	 * @param hoveringStatus hovering status
	 */
	public void hover(boolean hoveringStatus) {
		if (hoveringStatus) {
			this.setStyle("-fx-text-fill: gray;");
		} else {
			this.setStyle("");
		}
	}

	/**
	 * Getter for button's name
	 * 
	 * @return button's name
	 */
	public String getButtonName() {
		return buttonName;
	}

	/**
	 * Setter for button's name
	 * 
	 * @param buttonName button's name to be set
	 */
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
}