package card;

import java.util.ArrayList;

public interface Buyable {
	void buy(ArrayList<BaseCard> buyCards);
	boolean isBuyable(ArrayList<BaseCard> buyCards);
}
