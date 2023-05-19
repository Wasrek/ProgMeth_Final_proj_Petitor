package gui;

import javafx.scene.control.Button;

public class SetButton extends Button{
	private String nameButton;
	private double height, width;
	
	public SetButton(String name, double height, double width) {
		// TODO Auto-generated constructor stub
		super();
		this.setText(name);
		this.setHeight(height);
		this.setWidth(width);
	}

	public String getNameButton() {
		return nameButton;
	}

	public void setNameButton(String nameButton) {
		this.nameButton = nameButton;
	}

	public double getHeightButton() {
		return height;
	}

	public void setHeightButton(int height) {
		this.height = height;
	}

	public double getWidthButton() {
		return width;
	}

	public void setWidthButton(int width) {
		this.width = width;
	}
	
	
}
