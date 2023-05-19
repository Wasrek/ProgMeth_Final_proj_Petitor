package logic;

import java.util.ArrayList;
import java.util.Arrays;

public enum CardType {
    MONSTER,
    SPELL;

    public static ArrayList<CardType> getCardType() {
        return new ArrayList<>(Arrays.asList(MONSTER, SPELL));
    }
    @Override
    public String toString() {
        return super.toString().replace("_"," ");
    }
}