package com.charizard832.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by chari on 6/17/2016.
 */
public class AssetLoader {

    public static Texture texture;
    //public static TextureRegion bg, grass;

    public static Animation<TextureRegion> legendAnimationRight, legendAnimationLeft;
    public static TextureRegion legend, legendDown, legendRight, legendLeft, legendFade;

    public static TextureRegion sun, pehbble;

    public static TextureRegion enemy1;

    public static TextureRegion peh;

    public static TextureRegion playButtonUp, playButtonDown, tutButtonUp, tutButtonDown, pauseButtonUp, pauseButtonDown, playButton2Up, playButton2Down;


    public static Music bg, ded;

    public static void load() {

        bg = Gdx.audio.newMusic(Gdx.files.internal("peh test 2.wav"));
        bg.setLooping(true);

        ded = Gdx.audio.newMusic(Gdx.files.internal("peh ded.wav"));
        ded.setLooping(true

        );

        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

       // bg = new TextureRegion(texture, 0, 0, 136, 43);
       // bg.flip(false, true);

       // grass = new TextureRegion(texture, 0, 43, 143, 11);
       // grass.flip(false, true);

        legend = new TextureRegion(texture, 0, 0, 17, 17);
        legend.flip(false, true);

        legendDown = new TextureRegion(texture, 34, 0, 17, 17);
        legendDown.flip(false, true);

        legendRight = new TextureRegion(texture, 17, 0, 17, 17);
        legendRight.flip(false, true);

        legendLeft = new TextureRegion(texture, 51, 0, 17, 17);
        legendLeft.flip(false, true);

        legendFade = new TextureRegion(texture, 37, 17, 17, 17);
        legendFade.flip(false, true);

        sun = new TextureRegion(texture, 0, 17, 37, 36);
        sun.flip(false, true);

        pehbble = new TextureRegion(texture, 68, 0, 12, 7);
        pehbble.flip(false, true);

        enemy1 = new TextureRegion(texture, 0, 53, 20, 35);
        enemy1.flip(false, true);

        peh = new TextureRegion(texture, 80, 0, 84, 26);
        peh.flip(false, true);

        playButtonUp = new TextureRegion(texture, 165, 0, 27, 21);
        playButtonUp.flip(false, true);

        playButtonDown = new TextureRegion(texture, 192, 0, 27, 21);
        playButtonDown.flip(false, true);

        tutButtonUp = new TextureRegion(texture, 165, 21, 34, 9);
        tutButtonUp.flip(false, true);

        tutButtonDown = new TextureRegion(texture, 165, 30, 34, 9);
        tutButtonDown.flip(false, true);

        playButton2Up = new TextureRegion(texture, 20, 53, 15, 17);
        playButton2Up.flip(false, true);

        playButton2Down = new TextureRegion(texture, 35, 53, 15, 17);
        playButton2Down.flip(false, true);

        pauseButtonUp = new TextureRegion(texture, 50, 53, 15, 17);
        pauseButtonUp.flip(false, true);

        pauseButtonDown = new TextureRegion(texture, 65, 53, 15, 17);
        pauseButtonDown.flip(false, true);

        TextureRegion[] legendsRight = { legend, legendRight, legendDown, legendLeft };
        legendAnimationRight = new Animation<TextureRegion>(0.09f, legendsRight);
        legendAnimationRight.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion[] legendsLeft = { legend, legendLeft, legendDown, legendRight };
        legendAnimationLeft = new Animation<TextureRegion>(0.09f, legendsLeft);
        legendAnimationLeft.setPlayMode(Animation.PlayMode.LOOP);


    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bg.dispose();
        ded.dispose();
        texture.dispose();
    }

}
