package card;

import application.main;
import gui.GameFieldPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import logic.CardName;
import logic.CardType;
import logic.GameLogic;

/**
 * One type of monster card
 * @author Wishmeluck
 *
 */
public class KingCard extends MonsterCard {
	/**
	 * 	get game instance
	 */
	private GameLogic game = GameLogic.getInstance();

	/**
	 * Constructor for KingCard
	 */
	public KingCard() {
		super(CardName.KING, 10, 2100, 1600, true);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return	effect's detail in String
	 */
	@Override
	public String performEffect() {
		// TODO Auto-generated method stub
		return "Brainwash (Control enemy monster)";
	}
	
	/**
	 * 	@return	String of effect's name
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "King";
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
		}else if(game.getTurnCount() < this.getLastAtk() + 4) {
			game.updTextani("King's sleepy;-;");
		}
		if (this.getStatus() && (this.getSummonedTurn() != game.getTurnCount()) && (this.getLastAtk() != game.getTurnCount())) {
			if (game.getTurnCount() >= this.getLastAtk() + 4) {
				return true;						
			}			
		}
		return false;
	}
	
	/**
	 * @param 	card	card to be checked
	 *	@return	boolean that this monster can guard card or not.
	 */
	@Override
	public boolean isGuardable(BaseCard card) {
		// TODO Auto-generated method stub
		if (this.getDefVal() >= ((MonsterCard) card).getAtkVal()) {
			return true;
		}
		return false;	
	}
	
	/**
	 *	Method use to call effect
	 */
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

	/**
	 *@return boolean that this monster can use effect or not
	 */
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
	
	/**
	 *	@return	monster's image
	 */
	public Image getImg() {
		// TODO Auto-generated method stub
		if(this.getStatus()) return (new Image(getClass().getResourceAsStream("../img/KingAtk.png")));
		return (new Image(getClass().getResourceAsStream("../img/KingDef.png")));
	}

}