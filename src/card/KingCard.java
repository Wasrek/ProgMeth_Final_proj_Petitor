package card;

import application.main;
import gui.GameFieldPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public class KingCard extends MonsterCard {
	private GameLogic game = GameLogic.getInstance();

	public KingCard() {
		super(CardName.KING, 10, 2100, 1600, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Brainwash";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "King";
	}

	@Override
	public void attack(BaseCard card) {
		// TODO Auto-generated method stub
		if (this.isAttackable(card)) {
			game.updAtkani("Monster attacked");
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
					game.updProtectani();
				}else {
					game.getOpp().getMonHand().remove(card);
				}
			}
		}
	}

	@Override
	public boolean isAttackable(BaseCard card) {
		// TODO Auto-generated method stub
		if (this.getStatus() && (this.getSummonedTurn() != game.getTurnCount()) && (this.getLastAtk() != game.getTurnCount())) {
			if (game.getTurnCount() >= this.getLastAtk() + 4) {
				return true;						
			}			
		}
		return false;
	}

	@Override
	public boolean isGuardable(BaseCard card) {
		// TODO Auto-generated method stub
		if (this.getDefVal() >= ((MonsterCard) card).getAtkVal()) {
			return true;
		}
		return false;	
	}

	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			System.out.println(this.toString() + this.performEffect());
			game.updUseEffani(this.performEffect());
			this.setLastUsedTurn(game.getTurnCount());
			game.getCur().getMonHand().add(game.getnowClick().get(1));
			((MonsterCard) game.getnowClick().get(1)).setSummonedTurn(GameLogic.getInstance().getTurnCount());
			game.getOpp().getMonHand().remove(game.getnowClick().get(1));
		}
	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		if (game.getTurnCount() != this.getLastUsedTurn()) {
			if ((game.getCur().getMonHand().size() < game.getOpp().getMonHand().size()) && (game.getCur().getMonHand().size() < 3)) {
				return true;			
			}
		}
		return false;
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/KingAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/KingDef.png")));
	}

}
