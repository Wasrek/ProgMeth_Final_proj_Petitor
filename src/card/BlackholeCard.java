package card;

import javafx.scene.image.Image;
import logic.CardName;

/**
 * One type of spell card
 * @author Wishmeluck
 *
 */
public class BlackholeCard extends SpellCard{

	/**
	 * Constructor for Blackhole
	 */
	public BlackholeCard() {
		super(CardName.BLACKHOLE, 8);
		this.setEffType(5);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *	Method use to call effect
	 */
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Blackhole! (Destroy enemy monster)");
			game.getOpp().getMonHand().remove(game.getnowClick().get(1));
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
		return "Blackhole";
	}
	
	/**
	 *	@return	spell's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Blackhole.jpg")));
	}
}