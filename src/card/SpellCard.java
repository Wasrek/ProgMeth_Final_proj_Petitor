package card;

import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public abstract class SpellCard extends BaseCard {
	
	GameLogic game = GameLogic.getInstance();

	public SpellCard(CardName name, int price) {
		super(CardType.SPELL, name, price,0);
		// TODO Auto-generated constructor stub
	}
	//useable status, effect

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
