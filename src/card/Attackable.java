package card;

/**
 * interface for the cards that can attack other monster in attack phrase
 * @author Wishmeluck
 *
 */
public interface Attackable {
	/**
	 * attack function
	 * @param card	card to be attacked
	 */
	void attack(BaseCard card);
	/**
	 * check if this can attack card
	 * @param card	card to be attacked
	 * @return	true if the card can be attack, false otherwise
	 */
	boolean isAttackable(BaseCard card);
}