package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.charizard832.gameworld.GameWorld;

/**
 * Created by chari on 8/7/2016.
 */
public class Entity {



    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 acceleration;

    protected int width, height, speedX, speedY, accelerationSpeedX, accelerationSpeedY;

    protected GameWorld world;

    public Entity(GameWorld world, int x, int y, int width, int height){
        this.width = width;
        this.height = height;
        this.world = world;
        position = new Vector2(x, y);

    }
    public void init(){
        velocity = new Vector2(speedX, speedY);
        acceleration = new Vector2(accelerationSpeedX, accelerationSpeedY);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setY(int y) { position.y = y; }

    public void setX(int x) { position.x = x; }

    public int getSpeedX() { return speedX; }
    public int getSpeedY() { return speedY; }

    public void setVelocity(int x, int y){velocity.x = x; velocity.y = y;}
}
