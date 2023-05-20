package logic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * enum represents card type 
 * @author Petitor
 *
 */
public enum CardType {
    MONSTER,
    SPELL;

    /**
     * get all card type
     * @return	ArrayList of card type
     */
    public static ArrayList<CardType> getCardType() {
        return new ArrayList<>(Arrays.asList(MONSTER, SPELL));
    }
    /**
     *	the name of this enum constant
     */
    @Override
    public String toString() {
        return super.toString().replace("_"," ");
    }
}