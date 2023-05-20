package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public class GolemCard extends MonsterCard {
	private GameLogic game = GameLogic.getInstance();

	public GolemCard() {
		super(CardName.GOLEM, 4, 800, 1300, true);
		this.setEffType(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Mha Mhoo";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Golem";
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
					System.out.println("win");
					game.getOpp().setHp(game.getOpp().getHp() - difAtk);
					game.getOpp().getMonHand().remove(card);
				}else if (difAtk < 0) {
					System.out.println("lose");
					game.getCur().setHp(game.getCur().getHp() + difAtk);
					game.getCur().getMonHand().remove(this);
				}else {
					System.out.println("draw");
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
		if(!this.getStatus()) {
			game.updTextani("Not attacker!");
		}else if(this.getSummonedTurn() == game.getTurnCount()) {
			game.updTextani("Try next turn");
		}else if(this.getLastAtk() == game.getTurnCount()){
			game.updTextani("Already attack!");
		}
		return (this.getStatus() && (this.getSummonedTurn() != game.getTurnCount()) && (this.getLastAtk() != game.getTurnCount()) );
	}

	@Override
	public boolean isGuardable(BaseCard card) {
		// TODO Auto-generated method stub
		return ((this.getDefVal() >= ((MonsterCard) card).getAtkVal()));
	}

	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (isEffectable()) {
			System.out.println(this.toString() + this.performEffect());
			game.updUseEffani(this.performEffect());
			this.setLastUsedTurn(game.getTurnCount());
			for (BaseCard e: game.getCur().getMonHand()) {
				if (e != this) {
					((MonsterCard) e).setDefVal(((MonsterCard) e).getDefVal() + 300);
				}
			}
		}
	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		return (game.getTurnCount() != this.getLastUsedTurn());
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/GolemAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/GolemDef.png")));
	}

}
