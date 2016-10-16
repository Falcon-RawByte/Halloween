package com.banana4apps.halloween.candies;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class GAdv extends Application {

    private static String TAG = "GAdv";
    private final int AD_WAITING = 5000;
    private Context myActivity;
    Activity activity;
    public InterstitialAd interstitial;
    private boolean startApp = true;

    public GAdv(Context context)
    {
        myActivity = context;
        activity = (Activity) context;

        interstitial = new InterstitialAd(myActivity);
        interstitial.setAdUnitId(myActivity.getString(R.string.banner_inter));

        final AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        String testers[] = myActivity.getResources().getStringArray(R.array.testersId);
        for (int i = 0; i < testers.length; i++) {
            adRequestBuilder.addTestDevice(testers[i]);
        }
        final AdRequest adRequest = adRequestBuilder.build();

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitial.loadAd(adRequest);
            }

            @Override
            public void onAdLoaded() {
                //hideLoad();
            }
        });
        interstitial.loadAd(adRequest);

        AdView adView = (AdView) activity.findViewById(R.id.adVi);
        final AdRequest.Builder adRequestBannerBuilder = new AdRequest.Builder();
        for (int i = 0; i < testers.length; i++) {
            adRequestBannerBuilder.addTestDevice(testers[i]);
        }

        adRequestBannerBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        final AdRequest adRequestBanner = adRequestBannerBuilder.build();
        if (adView != null)
        adView.loadAd(adRequestBanner);

        Handler adHdl = new Handler();
        adHdl.postDelayed(new Runnable() {
            public void run() {
                //hideLoad();
            }
        }, AD_WAITING);

    }

    public void showAD(int delay)
    {
        if (interstitial != null && interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}

