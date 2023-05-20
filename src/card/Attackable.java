package card;

/**
 * interface for the cards that can attack other monster in attack phrase
 * @author Petitor
 *
 */
public interface Attackable {
	void attack(BaseCard card);
	boolean isAttackable(BaseCard card);
}
