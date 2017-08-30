package com.charizard832.gameobjects;

import com.charizard832.game.Data;
import com.charizard832.gameworld.GameWorld;
import com.charizard832.helpers.EnemyID;

import java.util.Random;

/**
 * Created by chari on 8/6/2016.
 */
public class RandomEnemy extends Enemy {

    Random r;

    public RandomEnemy(GameWorld world, int x, int y){
        super(world, x, y, 10, 25, EnemyID.Random);
        speedY = 150;
        accelerationSpeedY = 0;
        init();
        r = new Random();
    }
    public RandomEnemy(GameWorld world, int x, int y, int width, int height){
        super(world, x, y, width, height, EnemyID.Random);
        speedY = 150;
        accelerationSpeedY = 0;
        init();
        r = new Random();
    }

    public void doLogic(){
        if(position.y>world.getGameHeight()){
            Data.SCORE++;
            position.y = 0;
            position.x = r.nextInt(world.getGameWidth()-width);
            velocity.y = speedY;
        }else if(collides()){
            if(Data.LIVES > 1) {
                position.y = 0;
                position.x = r.nextInt(world.getGameWidth()-width);
                velocity.y = speedY;
                Data.LIVES--;
            }else
                Data.LIVES = 0;
        }
    }
}
