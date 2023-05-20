package card;

import java.util.Collections;

import javafx.scene.image.Image;
import logic.CardName;

/**
 * @author Wishmeluck
 *
 */
public class NewWorldCard extends SpellCard{

	/**
	 * Constructor for NewWorldCard
	 */
	public NewWorldCard() {
		super(CardName.NWORLD, 2);
		this.setEffType(3);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 	@return	String of card's name
	 */
	@Override
	public String toString() {
		return "New World (Clear market)";
	}
	
	/**
	 *	Method use to call effect
	 */
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("New World!");
			game.getMonDeck().addAll(game.getMonMarket());
			game.getSpDeck().addAll(game.getSpMarket());
	        
	        game.getMonMarket().clear();
	        game.getSpMarket().clear();
	        
	        Collections.shuffle(game.getMonDeck());
	        Collections.shuffle(game.getSpDeck());
	        
	        for (int i = 0 ; i < 3 ;i++) {
	        	BaseCard card = game.getMonDeck().remove(0);
	        	game.getMonMarket().add(card);
	        }
	        
	        for (int i = 0 ; i < 3 ;i++) {
	        	BaseCard card = game.getSpDeck().remove(0);
	        	game.getSpMarket().add(card);
	        }
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
	 *	@return	spell's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/NewWorld.png")));
	}
}