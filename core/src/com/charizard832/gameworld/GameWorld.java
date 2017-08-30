package com.charizard832.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.charizard832.game.ActionResolver;
import com.charizard832.game.Data;
import com.charizard832.gameobjects.FollowEnemy;
import com.charizard832.gameobjects.Legend;
import com.charizard832.gameobjects.Sun;
import com.charizard832.helpers.AssetLoader;
import com.charizard832.helpers.EnemyHandler;
import com.charizard832.helpers.AllyHandler;
import com.charizard832.helpers.InputHandler;
import com.charizard832.helpers.Spawner;
import com.charizard832.screens.GameScreen;


/**
 * Created by chari on 6/17/2016.
 */
public class GameWorld {

    private Legend legend;

    private EnemyHandler enemyHandler;
    private AllyHandler allyHandler;

    private Spawner spawner;

    private int gameWidth, gameHeight;

    private GameState gameState;

    private int adCount;

    private ActionResolver ar;


    public enum GameState{
        READY, RUNNING, GAMEOVER
    }
    private int midX;

    public Preferences prefs;


    public GameWorld(int midX, int midY, int gameWidth, int gameHeight, ActionResolver ar){
        this.ar = ar;
        prefs = Gdx.app.getPreferences("PehPreferences");
        this.midX = midX;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        adCount = 4;


        legend = new Legend(this, midX-9, midY+62, 17, 17);
        enemyHandler = new EnemyHandler();
        enemyHandler.addEnemy(new FollowEnemy(this, midX-9, 0, 16, 36));

        allyHandler = new AllyHandler();
        allyHandler.addSun(new Sun(this, 78, 10, 40, 40));

        spawner = new Spawner(this, enemyHandler, allyHandler);

        Data.HIGHSCORE = prefs.getInteger("highscore");
        Data.PEHBBLES = prefs.getInteger("pehbbles");

        gameState = GameState.READY;
    }



    public void update(float delta){
        switch (gameState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            case GAMEOVER:
                updateGameOver(delta);
                break;
        }

    }

    public void updateRunning(float delta){
        legend.update(delta);
        allyHandler.update(delta);
        enemyHandler.update(delta);
        spawner.update(delta);
        if(Data.LIVES == 0){
            AssetLoader.bg.stop();
            AssetLoader.ded.play();
            if(Data.SCORE>Data.HIGHSCORE){
                Data.HIGHSCORE = Data.SCORE;
                prefs.putInteger("highscore", Data.SCORE);
                prefs.flush();
            }
            legend.setRotation(0);
            legend.setVelocity(0, 0);
            adCount++;
            switch (adCount){
                case 5:
                    ar.showOrLoadInterstital();
                    break;
                case 6:
                    adCount = 0;
                    ar.showOrLoadInterstital();
                    break;
            }

            gameState = GameState.GAMEOVER;

        }
    }

    public void updateReady(float delta) {

    }
    public void updateGameOver(float delta){

    }

    public void reset(){
        spawner.reset();
        Data.SCORE = 0;
        Data.LIVES = 1;
        legend.setX(midX-9);
        legend.setVelocity(0, 0);
        legend.setRotation(0);
        enemyHandler.list.clear();
        enemyHandler.addEnemy(new FollowEnemy(this, midX-9, -50, 15, 30));
        while(enemyHandler.list.size()>1){enemyHandler.list.clear();enemyHandler.addEnemy(new FollowEnemy(this, midX-9, -50));}

        allyHandler.list.clear();
        allyHandler.sunList.clear();
        allyHandler.addSun(new Sun(this, 78, 10, 40, 40));

        gameState = GameState.RUNNING;
        AssetLoader.ded.stop();
        AssetLoader.bg.play();
    }


    public boolean isReady() {
        return gameState == GameState.READY;
    }

    public void start() {
        gameState = GameState.RUNNING;
        AssetLoader.bg.play();
    }

    public int getGameWidth() { return gameWidth; }

    public int getGameHeight() { return gameHeight; }

    public Legend getLegend(){
        return legend;
    }

    public GameState getGameState() { return gameState; }

    public EnemyHandler getEnemyHandler() { return enemyHandler; }

    public AllyHandler getAllyHandler() { return allyHandler; }


    public void setGameState(GameState state){ gameState = state; }
}
