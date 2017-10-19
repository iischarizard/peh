package com.charizard832.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.charizard832.gameobjects.Legend;
import com.charizard832.gameobjects.buttons.SimpleButton;
import com.charizard832.gameworld.GameRenderer;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.helpers.AssetLoader;
import com.charizard832.helpers.InputHandler;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zlnor on 8/12/2017.
 */

public class Tutorial {
    private int gameWidth, gameHeight;
    private int midPointY, midPointX;

    private Legend legend;

    private GameWorld world;

    private int flags;

    private InputHandler ih;

    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private BitmapFont font;
    private GlyphLayout tutText;

    private int textX, textY, text2X, text2Y, text3X, text3Y;

    private String text, text2, text3;

    private float enemyX, enemyY, enemySpeed;

    public Tutorial(GameWorld world, GameRenderer gameRenderer, int gameHeight, int midPointY, int midPointX){
        this.world = world;
        this.legend = world.getLegend();
        this.gameWidth = world.getGameWidth();
        this.gameHeight = world.getGameHeight();
        this.midPointY = midPointY;
        this.midPointX = midPointX;

        this.cam = gameRenderer.getCam();
        this.shapeRenderer = gameRenderer.getShapeRenderer();
        this.batcher = gameRenderer.getBatcher();
        this.font = gameRenderer.getFont();
        font.getData().setScale(0.7f, 0.7f);

        InputHandler ih = (InputHandler) Gdx.input.getInputProcessor();
        this.ih = ih;


        textX = 20;
        textY = midPointY - 30;
        text2X = 30;
        text2Y = midPointY-20;
        text3X = 5;
        text3Y = midPointY - 20;
        flags = 0;
        text = "Welcome to Peh!";
        text2= "";
        text3= "";

        enemyX = midPointX-9;
        enemyY = 0;
        enemySpeed = 150;
    }

    public int getFlag(){return flags;}

    public void progress(){
        flags++;
    }

    public void update(float delta){
        switch (flags){
            case 3:
                legend.update(delta);
                break;
            case 6:
                legend.update(delta);

                enemyY+=enemySpeed*delta;
                enemySpeed+=400*delta;
                break;
        }

    }
    public void render(float delta, float runTime){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(55 / 255.0f, 180 / 255.0f, 255 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 76);
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 76, 136, 60);
        shapeRenderer.end();
        batcher.begin();
        font.draw(batcher, text, textX, textY);
        font.draw(batcher, text2, text2X, text2Y);
        font.draw(batcher, text3, text3X, text3Y);
        switch (flags){
            case 0:
                textX = 20;
                break;
            case 1:
                textY = midPointY - 50;
                textX = 35;
                text2X = 38;
                text2Y = midPointY - 20;
                text3X = 20;
                text3Y = midPointY - 5;
                text = "This is Peh!";
                text2 = "You will be";
                text3 = "controlling Peh";
                legend.setX(midPointX-9);
                batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                break;
            case 2:
                textX = 28;
                text2X = 45;
                text2Y = midPointY - 35;
                text = "Tap to start";
                text2 = "rolling!";
                text3 = "";
                batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                break;
            case 3:
                textX = 37;
                text = "Great Job!";
                text2 = "";
                text3 = "";
                switch (legend.getRotation()) {
                    case 0:
                        batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                    case 1:
                        batcher.draw(AssetLoader.legendAnimationRight.getKeyFrame(runTime), legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                    case 2:
                        batcher.draw(AssetLoader.legendAnimationLeft.getKeyFrame(runTime), legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                }
                break;
            case 4:
                textX = 50;
                text2Y = midPointY - 20;
                text2X = 35;
                text3X = 25;
                text = "Oh no!";
                text2 = "A Pehnemmy";
                text3 = "has appeared!";
                batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                batcher.draw(AssetLoader.enemy1, legend.getX(), 0, 15, 35);
                break;
            case 5:
                text2X = 15;
                text = "Quick!";
                text2 = "Tap to roll away!!";
                text3 = "";
                enemyX = legend.getX();
                batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                batcher.draw(AssetLoader.enemy1, enemyX, 0, 15, 35);
                break;
            case 6:
                switch (legend.getRotation()) {
                    case 0:
                        batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                    case 1:
                        batcher.draw(AssetLoader.legendAnimationRight.getKeyFrame(runTime), legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                    case 2:
                        batcher.draw(AssetLoader.legendAnimationLeft.getKeyFrame(runTime), legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                        break;
                }
                batcher.draw(AssetLoader.enemy1, enemyX, enemyY, 15, 35);
                break;
            case 7:
                textX = 40;
                text2Y = midPointY - 20;
                text3Y = midPointY - 5;
                text2X = 25;
                text3X = 26;
                text = "Great job!";
                text2 = "Dodge enemies";
                text3 = "to get points!";
                batcher.draw(AssetLoader.legend, legend.getX(), legend.getY(), legend.getWidth(), legend.getHeight());
                break;
            case 8:
                textX=23;
                text2X= 19;
                text2Y = midPointY-35;
                text="Catch Sunpehs";
                text2="for extra lives!";
                text3="";
                batcher.draw(AssetLoader.sun, midPointX-AssetLoader.sun.getRegionWidth()/2, midPointY+10, AssetLoader.sun.getRegionWidth(), AssetLoader.sun.getRegionHeight());
                break;
            case 9:
                textX=20;
                text2X= 21;
                text3Y = midPointY-20;
                text3X = 38;
                text="Catch Pehbbles";
                text2="to buy stuff in";
                text3="the shop!";
                batcher.draw(AssetLoader.pehbble, midPointX-AssetLoader.pehbble.getRegionWidth()/2, midPointY+10, AssetLoader.pehbble.getRegionWidth(), AssetLoader.pehbble.getRegionHeight());
                break;
            case 10:
                textX=16;
                text2X= 25;
                text3X = 35;
                text="Tap the Fadepeh";
                text2="button to set";
                text3="a teleport!";
                batcher.draw(AssetLoader.legendFade, 136/2 - (AssetLoader.legend.getRegionWidth())+60, midPointY+10, 20, 20);
                batcher.draw(AssetLoader.legendFade, midPointX-9, legend.getY(), legend.getWidth(), legend.getHeight());
                break;
            case 11:
                textX=33;
                text2X= 30;
                text3X = 55;
                text="Tap the peh";
                text2="button to go";
                text3="to it!";
                batcher.draw(AssetLoader.legendFade, 136/2 - (AssetLoader.legend.getRegionWidth())+60, midPointY+10, 20, 20);
                batcher.draw(AssetLoader.legend, 136/2 - (AssetLoader.legend.getRegionWidth())+60, midPointY+30, 20, 20);
                batcher.draw(AssetLoader.legend, midPointX-9, legend.getY(), legend.getWidth(), legend.getHeight());
                break;
            case 12:
                textX= 40;
                text2X = 25;
                text = "Good luck!";
                text2 = "Tap to start!";
                text3 = "";
                ih.getMenuButtons().get(0).draw(batcher);
                break;
        }


        batcher.end();

    }


}