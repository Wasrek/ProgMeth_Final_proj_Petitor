package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;

//You CAN modify the first line
public abstract class BaseCard implements UseEffectAble{
    // TODO Implement here
	private CardType type;
	private CardName name;
	private int price;
	private int owner;	
	private int effType;
	
	public BaseCard(CardType type, CardName name, int price, int effType) {
		setType(type);
		setName(name);
		setPrice(price);
		setEffType(effType);
	}
	
	public abstract String toString();
	public abstract Image getImg();
	
	public CardType getType() {
		return type;
	}
	public void setType(CardType type) {
		this.type = type;
	}
	
	public CardName getName() {
		return name;
	}
	public void setName(CardName name) {
		this.name = name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getPrice() {
		return this.price;
	}
	
	public int getEffType() {
		return effType;
	}
	
	public void setEffType(int effType) {
		this.effType = effType;
	}
}
