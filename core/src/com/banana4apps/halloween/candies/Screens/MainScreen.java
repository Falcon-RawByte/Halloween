package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.banana4apps.halloween.candies.TextureFiles;

public class MainScreen extends Screen {

    int W = Gdx.graphics.getWidth();
    int H = Gdx.graphics.getHeight();

    public ImageButton
            start,
            more,
            options,
            sound;

    Table table;

    public static ChangeListener moreAction;

    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureFiles.main_page = new com.banana4apps.halloween.candies.UTexture("start_bg.png");

        table = new Table();
        table.setFillParent(true);

        TextureAtlas atlas = new TextureAtlas("main_buttons.atlas");
        Skin buttons_skin = new Skin();
        buttons_skin.addRegions(atlas);

        TextureRegion b_1_iddle = buttons_skin.get("b_start", TextureRegion.class);
        TextureRegion b_2_iddle = buttons_skin.get("b_more", TextureRegion.class);
        TextureRegion b_3_iddle = buttons_skin.get("b_options", TextureRegion.class);
        TextureRegion b_5_iddle = buttons_skin.get("b_sound_off", TextureRegion.class);
        TextureRegion b_4_checked = buttons_skin.get("b_sound_on", TextureRegion.class);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_1_iddle);
        start = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_2_iddle);
        more = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_3_iddle);
        options = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(b_5_iddle);
        style.imageChecked = new TextureRegionDrawable(b_4_checked);
        sound = new ImageButton(style);


        more.addListener(moreAction);

        options.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        sound.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {

            }
        });


        table.row();
        table.add(start).width(W/4).height((W / 4) * b_1_iddle.getRegionHeight() / b_1_iddle.getRegionWidth())
                .padLeft((3 * W) / 4).padTop(H / 30);
        table.row();
        table.add(more).width(W / 4).height((W / 4) * b_2_iddle.getRegionHeight() / b_2_iddle.getRegionWidth())
                .padLeft((3 * W) / 4).padTop(H / 30);
        table.row();
        table.add(options).width(W / 4).height((W / 4) * b_3_iddle.getRegionHeight() / b_3_iddle.getRegionWidth())
                .padLeft((3 * W) / 4).padTop(H / 30);
        table.row();
        table.add(sound).width(W).height((W / 4) * b_5_iddle.getRegionHeight() / b_5_iddle.getRegionWidth())
                .padLeft((3* W) / 4).padTop(H/30);

        table.invalidate();
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
        batch.draw(TextureFiles.main_page, 0, H - W * TextureFiles.main_page.getRatio(),
                W,
                W * TextureFiles.main_page.getRatio());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

}
