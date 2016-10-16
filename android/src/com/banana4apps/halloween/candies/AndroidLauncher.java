package com.banana4apps.halloween.candies;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.banana4apps.halloween.candies.Screens.WinScreen;

import org.wazzapps.sdk.WazzAdvertising;
import org.wazzapps.sdk.WazzTrackedActivity;

import java.io.IOException;
import java.io.InputStream;

public class AndroidLauncher extends AndroidApplication implements WazzTrackedActivity, com.banana4apps.halloween.candies.Screens.ScreenManager.ScreenCallback {

	GAdv googleAdv;
	WazzAdvertising wazzAdvertising;
	com.banana4apps.halloween.candies.MyGdxGame game;

	Share share;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		setContentView(R.layout.main_layout);

		game = new com.banana4apps.halloween.candies.MyGdxGame();
		View gameView = initializeForView(game, config);
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		params1.addRule(RelativeLayout.ABOVE, 1337);
		gameView.setLayoutParams(params1);

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.gameContainer);
		layout.addView(gameView);
		super.onCreate(savedInstanceState);



		ValueStorage.Initialize(this);

		GameValues.Canes = ValueStorage.getIntVar("canes");
		GameValues.Candy_1 = ValueStorage.getIntVar("candy_1");
		GameValues.Candy_2 = ValueStorage.getIntVar("candy_2");
		GameValues.Candy_3 = ValueStorage.getIntVar("candy_3");

//		GameValues.Canes = 100;
//		GameValues.Candy_1 = 100;
//		GameValues.Candy_2 = 100;
//		GameValues.Candy_3 = 100;

		GameValues.progress = ValueStorage.getIntVar("Progress");

		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_frankenstein[i] =
					ValueStorage.getIntVar("MC_1"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_scarecrow[i] =
					ValueStorage.getIntVar("MC_2"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_werewolf[i] =
					ValueStorage.getIntVar("MC_3"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_vampire[i] =
					ValueStorage.getIntVar("MC_4"+i) == 1;

		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_ghost[i] =
					ValueStorage.getIntVar("MC_5"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_mummy[i] =
					ValueStorage.getIntVar("MC_6"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_witch[i] =
					ValueStorage.getIntVar("MC_7"+i) == 1;
		for (int i = 0; i < 4; i++)
			GameValues.openedCards.o_rose[i] =
					ValueStorage.getIntVar("MC_8"+i) == 1;

		for (int i = 0; i < 6; i++)
			if (ValueStorage.getIntVar("PlayerB"+i) == 1)
				GameValues.bought_players[i] = true;

		com.banana4apps.halloween.candies.Screens.MainScreen.moreAction = new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				showMore();
			}
		};

		int p = ValueStorage.getIntVar("PlayerID");
		if (p != 0)
		GameValues.PlayerID = p;

		share = new Share(this);
		sharerText = AndroidLauncher.this.getString(R.string.iplay) + " " +
						AndroidLauncher.this.getString(R.string.inapp) + " " +
						AndroidLauncher.this.getString(R.string.app_name) + " " +
						AndroidLauncher.this.getString(R.string.hash) + " " +
						getResources().getString(R.string.share_link);

		com.banana4apps.halloween.candies.Screens.ScreenManager.callback = this;


		googleAdv = new GAdv(this);
		wazzAdvertising = new WazzAdvertising(this);
		wazzAdvertising.loadAd();
	}

	String sharerText ;

	public Bitmap getBitmapFromAsset(String filePath) {
		AssetManager assetManager = getAssets();

		InputStream istr;
		Bitmap bitmap = null;
		try {
			istr = assetManager.open(filePath);
			bitmap = BitmapFactory.decodeStream(istr);
		} catch (IOException e) {
			// handle exception
		}

		return bitmap;
	}

	public void showMore()
	{
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				final String appPackageName = "VooApps";
				try {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:" + appPackageName)));
				} catch (ActivityNotFoundException anfe) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/search?q=pub:" + appPackageName)));
				}
			}
		});
	}

	@Override
	public String getActivityName() {
		return null;
	}

	int count = 0;
	@Override
	public void showAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (count % 3 != 1)
					if (wazzAdvertising.isAdReady())
						wazzAdvertising.showAd();
					else googleAdv.showAD(150);
				count++;
			}
		});
	}

	@Override
	public void savePoints() {
		 ValueStorage.setVar("canes",   ""+  GameValues.Canes);
		 ValueStorage.setVar("candy_1", ""+GameValues.Candy_1);
		 ValueStorage.setVar("candy_2", "" + GameValues.Candy_2);
		 ValueStorage.setVar("candy_3", "" + GameValues.Candy_3);

		for (int i = 0; i < 4; i++) ValueStorage.setVar("MC_1"+i, ""+(GameValues.openedCards.o_frankenstein[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_2"+i, ""+(GameValues.openedCards.o_scarecrow[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_3"+i, ""+(GameValues.openedCards.o_werewolf[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_4"+i, ""+(GameValues.openedCards.o_vampire[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_5"+i, ""+(GameValues.openedCards.o_witch[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_6"+i, ""+(GameValues.openedCards.o_ghost[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_7"+i, ""+(GameValues.openedCards.o_mummy[i] ? 1 : 0));
		for (int i = 0; i < 4; i++)	ValueStorage.setVar("MC_8"+i, ""+(GameValues.openedCards.o_rose[i] ? 1 : 0));

		for (int i = 0; i < 6; i++) ValueStorage.setVar("PlayerB"+i, ""+(GameValues.bought_players[i] ? 1 : 0));

		ValueStorage.setVar("PlayerID", ""+GameValues.PlayerID);
		ValueStorage.setVar("Progress", ""+GameValues.progress);
	}

	@Override
	public void shareCard(int monster, int level) {

		String card = "c_";
		if (level == 0) card += "old_";
		if (level == 1) card += "norm_";
		if (level == 2) card += "gold_";
		if (level == 3) card += "hallo_";

		if (monster == 0) card += "franken";
		if (monster == 1) card += "scarecrow";
		if (monster == 2) card += "werwolf";
		if (monster == 3) card += "vampire";
		if (monster == 4) card += "witch";
		if (monster == 5) card += "ghost";
		if (monster == 6) card += "mummy";
		if (monster == 7) card += "rose";

		card += ".png";

		final String finalCard = card;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
								share.shareButtons(
						getBitmapFromAsset(finalCard),
						"share", sharerText);
			}});
	}

	@Override
	public void onBackPressed()
	{
		if (!game.manager.goBack())
			super.onBackPressed();
	}
}
