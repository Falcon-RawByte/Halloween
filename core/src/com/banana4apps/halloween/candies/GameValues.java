package com.banana4apps.halloween.candies;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameValues {

    public static int Canes;

    public static int Candy_1;
    public static int Candy_2;
    public static int Candy_3;

    public static TextureRegionDrawable Player;
    public static int PlayerID = -1;
    public static void setPlayer(int i)
    {
        if (i == 0) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_1w.png")));
        if (i == 1) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_2w.png")));
        if (i == 2) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_3m.png")));
        if (i == 3) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_4m.png")));
        if (i == 4) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_5m.png")));
        if (i == 5) Player = new TextureRegionDrawable(new TextureRegion(new Texture("avatar_6m.png")));
        PlayerID = i;
    }

    //Opened cards
    public static OpenedCards openedCards = new OpenedCards();

    public static int progress = 0;

    public static int Monster_Cards_Price[][] = new int[8][3];
    public static int Player_Cards_Price[][] = new int[8][3];

    public static boolean bought_players[] = new boolean[6];

    public static int current_Enemy = 0;
}

