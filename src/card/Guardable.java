package card;

/**
 * interface for the cards that can guard other monsters in attack phrase
 * @author Wishmeluck
 *
 */
public interface Guardable {
	/**
	 * check if this card can guard the attacker
	 * @param card	card attack this
	 * @return	true if can guard, false otherwise.
	 */
	boolean isGuardable(BaseCard card);
}