package com.banana4apps.halloween.candies;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.banana4apps.halloween.candies.Screens.ScreenManager;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	ScreenManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		TextureFiles.Card_Back = new com.banana4apps.halloween.candies.UTexture("cards_back.png");

		TextureFiles.Card_Face = new com.banana4apps.halloween.candies.UTexture[18];
		TextureFiles.Card_Face[0] = new com.banana4apps.halloween.candies.UTexture("cards_stone_0.png");
		TextureFiles.Card_Face[1] = new com.banana4apps.halloween.candies.UTexture("cards_stone_1.png");
		TextureFiles.Card_Face[2] = new com.banana4apps.halloween.candies.UTexture("cards_stone_2.png");
		TextureFiles.Card_Face[3] = new com.banana4apps.halloween.candies.UTexture("cards_stone_3.png");
		TextureFiles.Card_Face[4] = new com.banana4apps.halloween.candies.UTexture("cards_stone_4.png");
		TextureFiles.Card_Face[5] = new com.banana4apps.halloween.candies.UTexture("cards_stone_5.png");

		TextureFiles.Card_Face[6] = new com.banana4apps.halloween.candies.UTexture("cards_paper_0.png");
		TextureFiles.Card_Face[7] = new com.banana4apps.halloween.candies.UTexture("cards_paper_1.png");
		TextureFiles.Card_Face[8] = new com.banana4apps.halloween.candies.UTexture("cards_paper_2.png");
		TextureFiles.Card_Face[9] = new com.banana4apps.halloween.candies.UTexture("cards_paper_3.png");
		TextureFiles.Card_Face[10] = new com.banana4apps.halloween.candies.UTexture("cards_paper_4.png");
		TextureFiles.Card_Face[11] = new com.banana4apps.halloween.candies.UTexture("cards_paper_5.png");

		TextureFiles.Homes[0] = new com.banana4apps.halloween.candies.UTexture("house_franken.png");
		TextureFiles.Homes[1] = new com.banana4apps.halloween.candies.UTexture("house_scarecrow.png");
		TextureFiles.Homes[2] = new com.banana4apps.halloween.candies.UTexture("house_werwolf.png");
		TextureFiles.Homes[3] = new com.banana4apps.halloween.candies.UTexture("house_vampire.png");
		TextureFiles.Homes[4] = new com.banana4apps.halloween.candies.UTexture("house_witch.png");
		TextureFiles.Homes[5] = new com.banana4apps.halloween.candies.UTexture("house_ghost.png");
		TextureFiles.Homes[6] = new com.banana4apps.halloween.candies.UTexture("house_mummy.png");
		TextureFiles.Homes[7] = new com.banana4apps.halloween.candies.UTexture("house_rose.png");

		TextureFiles.Characters[0] = new com.banana4apps.halloween.candies.UTexture("pers_franken.png");
		TextureFiles.Characters[1] = new com.banana4apps.halloween.candies.UTexture("pers_scarecrow.png");
		TextureFiles.Characters[2] = new com.banana4apps.halloween.candies.UTexture("pers_werwolf.png");
		TextureFiles.Characters[3] = new com.banana4apps.halloween.candies.UTexture("pers_vampire.png");
		TextureFiles.Characters[4] = new com.banana4apps.halloween.candies.UTexture("pers_witch.png");
		TextureFiles.Characters[5] = new com.banana4apps.halloween.candies.UTexture("pers_ghost.png");
		TextureFiles.Characters[6] = new com.banana4apps.halloween.candies.UTexture("pers_mummy.png");
		TextureFiles.Characters[7] = new com.banana4apps.halloween.candies.UTexture("pers_rose.png");

		TextureFiles.Table = new com.banana4apps.halloween.candies.UTexture("cards_table.png");
		TextureFiles.Card_drop = new com.banana4apps.halloween.candies.UTexture("cards_shadow.png");

		TextureFiles.Card_Face[12] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_0.png");
		TextureFiles.Card_Face[13] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_1.png");
		TextureFiles.Card_Face[14] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_2.png");
		TextureFiles.Card_Face[15] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_3.png");
		TextureFiles.Card_Face[16] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_4.png");
		TextureFiles.Card_Face[17] = new com.banana4apps.halloween.candies.UTexture("cards_scissors_5.png");

		TextureFiles.Cards_old[0] = new com.banana4apps.halloween.candies.UTexture("c_old_franken.png");
		TextureFiles.Cards_old[1] = new com.banana4apps.halloween.candies.UTexture("c_old_scarecrow.png");
		TextureFiles.Cards_old[2] = new com.banana4apps.halloween.candies.UTexture("c_old_werwolf.png");
		TextureFiles.Cards_old[3] = new com.banana4apps.halloween.candies.UTexture("c_old_vampire.png");
		TextureFiles.Cards_old[4] = new com.banana4apps.halloween.candies.UTexture("c_old_witch.png");
		TextureFiles.Cards_old[5] = new com.banana4apps.halloween.candies.UTexture("c_old_ghost.png");
		TextureFiles.Cards_old[6] = new com.banana4apps.halloween.candies.UTexture("c_old_mummy.png");
		TextureFiles.Cards_old[7] = new com.banana4apps.halloween.candies.UTexture("c_old_rose.png");

		TextureFiles.Cards_norm[0] = new com.banana4apps.halloween.candies.UTexture("c_norm_franken.png");
		TextureFiles.Cards_norm[1] = new com.banana4apps.halloween.candies.UTexture("c_norm_scarecrow.png");
		TextureFiles.Cards_norm[2] = new com.banana4apps.halloween.candies.UTexture("c_norm_werwolf.png");
		TextureFiles.Cards_norm[3] = new com.banana4apps.halloween.candies.UTexture("c_norm_vampire.png");
		TextureFiles.Cards_norm[4] = new com.banana4apps.halloween.candies.UTexture("c_norm_witch.png");
		TextureFiles.Cards_norm[5] = new com.banana4apps.halloween.candies.UTexture("c_norm_ghost.png");
		TextureFiles.Cards_norm[6] = new com.banana4apps.halloween.candies.UTexture("c_norm_mummy.png");
		TextureFiles.Cards_norm[7] = new com.banana4apps.halloween.candies.UTexture("c_norm_rose.png");

		TextureFiles.Cards_gold[0] = new com.banana4apps.halloween.candies.UTexture("c_gold_franken.png");
		TextureFiles.Cards_gold[1] = new com.banana4apps.halloween.candies.UTexture("c_gold_scarecrow.png");
		TextureFiles.Cards_gold[2] = new com.banana4apps.halloween.candies.UTexture("c_gold_werwolf.png");
		TextureFiles.Cards_gold[3] = new com.banana4apps.halloween.candies.UTexture("c_gold_vampire.png");
		TextureFiles.Cards_gold[4] = new com.banana4apps.halloween.candies.UTexture("c_gold_witch.png");
		TextureFiles.Cards_gold[5] = new com.banana4apps.halloween.candies.UTexture("c_gold_ghost.png");
		TextureFiles.Cards_gold[6] = new com.banana4apps.halloween.candies.UTexture("c_gold_mummy.png");
		TextureFiles.Cards_gold[7] = new com.banana4apps.halloween.candies.UTexture("c_gold_rose.png");

		TextureFiles.Cards_hell[0] = new com.banana4apps.halloween.candies.UTexture("c_hallo_franken.png");
		TextureFiles.Cards_hell[1] = new com.banana4apps.halloween.candies.UTexture("c_hallo_scarecrow.png");
		TextureFiles.Cards_hell[2] = new com.banana4apps.halloween.candies.UTexture("c_hallo_werwolf.png");
		TextureFiles.Cards_hell[3] = new com.banana4apps.halloween.candies.UTexture("c_hallo_vampire.png");
		TextureFiles.Cards_hell[4] = new com.banana4apps.halloween.candies.UTexture("c_hallo_witch.png");
		TextureFiles.Cards_hell[5] = new com.banana4apps.halloween.candies.UTexture("c_hallo_ghost.png");
		TextureFiles.Cards_hell[6] = new com.banana4apps.halloween.candies.UTexture("c_hallo_mummy.png");
		TextureFiles.Cards_hell[7] = new com.banana4apps.halloween.candies.UTexture("c_hallo_rose.png");

		TextureFiles.hint_start = new com.banana4apps.halloween.candies.UTexture("start_text.png");
		TextureFiles.hint_end = new com.banana4apps.halloween.candies.UTexture("end_text.png");

		GameValues.Player = new TextureRegionDrawable(new TextureRegion(new Texture("icon_avatar.png")));
		GameValues.setPlayer(GameValues.PlayerID);

		for (int i = 0; i < 8; i++)
		{
			GameValues.Monster_Cards_Price[i][0] = 50 + 30*i;
			GameValues.Monster_Cards_Price[i][1] = 40 + 20*i;
			GameValues.Monster_Cards_Price[i][2] = 20 + 9 *i;
		}

		for (int i = 0; i < 8; i++)
		{
			GameValues.Player_Cards_Price[i][0] = 60;
			GameValues.Player_Cards_Price[i][1] = 40;
			GameValues.Player_Cards_Price[i][2] = 30;
		}

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		manager = new ScreenManager(camera);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.draw(batch);
	}
}
