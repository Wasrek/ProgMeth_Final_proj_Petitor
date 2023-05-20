package card;

import javafx.scene.image.Image;
import logic.CardName;

public class BlackholeCard extends SpellCard{

	public BlackholeCard() {
		super(CardName.BLACKHOLE, 8);
		this.setEffType(5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Blackhole!");
			game.getOpp().getMonHand().remove(game.getnowClick().get(1));
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
		return "Blackhole";
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Blackhole.jpg")));
	}
}
