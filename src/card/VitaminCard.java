package card;

import javafx.scene.image.Image;
import logic.CardName;

public class VitaminCard extends SpellCard{
	public VitaminCard() {
		super(CardName.VITAMIN, 4);
		this.setEffType(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	// fix input in Main
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Vitamin! (Power-up target)");
			BaseCard effectedCard = game.getnowClick().get(1);
			((MonsterCard) effectedCard).setAtkVal(((MonsterCard) effectedCard).getAtkVal() + 300);
			game.getCur().getSpHand().remove(this);
		}
	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "Vitamin";
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Vitamin.jpg")));
	}
	
}
