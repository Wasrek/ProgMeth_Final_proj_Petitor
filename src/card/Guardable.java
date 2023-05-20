package card;

/**
 * interface for the cards that can guard other monsters in attack phrase
 * @author Wishmeluck
 *
 */
public interface Guardable {
	boolean isGuardable(BaseCard card);
}