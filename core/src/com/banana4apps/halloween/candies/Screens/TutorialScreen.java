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
import com.banana4apps.halloween.candies.TextureFiles;

import java.util.Random;

public class TutorialScreen extends Screen {

    ImageButton Pumpkin;

    int W = Gdx.graphics.getWidth();
    int H = Gdx.graphics.getHeight();

    Table table;

    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(new Texture("hint_text.png")));

        Pumpkin = new ImageButton(style);
        Pumpkin.setWidth(W);

        Pumpkin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                ScreenManager.showGame = true;
            }
        });

        table.add(Pumpkin).width(W);

        table.setFillParent(true);
        stage.addActor(table);
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