package com.charizard832.game;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.charizard832.game.LegendGame;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
public class AndroidLauncher extends AndroidApplication implements ActionResolver{

	private InterstitialAd mInterstitialAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new LegendGame(this), config);

		MobileAds.initialize(this, "ca-app-pub-3435145263390263~2671531433");
		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-3435145263390263/5624997835");
		mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("057AA0F1628ED924BD2AB48A366192F4").addTestDevice("895FFF37FE79176E2CD72F12109AE2ED").build());
		showOrLoadInterstital();
		//MobileAds.initialize(this, "ca-app-pub-3435145263390263/5624997835");
	}
	public void showOrLoadInterstital(){
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					if (mInterstitialAd.isLoaded()) {
						mInterstitialAd.show();
					}
					else {
						AdRequest interstitialRequest = new AdRequest.Builder().build();
						mInterstitialAd.loadAd(interstitialRequest);
					}
				}
			});
		} catch (Exception e) {
		}
	}
}
