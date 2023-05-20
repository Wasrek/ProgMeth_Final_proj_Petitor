package card;

import javafx.scene.image.Image;
import logic.CardName;

/**
 * One type of spell card
 * @author Wishmeluck
 *
 */
public class TornadoCard extends SpellCard{

	/**
	 * Constructor for Tornado card
	 */
	public TornadoCard() {
		super(CardName.TORNADO, 6);
		this.setEffType(6);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *	Method use to call effect
	 */
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Tornado! (Destroy enemy spell)");
			game.getOpp().getSpHand().remove(game.getnowClick().get(1));
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
		return "Tornado";
	}
	
	/**
	 *	@return	spell's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Tornado.jpg")));
	}
}