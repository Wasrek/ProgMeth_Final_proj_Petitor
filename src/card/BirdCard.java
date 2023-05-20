package card;

import java.util.Random;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

public class BirdCard extends MonsterCard {
	private GameLogic game = GameLogic.getInstance();
	private int ranNum;
	
	
	public BirdCard() {
		super(CardName.BIRD, 2, 800, 800, true);
		this.setEffType(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Nok Sorn Khon";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Vihok";
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
					System.out.println(this.toString() + "win");
					game.getOpp().setHp(game.getOpp().getHp() - difAtk);
					game.getOpp().getMonHand().remove(card);
				}else if (difAtk < 0) {
					System.out.println(this.toString() + "lose");
					game.getCur().setHp(game.getCur().getHp() + difAtk);
					game.getCur().getMonHand().remove(this); 
				}else {
					System.out.println(this.toString() + "draw" + game.getOpp().toString() + game.getCur().toString());
					System.out.println(game.getOpp().getMonHand().contains(card) +"l");
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
		ranNum = new Random().nextInt(100);
		if (this.isEffectable()) {
			System.out.println(this.toString() + this.performEffect() + this.getLastUsedTurn());
			this.setLastUsedTurn(game.getTurnCount());
			System.out.println(this.toString() + this.performEffect() + this.getLastUsedTurn());
			if (ranNum > 50) {
				this.setAtkVal(this.getAtkVal() + 200);
				game.updUseEffani(this.performEffect()+" Success!");
			}else {
				game.updUseEffani(this.performEffect()+" Bad luck TT");
			}
		}
	}

	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		return (game.getTurnCount() != this.getLastUsedTurn());
	}

	@Override
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/BirdAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/BirdDef.png")));
	}

}
