package com.banana4apps.halloween.candies;

public class OpenedCards
{
    public boolean[] o_frankenstein = new boolean[4];
    public boolean[] o_scarecrow = new boolean[4];
    public boolean[] o_werewolf = new boolean[4];
    public boolean[] o_vampire = new boolean[4];
    public boolean[] o_witch = new boolean[4];
    public boolean[] o_ghost = new boolean[4];
    public boolean[] o_mummy = new boolean[4];
    public boolean[] o_rose = new boolean[4];

    public static boolean[] getArray(int i)
    {
        if (i == 0) return GameValues.openedCards.o_frankenstein ;
        else if (i == 1) return GameValues.openedCards.o_scarecrow ;
        else if (i == 2) return GameValues.openedCards.o_werewolf ;
        else if (i == 3) return GameValues.openedCards.o_vampire ;
        else if (i == 4) return GameValues.openedCards.o_witch ;
        else if (i == 5) return GameValues.openedCards.o_ghost ;
        else if (i == 6) return GameValues.openedCards.o_mummy ;
        else return GameValues.openedCards.o_rose ;
    }
}
