package com.charizard832.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.charizard832.gameworld.GameRenderer;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.tutorial.Tutorial;

/**
 * Created by zlnor on 8/12/2017.
 */

public class TutorialScreen implements Screen {

    private Tutorial tut;
    private float runtime;


    public TutorialScreen(GameWorld world, GameRenderer gameRenderer){

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);
        int midPointX = (int) (gameWidth / 2);

        tut = new Tutorial(world, gameRenderer, (int) gameHeight, midPointY, midPointX);

    }

    @Override
    public void render(float delta) {
        runtime++;
        tut.update(delta);
        tut.render(delta, runtime);

    }
    public Tutorial getTut(){return tut;}
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
