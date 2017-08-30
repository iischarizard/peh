package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.charizard832.gameworld.GameWorld;

/**
 * Created by zlnor on 8/9/2017.
 */

public class LegendFade extends Entity {
    public LegendFade(GameWorld world, float x, float y, int width, int height){
        super(world, (int)x, (int)y, width, height);
    }

    public void setPosition(Vector2 position){this.position = position;}

    public Vector2 getPosition(){return position;}
}
