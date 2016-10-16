package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.banana4apps.halloween.candies.TextureFiles;
import com.banana4apps.halloween.candies.UTexture;

import java.util.Random;


public class HousesScreen extends Screen {

    com.banana4apps.halloween.candies.UTexture sky;


    float W = Gdx.graphics.getWidth();
    float H = Gdx.graphics.getHeight();
    float Aratio;

    ScrollPane Street;


    private ChangeListener houseClicked = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            com.banana4apps.halloween.candies.Screens.ScreenManager.showGame = true;
        }
    };

    Button enemies[] = new Button[8];
    public void updateProgress()
    {
        for (int i = 0; i<8; i++)
        {
            enemies[i].setVisible(false);
            if (i <= com.banana4apps.halloween.candies.GameValues.progress) enemies[i].setVisible(true);
        }
    }

    public void create() {

        Aratio = H/720;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        sky = new com.banana4apps.halloween.candies.UTexture("street_sky.png");

        Stack stack = new Stack();


        Random rng = new Random();
        Table trees_group = new Table();
        trees_group.setFillParent(true);
        trees_group.add(new Image(new TextureRegion(TextureFiles.hint_start)))
                .height(TextureFiles.hint_start.getHeight() * Aratio)
                .width((TextureFiles.hint_start.getHeight() * Aratio) / TextureFiles.hint_start.getRatio());

        for (int i = 0; i<8; i++) {
            UTexture tree = new UTexture("street_trees_"+(rng.nextInt(6)+1)+".png");
            trees_group.add(new Image(new TextureRegion(tree)))
                    .height(tree.getHeight() * Aratio)
                    .width((tree.getHeight() * Aratio) / tree.getRatio());


            TextureRegion region = new TextureRegion(TextureFiles.Homes[i]);
            Button.ButtonStyle style = new Button.ButtonStyle();
            style.up = new TextureRegionDrawable(region);
            Button image = new Button(style);
            trees_group.add(image).height(TextureFiles.Homes[i].getHeight() * Aratio).width((TextureFiles.Homes[i].getHeight()*Aratio)/TextureFiles.Homes[i].getRatio());

        }

        trees_group.add(new Image(new TextureRegion(TextureFiles.hint_end)))
                .height(TextureFiles.hint_end.getHeight() * Aratio)
                .width((TextureFiles.hint_end.getHeight() * Aratio) / TextureFiles.hint_end.getRatio());

        final float max_W = trees_group.getPrefWidth() -
                (TextureFiles.hint_end.getHeight() * Aratio) / TextureFiles.hint_end.getRatio()
                - (TextureFiles.hint_start.getHeight() * Aratio) / TextureFiles.hint_start.getRatio();



        Table characters_group = new Table();
        characters_group.setFillParent(true);

        characters_group.add(new Image()).width(max_W / 48).padRight(5 * max_W / 48);

        for (int i = 0; i<8; i++) {
            TextureRegion region = new TextureRegion(TextureFiles.Characters[i]);
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();

            style.up = new TextureRegionDrawable(region);
            Button image = new Button(style);
            enemies[i] = image;
            final int finalI = i;
            image.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    com.banana4apps.halloween.candies.Screens.ScreenManager.showGame = true;
                    com.banana4apps.halloween.candies.GameValues.current_Enemy = finalI;
                }
            });
            float h = (max_W/48)*TextureFiles.Characters[i].getHeight()/TextureFiles.Characters[i].getWidth();
            characters_group.add(image).width(max_W/48).height(h).padRight(5*max_W/48);
        }


        Table street_group = new Table();
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_2.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_2.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_2.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_2.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_2.png")))).height(H).width(H*2.2917f);
        street_group.add(new Image(new TextureRegion(new Texture("street_1.png")))).height(H).width(H*2.2917f);


        stack.add(street_group);
        stack.add(trees_group);
        stack.add(characters_group);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(stack);

        Street = new ScrollPane(stack);
        Street.setSize(W, H);
        Street.setPosition(0, 0);
        Street.setOverscroll(false, false);
//        Street.setFlickScroll(false);
        stage.addActor(Street);
//        Street.addListener(new EventListener() {
//
//            @Override
//            public boolean handle(Event event) {
//                if (Street.getScrollX() > max_W-886) {
//                    Street.scrollTo(max_W - 886, 0, 0, 0);
//                    Street.setVelocityX(0);
//                }
//
//                return false;
//            }
//        });
    }

    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render (SpriteBatch batch) {
        Gdx.gl.glClearColor(7.f / 255, 5.f / 255, 27.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(sky, 0, H - W * sky.getRatio(),
                W,
                W * sky.getRatio());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
//        stage.setDebugAll(true);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
