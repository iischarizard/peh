package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.charizard832.gameworld.GameWorld;

/**
 * Created by chari on 6/17/2016.
 */
public class Legend extends Entity{

    private int rotation;

    private Circle boundingCircle;

    private boolean stopped;

    public Legend(GameWorld world, float x, float y, int width, int height){
        super(world, (int)x, (int)y, width, height);
        speedX = 100;
        rotation = 0;
        boundingCircle = new Circle();
        init();
        stopped = false;
    }

    public void update(float delta) {

        //velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        boundingCircle.set(position.x+8.5F, position.y+8.5F, 8.5F);
        if(position.x-speedX<0-speedX) {
            setVelocity(0, 0);
            stopped = true;
            rotation = 0;
            position.x=1;
        }
        if(position.x+speedX>world.getGameWidth()+speedX-width){
            stopped = true;
            setVelocity(0, 0);
            rotation = 0;
            position.x = world.getGameWidth()-1-width;
        }

    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public Vector2 getPosition(){return position;}

    public void setPosition(Vector2 position){
        this.position = position;
    }

    public int getRotation(){ return rotation; }

    public Circle getBoundingCircle() { return boundingCircle; }

    public void setRotation(int r){rotation = r;}


}
