package card;

import javafx.scene.image.Image;
import logic.CardName;

/**
 * @author Wishmeluck
 *
 */
public class VitaminCard extends SpellCard{
	/**
	 * Constructor for Vitamin card
	 */
	public VitaminCard() {
		super(CardName.VITAMIN, 4);
		this.setEffType(4);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *	Method use to call effect
	 */
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Vitamin! (Power-up target)");
			BaseCard effectedCard = game.getnowClick().get(1);
			((MonsterCard) effectedCard).setAtkVal(((MonsterCard) effectedCard).getAtkVal() + 300);
			game.getCur().getSpHand().remove(this);
		}
	}

	/**
	 *@return boolean that this spell can use effect or not
	 */
	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 	@return	String of card's name
	 */
	@Override
	public String toString() {
		return "Vitamin";
	}
	
	/**
	 *	@return	spell's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Vitamin.jpg")));
	}
	
}
