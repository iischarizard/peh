package com.charizard832.helpers;

import com.charizard832.game.Data;
import com.charizard832.gameobjects.Pehbble;
import com.charizard832.gameobjects.RandomEnemy;
import com.charizard832.gameobjects.Sun;
import com.charizard832.gameworld.GameWorld;

/**
 * Created by chari on 8/7/2016.
 */
public class Spawner {

    private boolean[] spawned = {false, false, false, false, false, false, false, false, false, false, false, false};
    private boolean[] pehbble = {false, false, false};

    private EnemyHandler handler;
    private AllyHandler allyHandler;
    private GameWorld world;

    public Spawner(GameWorld world, EnemyHandler handler, AllyHandler allyHandler){
        this.handler = handler;
        this.allyHandler = allyHandler;
        this.world = world;
        reset();
    }

    public void update(float delta){

        if(Data.SCORE == 10&&!spawned[0]){
            spawned[0] = true;
            handler.addEnemy(new RandomEnemy(world, 0, 0));
            allyHandler.addPehbble(new Pehbble(world, 9, 30, 12, 7));

        }
        if(Data.SCORE == 25&&!spawned[1]){
            spawned[1] = true;
            handler.addEnemy(new RandomEnemy(world, 0, 0, 20, 10));
            allyHandler.addPehbble(new Pehbble(world, 9, 30, 12, 7));
        }
        if(Data.SCORE == 50&&!spawned[2]){
            spawned[2] = true;
            allyHandler.addSun(new Sun(world, 78, 10, 40, 40));
            allyHandler.addPehbble(new Pehbble(world, 9, 30, 12, 7));
        }

    }

    public void reset(){
        for(int i = 0; i < spawned.length; i++)
            spawned[i] = false;

    }

}
