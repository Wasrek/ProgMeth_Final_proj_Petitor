package card;

/**
 * interface for the cards that can use effect in effects phrase
 * @author Wishmeluck
 *
 */
public interface UseEffectAble {
	/**
	 * use effect function
	 */
	void useEffect();
	/**
	 * check if this card can use effect
	 * @return	true if the effect can be used, false otherwise.
	 */
	boolean isEffectable();

}