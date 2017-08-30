package com.charizard832.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.charizard832.game.Data;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.helpers.EnemyID;

/**
 * Created by chari on 8/5/2016.
 */
public class Enemy extends Entity{

    protected EnemyID id;

    protected Rectangle boundingRectangle;

    public Enemy(GameWorld world, int x, int y, int width, int height,EnemyID id){
        super(world, x, y, width, height);
        this.id = id;
        boundingRectangle = new Rectangle();
    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        doLogic();
        boundingRectangle.set(position.x, position.y+10, width, height-10);

    }

    public void doLogic(){
    }

    public EnemyID getId(){return id;}

    public Rectangle getBoundingRectangle() { return boundingRectangle; }
    public boolean collides() {
        if(world.getGameState() == GameWorld.GameState.RUNNING)
            return (Intersector.overlaps(world.getLegend().getBoundingCircle(), boundingRectangle));

        return false;
    }
}
