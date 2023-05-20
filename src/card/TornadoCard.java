package card;

import javafx.scene.image.Image;
import logic.CardName;

public class TornadoCard extends SpellCard{

	public TornadoCard() {
		super(CardName.TORNADO, 6);
		this.setEffType(6);
		// TODO Auto-generated constructor stub
	}

	// fix
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			game.updUseEffani("Tornado! (Destroy enemy spell)");
			game.getOpp().getSpHand().remove(game.getnowClick().get(1));
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
		return "Tornado";
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		return (new Image(getClass().getResourceAsStream("../img/Tornado.jpg")));
	}
}
