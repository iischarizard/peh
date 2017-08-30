package com.charizard832.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.charizard832.game.ActionResolver;
import com.charizard832.game.LegendGame;
import com.charizard832.gameworld.GameRenderer;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.helpers.InputHandler;

/**
 * Created by chari on 6/17/2016.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    private InputHandler ih;
    private LegendGame lg;

    public GameScreen(LegendGame lg) {
        this.lg = lg;

        Gdx.app.log("GameScreen", "Attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);
        int midPointX = (int) (gameWidth / 2);


        world = new GameWorld(midPointX, midPointY, (int)gameWidth, (int)gameHeight, lg.getAr());
        ih = new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight, midPointX, midPointY, this);
        Gdx.input.setInputProcessor(ih);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY, midPointX);



    }

    public TutorialScreen setTutorialScreen(){TutorialScreen ts; ts = new TutorialScreen(world, renderer);lg.setScreen(ts); return ts;}
    public void setGameScreen(){lg.setScreen(this);}
    public InputHandler getInputHandler(){ return ih;}

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
