package logic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * enum represents card name 
 * @author Wishmeluck
 *
 */
public enum CardName {
    GOLEM,
    CAT,
    DUCK,
    BIRD,
    KING,
    
    NWORLD,
    VITAMIN,
    TORNADO,
    BLACKHOLE;

    /**
     * get all monster names
     * @return	ArrayList of monster names
     */
    public static ArrayList<CardName> getMonsterName() {
        return new ArrayList<>(Arrays.asList(GOLEM, CAT, DUCK, BIRD, KING));
    }
    /**
     * get all effect names
     * @return	ArrayList of effect names
     */
    public static ArrayList<CardName> getEffName() {
        return new ArrayList<>(Arrays.asList(NWORLD, VITAMIN, TORNADO, BLACKHOLE));
    }
    /**
     *	the name of this enum constant
     */
    @Override
    public String toString() {
        return super.toString().replace("_"," ");
    }
}