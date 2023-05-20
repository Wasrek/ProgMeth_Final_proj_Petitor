package card;

import javafx.scene.image.Image;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

/**
 * @author Wishmeluck
 *
 */
public class DuckCard extends MonsterCard {
	/**
	 * Shorten the code
	 */
	private GameLogic game = GameLogic.getInstance();

	/**
	 * Constructor for DuckCard
	 */
	public DuckCard() {
		super(CardName.DUCK, 8, 1600, 1000, true);
		this.setEffType(1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return	effect's detail in String
	 */
	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Sacred Wave (Power-up team attack)";
	}
	
	/**
	 * 	@return	String of effect's name
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Garbbi";
	}
	
	/**
	 *	Method to attack other monsters
	 *	@param	card card to be attacked
	 */
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
	
	/**
	 * @param card	card to be checked
	 * @return	boolean that card can be attacked or not
	 */
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
	
	/**
	 * @param 	card	card to be checked
	 *	@return	boolean that this monster can guard card or not.
	 */
	@Override
	public boolean isGuardable(BaseCard card) {
		// TODO Auto-generated method stub
		return ((this.getDefVal() >= ((MonsterCard) card).getAtkVal()));
	}
	
	/**
	 *	Method use to call effect
	 */
	@Override
	public void useEffect() {
		// TODO Auto-generated method stub
		if (this.isEffectable()) {
			System.out.println(this.toString() + this.performEffect());
			game.updUseEffani(this.performEffect());
			this.setLastUsedTurn(game.getTurnCount());
			for(BaseCard e: game.getCur().getMonHand()) {
				((MonsterCard) e).setAtkVal(((MonsterCard) e).getAtkVal() + 300);
			}
		}

	}

	/**
	 *@return boolean that this monster can use effect or not
	 */
	@Override
	public boolean isEffectable() {
		// TODO Auto-generated method stub
		if (game.getTurnCount() != this.getLastUsedTurn()) {
			if (game.getCur().getHp() < game.getOpp().getHp()) {
				return true;			
			}
		}
		return false;
	}

	/**
	 *	@return	monster's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/DuckAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/DuckDef.png")));
	}
}