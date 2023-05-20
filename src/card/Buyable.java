package card;

import java.util.ArrayList;

/**
 * interface for the player to buy the cards from the market in buy phrase
 * @author Wishmeluck
 *
 */
public interface Buyable {
	void buy(ArrayList<BaseCard> buyCards);
	boolean isBuyable(ArrayList<BaseCard> buyCards);
}
