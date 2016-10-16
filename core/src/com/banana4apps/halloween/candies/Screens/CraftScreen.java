package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.banana4apps.halloween.candies.GameValues;
import com.banana4apps.halloween.candies.TextureFiles;

public class CraftScreen extends Screen {

    private Table table, top_table;

    ImageButton b_Cards, b_Frames;
    ScrollPane Cards_Scroll;
//    ScrollPane Canes_Scroll;
    ScrollPane Frames_Scroll;

    ScreenManager manager;

    int W = Gdx.graphics.getWidth();
    int H = Gdx.graphics.getHeight();

    Actor rowMCard[] = new Actor[8];
    Actor rowPCard[] = new Actor[6];

    Actor priceButtons[][] = new Actor[6][3];

    public void create(ScreenManager m)
    {
        manager = m;
        stage = new Stage(new FillViewport(W, H));
        Gdx.input.setInputProcessor(stage);

//        table = new Table(new Skin());
//        table.setFillParent(true);
//        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("bord_menu.png"))));
        TextureFiles.ui_table = new com.banana4apps.halloween.candies.UTexture("bord_menu.png");
//        stage.addActor(table);

        top_table = new Table();
//        top_table.setFillParent(true);
        top_table.setSize(W * 0.5f, H / 12);
        top_table.setPosition(W / 4, H * 4.5f / 6);

        TextureAtlas atlas = new TextureAtlas("buttons.atlas");
        Skin buttons_skin = new Skin();
        buttons_skin.addRegions(atlas);

        TextureRegion cards_iddle = buttons_skin.get(   "tab_cards_off", TextureRegion.class);
        TextureRegion cards_pressed = buttons_skin.get( "tab_cards_on", TextureRegion.class);
        TextureRegion frames_iddle = buttons_skin.get(  "tab_avatars_off", TextureRegion.class);
        TextureRegion frames_pressed = buttons_skin.get("tab_avatars_on", TextureRegion.class);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        style.up = new TextureRegionDrawable(canes_iddle);
//        style.down = new TextureRegionDrawable(canes_pressed);
//        style.checked = new TextureRegionDrawable(canes_pressed);
//        b_Canes = new ImageButton(style);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(cards_iddle);
        style.down = new TextureRegionDrawable(cards_pressed);
        style.checked = new TextureRegionDrawable(cards_pressed);
        b_Cards = new ImageButton(style);
        b_Cards.setChecked(true);

        style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(frames_iddle);
        style.down = new TextureRegionDrawable(frames_pressed);
        style.checked = new TextureRegionDrawable(frames_pressed);
        b_Frames = new ImageButton(style);

        top_table.defaults().expand().fill().padBottom(4f);
        float height = (W/7)*frames_iddle.getRegionHeight()/frames_iddle.getRegionWidth();
//        top_table.add(b_Canes).width(W / 7).height(height);
        top_table.add(b_Cards).width(W / 7).height(height);
        top_table.add(b_Frames).width(W / 7).height(height);

//        b_Canes.getImage().setScaling(Scaling.fit);
        b_Cards.getImage().setScaling(Scaling.fit);
        b_Frames.getImage().setScaling(Scaling.fit);
//        b_Canes.invalidate();
        b_Cards.invalidate();
        b_Frames.invalidateHierarchy();

        VerticalGroup group = new VerticalGroup();

        rowMCard[0] = createRow(0);
        rowMCard[1] = createRow(1);
        rowMCard[2] = createRow(2);
        rowMCard[3] = createRow(3);
        rowMCard[4] = createRow(4);
        rowMCard[5] = createRow(5);
        rowMCard[6] = createRow(6);
        rowMCard[7] = createRow(7);

        group.addActor(rowMCard[0]);
        group.addActor(rowMCard[1]);
        group.addActor(rowMCard[2]);
        group.addActor(rowMCard[3]);
        group.addActor(rowMCard[4]);
        group.addActor(rowMCard[5]);
        group.addActor(rowMCard[6]);
        group.addActor(rowMCard[7]);

        Cards_Scroll = new ScrollPane(group);
        Cards_Scroll.setSize(W * 0.88f, W*0.55f*TextureFiles.ui_table.getRatio());
        Cards_Scroll.setPosition(W*0.06f, H*0.08f);
        Cards_Scroll.setScrollingDisabled(true, false);

        Texture scrollTexture = new Texture(Gdx.files.internal("scroll_1.png"));
        Texture box = new Texture(Gdx.files.internal("scroll_2.png"));
        NinePatch scrollNine = new NinePatch(new TextureRegion(scrollTexture,34,329),16,18,100,200);
        NinePatch boxNine = new NinePatch(new TextureRegion(box,18,38),7,10,13,23);

        ScrollPane.ScrollPaneStyle scrollStyle = new ScrollPane.ScrollPaneStyle();
        scrollStyle.vScrollKnob = new NinePatchDrawable(boxNine);
        scrollStyle.vScroll = new NinePatchDrawable(scrollNine);

        Cards_Scroll.setStyle(scrollStyle);
        Cards_Scroll.setFadeScrollBars(false);
        stage.addActor(Cards_Scroll);

        createFramesPage();

        ButtonGroup buttonGroup = new ButtonGroup(b_Cards, b_Frames);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        b_Cards.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Cards_Scroll.setVisible(true);
                Frames_Scroll.setVisible(false);
            }
        });

        b_Frames.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Cards_Scroll.setVisible(false);
                Frames_Scroll.setVisible(true);
            }
        });

        stage.addActor(top_table);


    }

    private Actor createRow(final int i) {
        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

        Label.LabelStyle Lstyle = new Label.LabelStyle(font, Color.valueOf("3a2115"));

        ImageTextButton.ImageTextButtonStyle IBStyle = new ImageTextButton.ImageTextButtonStyle(
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png"))),
                font
        );
        IBStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("candy_1_bubble.png")));
        IBStyle.pressedOffsetY = H/15;
        IBStyle.unpressedOffsetY=H/15;
        IBStyle.checkedOffsetY = H/15;
        IBStyle.checkedOffsetX = H/15;
        IBStyle.pressedOffsetX = H/15;
        IBStyle.unpressedOffsetX=H/15;
        IBStyle.fontColor = Color.valueOf("3a2115");

        Table g = new Table();
        g.setSize(W * 0.8f, H / 8);

        ImageButton.ImageButtonStyle style_card = new ImageButton.ImageButtonStyle();
        style_card.imageUp = new TextureRegionDrawable(new TextureRegion(TextureFiles.Cards_gold[i]));
        ImageButton card = new ImageButton(style_card);
        card.setWidth(W / 8);
        card.getImage().setScaling(Scaling.fit);
        float ratio = card.getImage().getDrawable().getMinHeight()/card.getImage().getDrawable().getMinWidth();
        g.add(card).width(W/8).height(ratio*W/8);

        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                if (com.banana4apps.halloween.candies.GameValues.Candy_1 >= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][0])
                if (com.banana4apps.halloween.candies.GameValues.Candy_2 >= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][1])
                if (com.banana4apps.halloween.candies.GameValues.Candy_3 >= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][2])
                {
                    com.banana4apps.halloween.candies.GameValues.Candy_1 -= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][0];
                    com.banana4apps.halloween.candies.GameValues.Candy_2 -= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][1];
                    com.banana4apps.halloween.candies.GameValues.Candy_3 -= com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][2];

                    com.banana4apps.halloween.candies.OpenedCards.getArray(i)[2] = true;
                    hideBought();
                    manager.updateCandy();
                    manager.albumScreen.updateCards();
                }
            }
        });


        Image text = new Image();
        if (i == 0) text = (new Image(new TextureRegion(new Texture("board_name_frankenstein.png"))));
        if (i == 1) text = (new Image(new TextureRegion(new Texture("board_name_scarecrow.png"))));
        if (i == 2) text = (new Image(new TextureRegion(new Texture("board_name_werwolf.png"))));
        if (i == 3) text = (new Image(new TextureRegion(new Texture("board_name_vampire.png"))));
        if (i == 4) text = (new Image(new TextureRegion(new Texture("board_name_witch.png"))));
        if (i == 5) text = (new Image(new TextureRegion(new Texture("board_name_ghost.png"))));
        if (i == 6) text = (new Image(new TextureRegion(new Texture("board_name_mummy.png"))));
        if (i == 7) text = (new Image(new TextureRegion(new Texture("board_name_rose.png"))));
        text.setWidth(W / 3);
        text.setScaling(Scaling.fit);
        g.add(text).width(W * 0.18f);

        ImageTextButton itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][0], IBStyle);
        ratio = IBStyle.checked.getMinHeight()/IBStyle.checked.getMinWidth();
        g.add(itb).width(W*0.15f).height((W*0.15f)*ratio);

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
        itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][1], IBStyle);
        g.add(itb).width(W * 0.15f).height((W*0.15f)*ratio);

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
        itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Monster_Cards_Price[i][2], IBStyle);
        itb.getImage().setScaling(Scaling.fit);

        g.add(itb).width(W*0.15f).height((W*0.15f)*ratio);

        return g;
    }

    private void createFramesPage()
    {
        VerticalGroup group = new VerticalGroup();
        group.addActor((rowPCard[0] = createFramesRow(0)));
        group.addActor((rowPCard[1] = createFramesRow(1)));
        group.addActor((rowPCard[2] = createFramesRow(2)));
        group.addActor((rowPCard[3] = createFramesRow(3)));
        group.addActor((rowPCard[4] = createFramesRow(4)));
        group.addActor((rowPCard[5] = createFramesRow(5)));

        Frames_Scroll = new ScrollPane(group);
        Frames_Scroll.setSize(W * 0.88f, W*0.55f*TextureFiles.ui_table.getRatio());
        Frames_Scroll.setPosition(W*0.06f, H*0.08f);
        Frames_Scroll.setScrollingDisabled(true, false);

        Texture scrollTexture = new Texture(Gdx.files.internal("scroll_1.png"));
        Texture box = new Texture(Gdx.files.internal("scroll_2.png"));
        NinePatch scrollNine = new NinePatch(new TextureRegion(scrollTexture,34,329),16,18,100,200);
        NinePatch boxNine = new NinePatch(new TextureRegion(box,18,38),7,10,13,23);

        ScrollPane.ScrollPaneStyle scrollStyle = new ScrollPane.ScrollPaneStyle();
        scrollStyle.vScrollKnob = new NinePatchDrawable(boxNine);
        scrollStyle.vScroll = new NinePatchDrawable(scrollNine);

        Frames_Scroll.setStyle(scrollStyle);
        Frames_Scroll.setFadeScrollBars(false);
        stage.addActor(Frames_Scroll);
        Frames_Scroll.setVisible(false);
    }

    private Actor createFramesRow(final int i)
    {
        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

        Label.LabelStyle Lstyle = new Label.LabelStyle(font, Color.valueOf("3a2115"));

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

        TextureRegion card_draw = new TextureRegion();
        if (i == 0) card_draw = (new TextureRegion(new Texture("ic_avatar_1w.png")));
        if (i == 1) card_draw = (new TextureRegion(new Texture("ic_avatar_2w.png")));
        if (i == 2) card_draw = (new TextureRegion(new Texture("ic_avatar_3m.png")));
        if (i == 3) card_draw = (new TextureRegion(new Texture("ic_avatar_4m.png")));
        if (i == 4) card_draw = (new TextureRegion(new Texture("ic_avatar_5m.png")));
        if (i == 5) card_draw = (new TextureRegion(new Texture("ic_avatar_6m.png")));

        ImageButton.ImageButtonStyle style_card = new ImageButton.ImageButtonStyle();
        style_card.imageUp = new TextureRegionDrawable(card_draw);
        ImageButton card = new ImageButton(style_card);
        card.setWidth(W / 8);
        card.getImage().setScaling(Scaling.fit);
        float ratio = card.getImage().getDrawable().getMinHeight()/card.getImage().getDrawable().getMinWidth();
        g.add(card).width(W/8).height(ratio * W / 8);

        card.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {

                if (GameValues.bought_players[i]) {
                    com.banana4apps.halloween.candies.GameValues.setPlayer(i);
                    manager.updateCandy();
                    manager.albumScreen.updateCards();
                    return;
                }

                if (com.banana4apps.halloween.candies.GameValues.Candy_1 >= com.banana4apps.halloween.candies.GameValues.Player_Cards_Price[i][0])
                    if (GameValues.Candy_2 >= com.banana4apps.halloween.candies.GameValues.Player_Cards_Price[i][1])
                        if (GameValues.Candy_3 >= GameValues.Player_Cards_Price[i][2]) {

                            GameValues.Candy_1 -= GameValues.Player_Cards_Price[i][0];
                            GameValues.Candy_2 -= GameValues.Player_Cards_Price[i][1];
                            GameValues.Candy_3 -= GameValues.Player_Cards_Price[i][2];

                            com.banana4apps.halloween.candies.GameValues.setPlayer(i);
                            manager.updateCandy();
                            manager.albumScreen.updateCards();
                            GameValues.bought_players[i] = true;
                        }
            }
        });


        ImageTextButton itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Player_Cards_Price[i][0], IBStyle);
        priceButtons[i][2] = itb;
        g.add(itb).width(W*0.15f);

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
        itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Player_Cards_Price[i][1], IBStyle);
        priceButtons[i][2] = itb;
        g.add(itb).width(W*0.15f);

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
        itb = new ImageTextButton(""+ com.banana4apps.halloween.candies.GameValues.Player_Cards_Price[i][2], IBStyle);
        itb.getImage().setScaling(Scaling.fit);

        priceButtons[i][2] = itb;
        g.add(itb).width(W*0.15f);

        return g;
    }

    public void hideBought()
    {
        for (int i = 0; i<8; i++)
        if (com.banana4apps.halloween.candies.OpenedCards.getArray(i)[2])
        {
            rowMCard[i].setVisible(false);
        }

        for (int i = 0; i<6; i++)
            if (GameValues.bought_players[i])
            {
                priceButtons[i][0].setVisible(false);
                priceButtons[i][1].setVisible(false);
                priceButtons[i][2].setVisible(false);
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
        batch.draw(TextureFiles.ui_table, W * 0.06f, H * 0.04f,
                W * 0.88f,
                W * 0.88f * TextureFiles.ui_table.getRatio());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
//        stage.setDebugAll(true);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

}
