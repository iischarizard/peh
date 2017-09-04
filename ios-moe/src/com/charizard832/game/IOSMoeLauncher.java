package com.charizard832.game;

import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;
import org.moe.natj.general.Pointer;
import com.charizard832.game.LegendGame;

import apple.uikit.c.UIKit;

public class IOSMoeLauncher extends IOSApplication.Delegate implements ActionResolver{

    protected IOSMoeLauncher(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = false;
        return new IOSApplication(new LegendGame(this), config);
    }

    public static void main(String[] argv) {
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
    }
    public void showOrLoadInterstital(){}
}
