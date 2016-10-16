package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.banana4apps.halloween.candies.GameValues;
import com.banana4apps.halloween.candies.TextureFiles;

import java.util.Random;

public class WinScreen extends Screen {

    int candy_Win[] = new int[3];
    ImageTextButton b1, b2, b3;
    Image card;

    int W = Gdx.graphics.getWidth();
    int H = Gdx.graphics.getHeight();

    public ImageButton
            start,
            more,
            options,
            sound;


    public static ClickListener shareAction;

    Table table;
    ImageButton share;

    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

        Label.LabelStyle Lstyle = new Label.LabelStyle(font, Color.valueOf("3a2115"));

        card = new Image(new TextureRegion(TextureFiles.Cards_gold[0]));
        card.setWidth(W / 8);
        card.setScaling(Scaling.fit);
        table.add(card).width(W / 8);

        share = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("but_share.png"))));
        share.setWidth(W / 10);

        table.add(share).width(W/10);

        ImageTextButton.ImageTextButtonStyle IBStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                font
        );
        IBStyle.pressedOffsetY = H/15;
        IBStyle.unpressedOffsetY=H/15;
        IBStyle.checkedOffsetY = H/15;
        IBStyle.checkedOffsetX = H/15;
        IBStyle.pressedOffsetX = H/15;
        IBStyle.unpressedOffsetX=H/15;
        IBStyle.fontColor = Color.valueOf("3a2115");

        Table g = new Table();
        g.setSize(W * 0.8f, H / 8);

        b1 = new ImageTextButton(""+candy_Win[0], IBStyle);
        g.add(b1).width(W*0.15f);

        IBStyle = new ImageTextButton.ImageTextButtonStyle();
        IBStyle.font = font;
        IBStyle.pressedOffsetY = H/15;
        IBStyle.unpressedOffsetY=H/15;
        IBStyle.checkedOffsetY = H/15;
        IBStyle.checkedOffsetX = H/15;
        IBStyle.pressedOffsetX = H/15;
        IBStyle.unpressedOffsetX=H/15;
        IBStyle.fontColor = Color.valueOf("3a2115");
        IBStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("candy_2_bubble.png")));
        b2 = new ImageTextButton(""+candy_Win[1], IBStyle);
        g.add(b2).width(W*0.15f);

        IBStyle = new ImageTextButton.ImageTextButtonStyle();
        IBStyle.font = font;
        IBStyle.pressedOffsetY = H/15;
        IBStyle.unpressedOffsetY=H/15;
        IBStyle.checkedOffsetY = H/15;
        IBStyle.checkedOffsetX = H/15;
        IBStyle.pressedOffsetX = H/15;
        IBStyle.unpressedOffsetX=H/15;
        IBStyle.fontColor = Color.valueOf("3a2115");
        IBStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("candy_3_bubble.png")));
        b3 = new ImageTextButton(""+candy_Win[2], IBStyle);
        b3.getImage().setScaling(Scaling.fit);
        g.add(b3).width(W*0.15f);

        table.setFillParent(true);
        table.row();
        table.add(g).colspan(2);

        stage.addActor(table);
    }

    boolean[] getArray(int i)
    {
        if (i == 0) return com.banana4apps.halloween.candies.GameValues.openedCards.o_frankenstein ;
        else if (i == 1) return com.banana4apps.halloween.candies.GameValues.openedCards.o_scarecrow ;
        else if (i == 2) return com.banana4apps.halloween.candies.GameValues.openedCards.o_werewolf ;
        else if (i == 3) return com.banana4apps.halloween.candies.GameValues.openedCards.o_vampire ;
        else if (i == 4) return com.banana4apps.halloween.candies.GameValues.openedCards.o_witch ;
        else if (i == 5) return com.banana4apps.halloween.candies.GameValues.openedCards.o_ghost ;
        else if (i == 6) return com.banana4apps.halloween.candies.GameValues.openedCards.o_mummy ;
        else return com.banana4apps.halloween.candies.GameValues.openedCards.o_rose ;
    }

    public void show(ScreenManager manager, int Total)
    {
        int level = 0;
        if (Total == 5) {
            card.setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_gold[com.banana4apps.halloween.candies.GameValues.current_Enemy])));
            getArray(com.banana4apps.halloween.candies.GameValues.current_Enemy)[2] = true;
            level = 2;
        }
        else if (Total < 8) {
            card.setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_norm[com.banana4apps.halloween.candies.GameValues.current_Enemy])));
            getArray(com.banana4apps.halloween.candies.GameValues.current_Enemy)[1] = true;
            level = 1;
        }
        else{
            card.setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_old[com.banana4apps.halloween.candies.GameValues.current_Enemy])));
            getArray(com.banana4apps.halloween.candies.GameValues.current_Enemy)[0] = true;
            level = 0;
        }

        final int finalLevel = level;
        share.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                {
                    ScreenManager.callback.shareCard(GameValues.current_Enemy, finalLevel);
                }
            }
        });

        if (com.banana4apps.halloween.candies.GameValues.progress == com.banana4apps.halloween.candies.GameValues.current_Enemy) com.banana4apps.halloween.candies.GameValues.progress++;

        int number_of_prizes = 1;
        Random rng = new Random();

        if (rng.nextInt(100) < 60) number_of_prizes++;
        if (rng.nextInt(100) < 50) number_of_prizes++;

        for (int i = 0; i < number_of_prizes; i++)
            candy_Win[i] = 2*(rng.nextInt(3-i)+1)*(3-(i+1)/2);

        com.banana4apps.halloween.candies.GameValues.Candy_1 += candy_Win[0];
        com.banana4apps.halloween.candies.GameValues.Candy_2 += candy_Win[1];
        com.banana4apps.halloween.candies.GameValues.Candy_3 += candy_Win[2];

        b1.getLabel().setText(""+candy_Win[0]);
        b2.getLabel().setText(""+candy_Win[1]);
        b3.getLabel().setText("" + candy_Win[2]);

        manager.updateCandy();
        manager.albumScreen.updateCards();
        manager.housesScreen.updateProgress();
        manager.gameScreen = new GameScreen(manager);
        manager.showGame = false;
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render (SpriteBatch batch) {
        Gdx.gl.glClearColor(7.f / 255, 5.f / 255, 27.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(TextureFiles.Table, 0, 0, W, W * TextureFiles.Table.getRatio());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
//        stage.setDebugAll(true);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }


}
