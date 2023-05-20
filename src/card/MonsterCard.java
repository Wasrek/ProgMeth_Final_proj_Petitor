package card;

import logic.CardName;
import logic.CardType;
import logic.GameLogic;

/**
 * @author Petitor
 *
 */
/**
 * @author Chayodom
 *
 */
public abstract class MonsterCard extends BaseCard implements Attackable, Guardable{
	/**
	 * Card's defense power
	 */
	private int DefVal;
	/**
	 * 	Card's attack power
	 */
	private int AtkVal;
	/**
	 * Card's status if true represents attacker, false represents defender
	 */
	private boolean Status;
	/**
	 * Store the turn that card use effect
	 */
	private int lastUsedTurn;
	/**
	 * Store the turn that card attack
	 */
	private int lastAtk;
	/**
	 * Store the turn that card is summoned
	 */
	private int summonedTurn;
	
	/**
	 * Constructor for Monster card
	 * @param name	Card's name
	 * @param price	Card's price
	 * @param AtkVal Card's attack power 
	 * @param DefVal Card's defense power
	 * @param Status Card's status
	 */
	public MonsterCard(CardName name, int price, int AtkVal, int DefVal, boolean Status) {
		super(CardType.MONSTER, name, price, 0);
		this.DefVal = DefVal;
		this.AtkVal = AtkVal;
		this.Status = Status;
		this.lastUsedTurn = -1;
		this.lastAtk = -1;
		this.summonedTurn = -1;
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @return effect's name
	 */
	public abstract String performEffect();

	public int getDefVal() {
		return DefVal;
	}

	public void setDefVal(int defVal) {
		DefVal = defVal;
	}

	public int getAtkVal() {
		return AtkVal;
	}

	public void setAtkVal(int atkVal) {
		AtkVal = atkVal;
	}

	public boolean getStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}
	
	public int getVal() {
		if(this.getStatus()) {
			return this.AtkVal;
		}
		return this.DefVal;
	}
	
	public int getLastUsedTurn() {
		return lastUsedTurn;
	}

	public void setLastUsedTurn(int lastUsedTurn) {
		this.lastUsedTurn = lastUsedTurn;
	}
	
	public int getLastAtk() {
		return lastAtk;
	}
	
	public void setLastAtk(int lastAtk) {
		this.lastAtk = lastAtk;
	}
	public int getSummonedTurn() {
		return summonedTurn;
	}


	public void setSummonedTurn(int summonedTurn) {
		this.summonedTurn = summonedTurn;
	}
	
	// atk, def, status, effect
	// last status modify(check attackable), last turn active(check if already attack on that turn or not)
}
