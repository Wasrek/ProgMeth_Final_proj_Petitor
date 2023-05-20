package card;

import logic.CardName;
import logic.CardType;
import logic.GameLogic;

/**
 * One type of normal base card
 * @author Wishmeluck
 *
 */
public abstract class SpellCard extends BaseCard {
	
	/**
	 * Shorten code
	 */
	GameLogic game = GameLogic.getInstance();

	/**
	 * Constructor for Spell card
	 * @param name	card's name
	 * @param price	card's price
	 */
	public SpellCard(CardName name, int price) {
		super(CardType.SPELL, name, price,0);
		// TODO Auto-generated constructor stub
	}

	/**
	 *	@return	nothing for spell card
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}