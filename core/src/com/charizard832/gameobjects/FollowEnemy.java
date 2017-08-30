package com.charizard832.gameobjects;

import com.badlogic.gdx.Gdx;
import com.charizard832.game.Data;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.helpers.EnemyID;

/**
 * Created by chari on 8/5/2016.
 */
public class FollowEnemy extends Enemy {

    public FollowEnemy(GameWorld world, int x, int y){
        super(world, x, y, 15, 35, EnemyID.Follow);
        speedY = 150;
        accelerationSpeedY = 400;
        init();
    }

    public FollowEnemy(GameWorld world, int x, int y, int width, int height){
        super(world, x, y, width, height, EnemyID.Follow);
        speedY = 150;
        accelerationSpeedY = 400;
        init();
    }

    public void doLogic(){
        if(position.y>world.getGameHeight()){
            Data.SCORE++;
            position.y = 0;
            position.x = world.getLegend().getX();
            velocity.y = speedY;
        }else if(collides()){
            if(Data.LIVES > 1) {
                position.y = 0;
                position.x = world.getLegend().getX();
                velocity.y = speedY;
                Data.LIVES--;
            }else
                Data.LIVES = 0;
        }
    }

}
