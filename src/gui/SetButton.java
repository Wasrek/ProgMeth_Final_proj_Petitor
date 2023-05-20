package gui;

import javafx.scene.control.Button;

/**
 * A button style using in the game pausePane
 * @author Wishmeluck
 *
 */
public class SetButton extends Button{
	/**
	 * Button's name
	 */
	private String nameButton;
	/**
	 * Button's height
	 */
	/**
	 * Button's width
	 */
	private double height, width;
	
	/**
	 * 
	 * Constructor for setButton
	 * @param name	Button's name
	 * @param height	Button's height
	 * @param width		Button's width
	 */
	public SetButton(String name, double height, double width) {
		// TODO Auto-generated constructor stub
		super();
		this.setText(name);
		this.setHeight(height);
		this.setWidth(width);
	}

	/**
	 * Getter for button's name
	 * @return	nameButton
	 */
	public String getNameButton() {
		return nameButton;
	}

	/**
	 * Setter for button's name
	 * @param nameButton
	 */
	public void setNameButton(String nameButton) {
		this.nameButton = nameButton;
	}

	/**
	 * Getter for button's height
	 * @return	height
	 */
	public double getHeightButton() {
		return height;
	}

	/**
	 * Setter for button's height
	 * @param height
	 */
	public void setHeightButton(int height) {
		this.height = height;
	}

	/**
	 * Getter for button's width
	 * @return	width
	 */
	public double getWidthButton() {
		return width;
	}

	/**
	 * Setter for button's width
	 * @param width
	 */
	public void setWidthButton(int width) {
		this.width = width;
	}
	
	
}
