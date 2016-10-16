package com.banana4apps.halloween.candies.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.banana4apps.halloween.candies.GameValues;


public class ScreenManager {

    public static boolean showGame;
    private static Screen currentScreen;

    public boolean goBack() {
        if (currentScreen == mainScreen) return false;
        if (showGame) return true;

        if (currentScreen == housesScreen)
        changeScreen(mainScreen);
        else changeScreen(housesScreen);

        return true;
    }

    public void updateCandy() {
        candy1.setText("" + com.banana4apps.halloween.candies.GameValues.Candy_1);
        candy2.setText("" + com.banana4apps.halloween.candies.GameValues.Candy_2);
        candy3.setText("" + com.banana4apps.halloween.candies.GameValues.Candy_3);

        callback.savePoints();

        setting.getStyle().up = (com.banana4apps.halloween.candies.GameValues.Player);
//        candy2.setText(""+ GameValues.Candy_3);
    }

    public interface ScreenCallback
    {
        void showAd();
        void savePoints();
        void shareCard(int monster, int level);
    }
    public static ScreenCallback callback;

    com.banana4apps.halloween.candies.Screens.GameScreen gameScreen;
    CraftScreen craftScreen;
    AlbumScreen albumScreen;
    com.banana4apps.halloween.candies.Screens.MainScreen mainScreen;
    com.banana4apps.halloween.candies.Screens.WinScreen winScreen;
    TutorialScreen tutorialScreen;

    HousesScreen housesScreen;
    OrthographicCamera Camera;

    Stage moneyStage;

    Button craft, home, setting, book;
    Label candy1, candy2, candy3;

    public ScreenManager(OrthographicCamera camera) {
        Camera = camera;
        gameScreen = new com.banana4apps.halloween.candies.Screens.GameScreen(this);

        craftScreen = new CraftScreen();
        craftScreen.create(this);
        craftScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        albumScreen = new AlbumScreen();
        albumScreen.create();
        albumScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        housesScreen = new HousesScreen();
        housesScreen.create();
        housesScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mainScreen = new com.banana4apps.halloween.candies.Screens.MainScreen();
        mainScreen.create();
        mainScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        winScreen = new com.banana4apps.halloween.candies.Screens.WinScreen();
        winScreen.create();
        winScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        tutorialScreen = new TutorialScreen();
        tutorialScreen.create();
        tutorialScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Button.ButtonStyle style;
        TextureAtlas atlas = new TextureAtlas("unnamed.atlas");
        Skin buttons_skin = new Skin();
        buttons_skin.addRegions(atlas);

        int W = Gdx.graphics.getWidth();
        int H = Gdx.graphics.getHeight();

        style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(buttons_skin.get(   "but_book", TextureRegion.class));;
        book = new Button(style);
        book.setSize(0.1f * W, (0.1f * W) * style.up.getMinHeight() / style.up.getMinWidth());
        book.setPosition(W * 0.86f, 0);
        book.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(albumScreen);
            }
        });

        style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(buttons_skin.get(   "but_craft", TextureRegion.class));;
        craft = new Button(style);
        craft.setSize(0.1f * W, (0.1f * W) * style.up.getMinHeight() / style.up.getMinWidth());
        craft.setPosition(10, 0.8f * H);
        craft.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(craftScreen);
                craftScreen.b_Cards.setChecked(true);
            }
        });

        style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(buttons_skin.get(   "but_home", TextureRegion.class));;
        home = new Button(style);
        home.setSize(0.1f * W, (0.1f * W) * style.up.getMinHeight() / style.up.getMinWidth());
        home.setPosition(W * 0.72f, 0);
        home.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(housesScreen);
            }
        });

        style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture("icon_avatar.png")));;
        setting = new Button(style);
        setting.setSize(0.08f * W, (0.08f * W) * style.up.getMinHeight() / style.up.getMinWidth());
        setting.setPosition(W * 0.86f, H * 0.8f);
        setting.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(craftScreen);
                craftScreen.b_Frames.setChecked(true);
            }
        });

        changeScreen(mainScreen);

        moneyStage = new Stage();
        Image up_count_candy = new Image(new TextureRegion(new Texture("up_count_candys.png")));
//        Image up_count_canes = new Image(new TextureRegion(new Texture("up_count_canes.png")));

        Table top_table = new Table();
        top_table.setSize(W * 0.5f, H / 8);
        top_table.setPosition(W / 4, H * 7.f / 8);

        top_table.add(up_count_candy);

        moneyStage.addActor(top_table);

        top_table = new Table();
        top_table.setSize(W * 0.4f, H / 9);
        top_table.setPosition(W * 0.3f, H * 8.f / 9);

//        Image cane    = new Image(new TextureRegion(new Texture("cane.png")));
        Image candy_1 = new Image(new TextureRegion(new Texture("candy_1.png")));
        Image candy_2 = new Image(new TextureRegion(new Texture("candy_2.png")));
        Image candy_3 = new Image(new TextureRegion(new Texture("candy_3.png")));

//        cane   .setScaling(Scaling.fit);
        candy_1.setScaling(Scaling.fit);
        candy_2.setScaling(Scaling.fit);
        candy_3.setScaling(Scaling.fit);

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
        Label.LabelStyle Lstyle = new Label.LabelStyle(font, Color.valueOf("cb6137"));

        Label cane_Text = new Label("0", Lstyle);
        candy1 = new Label("0", Lstyle);
        candy2 = new Label("0", Lstyle);
        candy3 = new Label("0", Lstyle);

        cane_Text.setAlignment(Align.topLeft);
        candy1.setAlignment(Align.topLeft);
        candy2.setAlignment(Align.topLeft);
        candy3.setAlignment(Align.topLeft);

//        top_table.add(cane   ).width((W*0.4f)/8).pad(8);
//        top_table.add(cane_Text).width((W * 0.4f) / 8).padBottom(15);
//        top_table.add().width((W * 0.4f) / 8);

        top_table.add(candy_1).width((W * 0.4f) / 8);
        top_table.add(candy1).width((W * 0.4f) / 8).padBottom(15);
        top_table.add(candy_2).width((W * 0.4f) / 8);
        top_table.add(candy2).width((W * 0.4f) / 8).padBottom(15);
        top_table.add(candy_3).width((W * 0.4f) / 8);
        top_table.add(candy3).width((W * 0.4f) / 8).padBottom(15);
        moneyStage.addActor(top_table);

        mainScreen.start.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                changeScreen(housesScreen);
            }
        });

        updateCandy();
        housesScreen.updateProgress();
        callback.savePoints();
    }

    boolean showTutorial = true;
    public void draw(SpriteBatch batch) {
        if (showGame) {

            if (GameValues.progress == 0 && showTutorial) {
                currentScreen = tutorialScreen;
                showTutorial = false;
                showGame = false;
            }

            gameScreen.update(Camera);
            batch.setProjectionMatrix(Camera.combined);
            batch.begin();
            gameScreen.draw(batch);
            batch.end();
        } else {
            currentScreen.render(batch);
            moneyStage.act(Gdx.graphics.getDeltaTime());

            if (currentScreen != mainScreen && currentScreen != tutorialScreen)
                moneyStage.draw();
        }
    }

    public void changeScreen(Screen s)
    {
        currentScreen = s;
        showGame = false;
        if (s.getClass().equals(GameScreen.class))
            showGame = true;

        if (currentScreen != mainScreen) {
            callback.showAd();
            currentScreen.stage.addActor(setting);
            currentScreen.stage.addActor(home);
            currentScreen.stage.addActor(craft);
            currentScreen.stage.addActor(book);
        }
        Gdx.input.setInputProcessor(currentScreen.stage);
    }

}
