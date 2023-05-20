package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;

/**
 * The base abstract class of all cards
 * @author Wishmeluck
 *
 */
public abstract class BaseCard implements UseEffectAble{
	/**
	 * Card's type (Monster or Spell)
	 */
	private CardType type;
	
	/**
	 * Card's name
	 */
	private CardName name;
	/**
	 * Card's price
	 */
	private int price;
	/**
	 * Card's owner (player)
	 */
	private int owner;	
	
	/**
	 * Card's effect strategy type (represents the type of card it could use effect with)
	 */
	private int effType;
	
	/**
	 * Constructor for BaseCard
	 * @param type	Card type 
	 * @param name	Card's name
	 * @param price	price of the card
	 * @param effType	Card's effect type
	 */
	public BaseCard(CardType type, CardName name, int price, int effType) {
		setType(type);
		setName(name);
		setPrice(price);
		setEffType(effType);
	}
	
	/**
	 *Abstract method to get string represents that card
	 */
	public abstract String toString();
	
	/**
	 * Abstract method to get images represent the card
	 * @return Image of the card
	 */
	public abstract Image getImg();
	
	/**
	 * Getter for type 
	 * @return 
	 */
	public CardType getType() {
		return type;
	}
	/**
	 * Setter for type
	 * @param type	CardType to be set
	 */
	public void setType(CardType type) {
		this.type = type;
	}
	
	/**
	 * Getter for name
	 * @return name
	 */
	public CardName getName() {
		return name;
	}
	/**
	 * Setter for name
	 * @param name	card's name
	 */
	public void setName(CardName name) {
		this.name = name;
	}
	
	/**
	 * Setter for price
	 * @param price	card's price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Getter for owner
	 * @return	Card's owner
	 */
	public int getOwner() {
		return owner;
	}
	
	/**
	 * Setter for owner
	 * @param owner	Card's owner
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * Getter for price
	 * @return	Card's price
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * Getter for card's effect type
	 * @return Card's effect type
	 */
	public int getEffType() {
		return effType;
	}
	
	/**
	 * Setter for card's effect type 
	 * @param effType
	 */
	public void setEffType(int effType) {
		this.effType = effType;
	}
}