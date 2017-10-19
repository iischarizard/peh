package com.charizard832.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.charizard832.game.Data;
import com.charizard832.gameobjects.Enemy;
import com.charizard832.gameobjects.Legend;
import com.charizard832.gameobjects.LegendFade;
import com.charizard832.gameobjects.Pehbble;
import com.charizard832.gameobjects.Sun;
import com.charizard832.gameobjects.buttons.SimpleButton;
import com.charizard832.helpers.AssetLoader;
import com.charizard832.helpers.EnemyHandler;
import com.charizard832.helpers.EnemyID;
import com.charizard832.helpers.AllyHandler;
import com.charizard832.helpers.InputHandler;

import java.util.List;

/**
 * Created by chari on 6/17/2016.
 */
public class GameRenderer {

    private GameWorld world;


    private OrthographicCamera cam;

    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private BitmapFont font;

    private int midPointY, midPointX;
    private int gameHeight;

    private EnemyHandler enemyHandler;
    private AllyHandler allyHandler;

    private LegendFade lf;

    private InputHandler ih;

    GlyphLayout score, highscore, text;

    private List<SimpleButton> menuButtons, gameButtons;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY, int midPointX){
        this.world = world;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        this.midPointX = midPointX;

        InputHandler ih = (InputHandler) Gdx.input.getInputProcessor();
        this.ih = ih;
        this.menuButtons = ih.getMenuButtons();
        this.gameButtons = ih.getGameButtons();
        this.lf = ih.getLf();

        this.enemyHandler = world.getEnemyHandler();
        this.allyHandler = world.getAllyHandler();

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Pixeled.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 10;
        parameter.flip = true;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1;
        font = generator.generateFont(parameter);
        font.getData().setScale(0.9f, 0.9f);
        score = new GlyphLayout(font, "");
        highscore = new GlyphLayout(font, "");
        text = new GlyphLayout(font, "Touch to start!");
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch getBatcher() {
        return batcher;
    }

    public BitmapFont getFont() {
        return font;
    }
    public void render(float delta, float runTime){
        Legend legend = world.getLegend();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        if(world.getGameState() == GameWorld.GameState.RUNNING||world.getGameState() == GameWorld.GameState.GAMEOVER) {
            shapeRenderer.setColor(55 / 255.0f, 180 / 255.0f, 255 / 255.0f, 1);
            shapeRenderer.rect(0, 0, 136, midPointY + 76);

            shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
            shapeRenderer.rect(0, midPointY + 76, 136, 60);
        } else if(world.getGameState() == GameWorld.GameState.READY) {
            shapeRenderer.setColor(55 / 255.0f, 180 / 255.0f, 255 / 255.0f, 1);
            shapeRenderer.rect(0, 0, 136, 300);
        }

        shapeRenderer.end();



        batcher.begin();
        if(world.getGameState() == GameWorld.GameState.RUNNING||world.getGameState() == GameWorld.GameState.GAMEOVER) {

            for (int i = 0; i < allyHandler.sunList.size(); i++) {
                Sun temp = allyHandler.sunList.get(i);
                batcher.draw(AssetLoader.sun, temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());

            }

            batcher.draw(AssetLoader.legendFade, lf.getX(), lf.getY(), lf.getWidth(), lf.getHeight());
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
            for (int i = 0; i < enemyHandler.list.size(); i++) {
                Enemy temp = enemyHandler.list.get(i);
                if (temp.getId() == EnemyID.Follow) {
                    batcher.draw(AssetLoader.enemy1, temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
                }
                if (temp.getId() == EnemyID.Random) {
                    batcher.draw(AssetLoader.enemy1, temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
                }
            }

            for (int i = 0; i < allyHandler.list.size(); i++) {
                Pehbble temp = allyHandler.list.get(i);
                batcher.draw(AssetLoader.pehbble, temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());

            }
        }

        switch(world.getGameState()){
            case READY:
                batcher.draw(AssetLoader.peh, ((world.getGameWidth()- (int)(AssetLoader.peh.getRegionWidth()*1.25)) / 2), 20, 105, 33);
                font.draw(batcher, text, (world.getGameWidth()- text.width) / 2, world.getGameHeight() /2-20);
                for (SimpleButton button : menuButtons) {
                    button.draw(batcher);
                }
                break;
            case RUNNING:
                for (SimpleButton button : gameButtons) {
                    button.draw(batcher);
                }
                font.draw(batcher, "Score: " + Data.SCORE, 2, 5);
                font.draw(batcher, "Lives: " + Data.LIVES, 2, 20);
                font.draw(batcher, "Pehbbles: " + Data.PEHBBLES, 2, legend.getY()+25);
                break;
            case GAMEOVER:
                for (SimpleButton button : gameButtons) {
                    button.draw(batcher);
                }
                for (SimpleButton button : menuButtons) {
                    button.draw(batcher);
                }
                score.setText(font, "Score: "+ Data.SCORE);
                highscore.setText(font, "High Score: "+Data.HIGHSCORE);
                font.draw(batcher, "Pehbbles: " + Data.PEHBBLES, 2, legend.getY()+25);
                font.draw(batcher, score, (world.getGameWidth()- score.width) / 2, world.getGameHeight() / 2-35);
                font.draw(batcher, highscore, (world.getGameWidth()- highscore.width) / 2, world.getGameHeight() / 2-15);

                break;
        }


        batcher.end();

        /*shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
       // shapeRenderer.circle(gameButtons.get(0)().x, legend.getBoundingCircle().y, legend.getBoundingCircle().radius);
        shapeRenderer.rect(gameButtons.get(0).getBounds().x, gameButtons.get(0).getBounds().y, 32, 32);
        shapeRenderer.end();*/
    }

}
