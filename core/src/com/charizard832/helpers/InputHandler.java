package com.charizard832.helpers;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.charizard832.game.Data;
import com.charizard832.game.LegendGame;
import com.charizard832.gameobjects.Legend;
import com.charizard832.gameobjects.LegendFade;
import com.charizard832.gameobjects.buttons.SimpleButton;
import com.charizard832.gameworld.GameRenderer;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.screens.GameScreen;
import com.charizard832.screens.TutorialScreen;

/**
 * Created by chari on 6/17/2016.
 */
public class InputHandler implements InputProcessor {

    private Legend legend;
    private GameWorld world;
    private float scaleFactorX;
    private float scaleFactorY;
    private int midPointX;
    private int midPointY;

    private List<SimpleButton> menuButtons, gameButtons;
    private SimpleButton playButton, fade, fadeSnap, tutButton;

    private LegendFade lf;
    public boolean dir, beginDir;

    private GameScreen gs;

    private boolean tutorial;

    private TutorialScreen ts;

    public InputHandler(GameWorld world, float scaleFactorX,
                        float scaleFactorY, int midPointX, int midPointY, GameScreen gs) {

        ts = null;
        this.gs = gs;
        tutorial = false;
        dir = false;
        beginDir = true;

        this.midPointX = midPointX;
        this.midPointY = midPointY;
        // int midPointY = world.getMidPointY();

        menuButtons = new ArrayList<SimpleButton>();
        gameButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(136/2 - (AssetLoader.playButtonDown.getRegionWidth()), midPointY, 27*2, 21*2, AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        fade = new SimpleButton(136/2 - (AssetLoader.legend.getRegionWidth())+60, midPointY+10, 20, 20, AssetLoader.legendFade, AssetLoader.legendFade);
        fadeSnap = new SimpleButton(136/2 - (AssetLoader.legend.getRegionWidth())+60, midPointY+30, 20, 20, AssetLoader.legend, AssetLoader.legend);
        tutButton = new SimpleButton(136/2 - (AssetLoader.tutButtonDown.getRegionWidth()), midPointY+1+AssetLoader.playButtonDown.getRegionHeight()*2, 24*2, 18*2, AssetLoader.tutButtonUp, AssetLoader.tutButtonDown);
        menuButtons.add(playButton);
        menuButtons.add(tutButton);
        gameButtons.add(fade);
        gameButtons.add(fadeSnap);

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        this.world = world;
        this.legend = world.getLegend();

        lf = new LegendFade(world, legend.getX(), legend.getY(), 17, 17);
    }

    public LegendFade getLf(){return lf;};


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if(!tutorial) {
            switch (world.getGameState()) {
                case READY:
                    playButton.isTouchDown(screenX, screenY);
                    tutButton.isTouchDown(screenX, screenY);
                    //world.start();
                    break;
                case RUNNING:

                    if (fade.isTouchDown(screenX, screenY)) {
                        lf.setX((int) legend.getX());
                        return true;
                    }
                    if (fadeSnap.isTouchDown(screenX, screenY)) {
                        legend.setX((int) lf.getX());
                        return true;
                    }
                    if (beginDir) {
                        beginDir = false;
                        if (screenX > legend.getX()) {
                            dir = false;
                            legend.setVelocity(legend.getSpeedX(), 0);
                            legend.setRotation(1);
                        } else {
                            dir = true;
                            legend.setVelocity(-legend.getSpeedX(), 0);
                            legend.setRotation(2);
                        }
                    } else {
                        if(!legend.isStopped()) {
                            if (dir) {
                                dir = false;
                                legend.setVelocity(legend.getSpeedX(), 0);
                                legend.setRotation(1);
                            } else {
                                dir = true;
                                legend.setVelocity(-legend.getSpeedX(), 0);
                                legend.setRotation(2);
                            }
                        }else{
                            legend.setStopped(false);
                            if (screenX > legend.getX()) {
                                dir = false;
                                legend.setVelocity(legend.getSpeedX(), 0);
                                legend.setRotation(1);
                            } else {
                                dir = true;
                                legend.setVelocity(-legend.getSpeedX(), 0);
                                legend.setRotation(2);
                            }
                        }
                    }

                    break;
                case GAMEOVER:
                    playButton.isTouchDown(screenX, screenY);
                    tutButton.isTouchDown(screenX, screenY);
                    break;
            }
        }else{
            switch (ts.getTut().getFlag()){
                case 0:
                    ts.getTut().progress();
                    break;
                case 1:
                    ts.getTut().progress();
                    break;
                case 2:
                    ts.getTut().progress();
                    if (screenX > legend.getX()) {
                        legend.setVelocity(legend.getSpeedX(), 0);
                        legend.setRotation(1);
                    } else {
                        legend.setVelocity(-legend.getSpeedX(), 0);
                        legend.setRotation(2);
                    }
                    break;
                case 3:
                    ts.getTut().progress();
                    break;
                case 4:
                    ts.getTut().progress();
                    break;
                case 5:
                    ts.getTut().progress();
                    if (midPointX > legend.getX()) {
                        legend.setVelocity(legend.getSpeedX(), 0);
                        legend.setRotation(1);
                    } else {
                        legend.setVelocity(-legend.getSpeedX(), 0);
                        legend.setRotation(2);
                    }
                    break;
                case 6:
                    ts.getTut().progress();
                    break;
                case 7:
                    ts.getTut().progress();
                    break;
                case 8:
                    ts.getTut().progress();
                    break;
                case 9:
                    ts.getTut().progress();
                    break;
                case 10:
                    ts.getTut().progress();
                    break;
                case 11:
                    ts.getTut().progress();
                    break;
                case 12:
                    playButton.isTouchDown(screenX, screenY);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        switch(world.getGameState()){
            case READY:

                if(playButton.isTouchUp(screenX, screenY)){world.start();legend.setVelocity(0, 0);}
                if(tutButton.isTouchUp(screenX, screenY)) {
                    ts = gs.setTutorialScreen();
                    tutorial = true;
                }
                break;
            case RUNNING:
                break;
            case GAMEOVER:
                if(playButton.isTouchUp(screenX, screenY)){world.reset();lf.setX((int)legend.getX());beginDir = true;}
                if(tutButton.isTouchUp(screenX, screenY)) {
                    ts = gs.setTutorialScreen();
                    tutorial = true;
                }
                break;
        }
        if(tutorial)
            if(playButton.isTouchUp(screenX, screenY)){gs.setGameScreen();tutorial=false;world.reset();}
        return true;
    }
    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public List<SimpleButton> getMenuButtons(){return menuButtons;}
    public List<SimpleButton> getGameButtons(){return gameButtons;}

}
