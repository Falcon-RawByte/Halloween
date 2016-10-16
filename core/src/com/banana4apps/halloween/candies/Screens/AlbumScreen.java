package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.banana4apps.halloween.candies.GameValues;
import com.banana4apps.halloween.candies.OpenedCards;
import com.banana4apps.halloween.candies.TextureFiles;

public class AlbumScreen extends Screen {

    private Table table;
    private VerticalGroup page1, page2, page3, page4;
    private Table right_table;

    Image moster_cards[] = new Image[32];

    ImageButton b_1, b_2, b_3, b_4;

    int W = Gdx.graphics.getWidth();
    int H = Gdx.graphics.getHeight();
    private int rowI = 0;


    public void create()
    {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureFiles.ui_page = new com.banana4apps.halloween.candies.UTexture("album.png");

        right_table = new Table();
        right_table.setSize(W * 0.1f, H / 3);
        right_table.setPosition(W * 0.75f, H / 3);
        stage.addActor(right_table);

        page1 = createPage(1);
        page2 = createPage(2);
        page3 = createPage(3);
        page4 = createPage(4);

        stage.addActor(page1);
        stage.addActor(page2);
        stage.addActor(page3);
        stage.addActor(page4);

        page1.setVisible(true);
        page2.setVisible(false);
        page3.setVisible(false);
        page4.setVisible(false);

        TextureAtlas atlas = new TextureAtlas("numbers.atlas");
        Skin buttons_skin = new Skin();
        buttons_skin.addRegions(atlas);

        TextureRegion b_1_iddle = buttons_skin.get(   "album_page_1_off", TextureRegion.class);
        TextureRegion b_1_pressed = buttons_skin.get( "album_page_1_on", TextureRegion.class);
        TextureRegion b_2_iddle = buttons_skin.get(   "album_page_2_off", TextureRegion.class);
        TextureRegion b_2_pressed = buttons_skin.get( "album_page_2_on", TextureRegion.class);
        TextureRegion b_3_iddle = buttons_skin.get(   "album_page_3_off", TextureRegion.class);
        TextureRegion b_3_pressed = buttons_skin.get( "album_page_3_on", TextureRegion.class);
        TextureRegion b_4_iddle = buttons_skin.get(   "album_page_4_off", TextureRegion.class);
        TextureRegion b_4_pressed = buttons_skin.get( "album_page_4_on", TextureRegion.class);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_1_iddle);
        style.down = new TextureRegionDrawable(b_1_pressed);
        style.checked = new TextureRegionDrawable(b_1_pressed);
        b_1 = new ImageButton(style);
        b_1.setSize(0.2f * W, 0.2f * H);
        b_1.setChecked(true);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_2_iddle);
        style.down = new TextureRegionDrawable(b_2_pressed);
        style.checked = new TextureRegionDrawable(b_2_pressed);
        b_2 = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_3_iddle);
        style.down = new TextureRegionDrawable(b_3_pressed);
        style.checked = new TextureRegionDrawable(b_3_pressed);
        b_3 = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(b_4_iddle);
        style.down = new TextureRegionDrawable(b_4_pressed);
        style.checked = new TextureRegionDrawable(b_4_pressed);
        b_4 = new ImageButton(style);


        b_1.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                page1.setVisible(true);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(false);
            }
        });

        b_2.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                page1.setVisible(false);
                page2.setVisible(true);
                page3.setVisible(false);
                page4.setVisible(false);
            }
        });

        b_3.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(true);
                page4.setVisible(false);
            }
        });

        b_4.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(true);
            }
        });


//        right_table.space(H/20);
                           right_table.add(b_1).width(W/14).height((W/14)*b_1_iddle.getRegionHeight()/b_1_iddle.getRegionWidth()).pad(10);
        right_table.row(); right_table.add(b_2).width(W/14).height((W/14)*b_1_iddle.getRegionHeight()/b_1_iddle.getRegionWidth()).pad(10);
        right_table.row(); right_table.add(b_3).width(W/14).height((W/14)*b_1_iddle.getRegionHeight()/b_1_iddle.getRegionWidth()).pad(10);
        right_table.row(); right_table.add(b_4).width(W/14).height((W/14)*b_1_iddle.getRegionHeight()/b_1_iddle.getRegionWidth()).pad(10);

        right_table.invalidate();
        stage.addActor(right_table);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);
        buttonGroup.add(b_1);
        buttonGroup.add(b_2);
        buttonGroup.add(b_3);
        buttonGroup.add(b_4);

        updateCards();
    }

    private VerticalGroup createPage(int i)
    {
        // Create Page

        VerticalGroup group = new VerticalGroup();
        group.setSize(W * 0.6f, H * 0.7f);
        group.setPosition(W * 0.11f, 0);

        if (i == 1)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_frankenstein.png"))));
        if (i == 2)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_werwolf.png"))));
        if (i == 3)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_witch.png"))));
        if (i == 4)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_mummy.png"))));
        group.addActor(createRow());

        if (i == 1)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_scarecrow.png"))));
        if (i == 2)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_vampire.png"))));
        if (i == 3)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_ghost.png"))));
        if (i == 4)
            group.addActor(new Image(new TextureRegion(new Texture("album_name_rose.png"))));
        group.addActor(createRow());

        return group;
    }

    private Actor createRow() {

        Table g = new Table();
//        g.fill();
//        g.setFillPa/rent(true);
        Image i_1 = new Image(new TextureRegion(new Texture("monstr_card.png")));
        i_1.setScaling(Scaling.fit);
        Image i_2 = new Image(new TextureRegion(new Texture("monstr_card.png")));
        i_2.setScaling(Scaling.fit);
        Image i_3 = new Image(new TextureRegion(new Texture("monstr_card.png")));
        i_3.setScaling(Scaling.fit);
        Image i_4 = new Image(new TextureRegion(new Texture("monstr_card.png")));
        i_4.setScaling(Scaling.fit);

        moster_cards[rowI + 0] = i_1;
        moster_cards[rowI + 1] = i_2;
        moster_cards[rowI + 2] = i_3;
        moster_cards[rowI + 3] = i_4;

        i_1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                {
                    ScreenManager.callback.shareCard(rowI/4, 0);
                }
            }
        });
        i_2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                {
                    ScreenManager.callback.shareCard(rowI/4, 1);
                }
            }
        });
        i_3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                {
                    ScreenManager.callback.shareCard(rowI/4, 2);
                }
            }
        });
        i_4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                {
                    ScreenManager.callback.shareCard(rowI/4, 3);
                }
            }
        });


        rowI += 4;

        g.add(i_1).width(W / 9).height(((float) H / 720) * 150);
        g.add(i_2).width(W / 9).height(((float) H / 720) * 150);
        g.add(i_3).width(W / 9).height(((float) H / 720) * 150);
        g.add(i_4).width(W / 9).height(((float) H / 720) * 150);

        return g;
    }

    public void updateCards()
    {
        int row = 0;
        for (int monster = 0; monster < 8; monster++) {
            
            
            for (int i = 0; i < 4; i++) {
                if (OpenedCards.getArray(monster)[0]
                        && OpenedCards.getArray(monster)[1] &&
                        OpenedCards.getArray(monster)[2])
                    OpenedCards.getArray(monster)[3] = true;

                if (com.banana4apps.halloween.candies.OpenedCards.getArray(monster)[i])
                    if (i == 0)
                        moster_cards[row + i].setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_old[row / 4])));
                    else if (i == 1)
                        moster_cards[row + i].setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_norm[row / 4])));
                    else if (i == 2)
                        moster_cards[row + i].setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_gold[row / 4])));
                    else if (i == 3)
                        moster_cards[row + i].setDrawable(new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_hell[row / 4])));
            }
            row += 4;
        }
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render (SpriteBatch batch) {
        Gdx.gl.glClearColor(7.f / 255, 5.f / 255, 27.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(TextureFiles.ui_page, 0, 0,
                W * 0.95f,
                W * 0.95f * TextureFiles.ui_page.getRatio());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

}
