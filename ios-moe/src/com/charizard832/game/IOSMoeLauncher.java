package com.charizard832.game;

import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;

import org.moe.natj.general.Pointer;
import com.charizard832.game.LegendGame;

import apple.uikit.c.UIKit;

public class IOSMoeLauncher extends IOSApplication.Delegate implements ActionResolver{

    //private GADInterstitial interstitial;
    private IOSApplication application;


    protected IOSMoeLauncher(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = false;
        //GADMobileAds.configureWithApplicationID("ca-app-pub-3435145263390263~2671531433");
        //interstitial = GADInterstitial.alloc().initWithAdUnitID("ca-app-pub-3435145263390263/8241074766");
        //interstitial.loadRequest(GADRequest.request());

        application = new IOSApplication(new LegendGame(this), config);
        return application;

    }

    public static void main(String[] argv) {
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
    }
    public void showOrLoadInterstital(){
        /*if(interstitial.isReady()){
            interstitial.presentFromRootViewController(application.getUIViewController());
        }else{
            interstitial.loadRequest(GADRequest.request());
        }*/

    }
}
