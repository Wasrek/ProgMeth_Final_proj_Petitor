package card;

import java.util.ArrayList;

/**
 * interface for the player to buy the cards from the market in buy phrase
 * @author Wishmeluck
 *
 */
public interface Buyable {
	/**
	 * Buy card function
	 * @param buyCards	cards to buy
	 */
	void buy(ArrayList<BaseCard> buyCards);
	/**
	 * check if this can buy cards
	 * @param buyCards	card to buy
	 * @return	true if can buy the card, false otherwise.
	 */
	boolean isBuyable(ArrayList<BaseCard> buyCards);
}