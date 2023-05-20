package card;

import logic.CardName;
import logic.CardType;
import logic.GameLogic;

/**
 * @author Wishmeluck
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
	 * Get card effect's name
	 * @return effect's name
	 */
	public abstract String performEffect();

	/**
	 * Getter for attack power
	 * @return	card's defense power
	 */
	public int getDefVal() {
		return DefVal;
	}

	/**
	 * Setter for defense power
	 * @param defVal card's defense power
	 */
	public void setDefVal(int defVal) {
		DefVal = defVal;
	}

	/**
	 * Getter for attack power
	 * @return card's attack power
	 */
	public int getAtkVal() {
		return AtkVal;
	}

	/**
	 * Setter for attack power
	 * @param atkVal card's attack power
	 */
	public void setAtkVal(int atkVal) {
		AtkVal = atkVal;
	}

	/**
	 * Getter for card's status
	 * @return card's status
	 */
	public boolean getStatus() {
		return Status;
	}

	/**
	 * Setter for card's status
	 * @param status card's status
	 */
	public void setStatus(boolean status) {
		Status = status;
	}
	
	/**
	 * Getter for card's value depending on its status 
	 * @return card's value
	 */
	public int getVal() {
		if(this.getStatus()) {
			return this.AtkVal;
		}
		return this.DefVal;
	}
	
	/**
	 * Getter for card's last used effect turn 
	 * @return last used effect turn
	 */
	public int getLastUsedTurn() {
		return lastUsedTurn;
	}

	/**
	 * Getter for card's last used effect turn 
	 * @param lastUsedTurn	turn that this card use effect last turn
	 */
	public void setLastUsedTurn(int lastUsedTurn) {
		this.lastUsedTurn = lastUsedTurn;
	}
	
	/**
	 * Getter for card's last attack turn
	 * @return last attack turn
	 */
	public int getLastAtk() {
		return lastAtk;
	}
	
	/**
	 * Setter card's last attack turn
	 * @param lastAtk	turn that this card attack last turn
	 */
	public void setLastAtk(int lastAtk) {
		this.lastAtk = lastAtk;
	}
	/**
	 * Getter for card's summoned turn
	 * @return card's summoned turn
	 */
	public int getSummonedTurn() {
		return summonedTurn;
	}


	/**
	 * Setter for card's summoned turn
	 * @param summonedTurn	turn that this card is summoned
	 */
	public void setSummonedTurn(int summonedTurn) {
		this.summonedTurn = summonedTurn;
	}
}