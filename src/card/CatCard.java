package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public class CatCard extends MonsterCard {
	private GameLogic game = GameLogic.getInstance();

	public CatCard() {
		super(CardName.CAT, 6, 1300, 800, true);
		this.setEffType(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Death Claw";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Fire";
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
			game.getCur().setMoney(game.getCur().getMoney() + 2);
		}
	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		if (game.getTurnCount() != this.getLastUsedTurn()) return true;
		return true;
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
					System.out.println(GameLogic.getInstance().getNowPhrase());
					System.out.println("win");
					game.getOpp().setHp(game.getOpp().getHp() - difAtk);
					game.getOpp().getMonHand().remove(card);
					this.useEffect();
				}else if (difAtk < 0) {
					System.out.println("lose");
					game.getCur().setHp(game.getCur().getHp() + difAtk);
					game.getCur().getMonHand().remove(this);
				}else {
					System.out.println("draw");
					game.getOpp().getMonHand().remove(card);
					game.getCur().getMonHand().remove(this);
					this.useEffect();
				}
			}else {
				int diff = this.getAtkVal() - ((MonsterCard) card).getDefVal();
				if (((MonsterCard) card).isGuardable(this)) {
					game.getCur().setHp(game.getCur().getHp() + diff);
				}else {
					game.getOpp().getMonHand().remove(card);
					this.useEffect();
				}
			}
		}
	}

	@Override
	public boolean isAttackable(BaseCard card) {
		// TODO Auto-generated method stub
		return (this.getStatus() && (this.getLastAtk() != game.getTurnCount()));
	}
	
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/CatAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/CatDef.png")));
	}
}
