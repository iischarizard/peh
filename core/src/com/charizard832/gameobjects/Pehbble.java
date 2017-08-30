package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.charizard832.game.Data;
import com.charizard832.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by chari on 8/7/2016.
 */
public class Pehbble extends Entity{

    protected Rectangle boundingRectangle;
    Random r;

    public Pehbble(GameWorld world, int x, int y, int width, int height){
        super(world, x, y, width, height);
        speedY = 25;
        accelerationSpeedY = 10;
        boundingRectangle = new Rectangle();
        r = new Random();
        init();
    }

    public void update(float delta){

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        boundingRectangle.set(position.x, position.y, width, height);
        if(collides()){
            position.y = -100;
            position.x = r.nextInt(world.getGameWidth()-width);
            Data.PEHBBLES++;
            world.prefs.putInteger("pehbbles", Data.PEHBBLES);
            world.prefs.flush();
        }else if(position.y > world.getGameHeight()){
            position.y = -100;
            position.x = r.nextInt(world.getGameWidth()-width);
            speedY = 25;
        }
    }

    public boolean collides() {
        if(world.getGameState() == GameWorld.GameState.RUNNING)
            return (Intersector.overlaps(world.getLegend().getBoundingCircle(), boundingRectangle));

        return false;
    }

}
