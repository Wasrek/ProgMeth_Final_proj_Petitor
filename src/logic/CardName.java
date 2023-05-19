package logic;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<CardName> getMonsterName() {
        return new ArrayList<>(Arrays.asList(GOLEM, CAT, DUCK, BIRD, KING));
    }
    public static ArrayList<CardName> getEffName() {
        return new ArrayList<>(Arrays.asList(NWORLD, VITAMIN, TORNADO, BLACKHOLE));
    }
    @Override
    public String toString() {
        return super.toString().replace("_"," ");
    }
}