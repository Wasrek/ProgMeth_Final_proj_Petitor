package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public class DuckCard extends MonsterCard {
	private GameLogic game = GameLogic.getInstance();

	public DuckCard() {
		super(CardName.DUCK, 8, 1600, 1000, true);
		this.setEffType(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Sacred Wave";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Garbbi";
	}

	@Override
	public void attack(BaseCard card) {
		// TODO Auto-generated method stub
		if (this.isAttackable(card)) {
			this.setLastAtk(game.getTurnCount());
			System.out.println(this.toString() + "Attack");
			if (((MonsterCard) card).getStatus()) {
				int difAtk = this.getAtkVal() - ((MonsterCard) card).getAtkVal();
				if (difAtk > 0) {
					game.getOpp().setHp(game.getOpp().getHp() - difAtk);
					game.getOpp().getMonHand().remove(card);
				}else if (difAtk < 0) {
					game.getCur().setHp(game.getCur().getHp() + difAtk);
					game.getCur().getMonHand().remove(this);
				}else {
					game.getOpp().getMonHand().remove(card);
					game.getCur().getMonHand().remove(this);
				}
			}else {
				int diff = this.getAtkVal() - ((MonsterCard) card).getDefVal();
				if (((MonsterCard) card).isGuardable(this)) {
					game.getCur().setHp(game.getCur().getHp() + diff);
				}else {
					game.getOpp().getMonHand().remove(card);
				}
			}
		}
	}

	@Override
	public boolean isAttackable(BaseCard card) {
		// TODO Auto-generated method stub
		return (this.getStatus() && (this.getLastAtk() != game.getTurnCount()));
	}

	@Override
	public boolean isGuardable(BaseCard card) {
		// TODO Auto-generated method stub
		return ((this.getDefVal() >= ((MonsterCard) card).getAtkVal()));
	}
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			System.out.println(this.toString() + this.performEffect());
			this.setLastUsedTurn(game.getTurnCount());
			for(BaseCard e: game.getCur().getMonHand()) {
				((MonsterCard) e).setAtkVal(((MonsterCard) e).getAtkVal() + 300);
			}
		}

	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		if (game.getTurnCount() != this.getLastUsedTurn()) {
			if (game.getCur().getHp() > game.getOpp().getHp()) {
				return true;			
			}
		}
		return false;
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/DuckAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/DuckDef.png")));
	}
}
