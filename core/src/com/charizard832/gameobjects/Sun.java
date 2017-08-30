package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.charizard832.game.Data;
import com.charizard832.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by chari on 8/7/2016.
 */
public class Sun extends Entity{

    private Circle boundingCircle;
    private float counter = 0;
    Random r;


    public Sun(GameWorld world, int x, int y, int width, int height){
        super(world, x, y, width, height);
        speedY = 200;
        boundingCircle = new Circle();
        r = new Random();
        init();
    }

    public void update(float delta){
        if(counter<600)
            counter++;
        else {
            position.add(velocity.cpy().scl(delta));
            boundingCircle.set(position.x + width/2, position.y + height/2, width/2);
            if (collides()) {
                counter = 0;
                Data.LIVES++;
                position.y = 10;
                position.x = r.nextInt(world.getGameWidth()-width);
            }else if(position.y>world.getGameHeight()){
                counter = 0;
                position.y = 10;
                position.x = r.nextInt(world.getGameWidth()-width);
            }
        }
    }
    public boolean collides() {
        if(world.getGameState() == GameWorld.GameState.RUNNING)
            return (Intersector.overlaps(world.getLegend().getBoundingCircle(), boundingCircle));

        return false;
    }
    public void setCounter(float i){counter = i;}

}
