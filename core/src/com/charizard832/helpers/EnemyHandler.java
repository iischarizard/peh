package com.charizard832.helpers;

import com.charizard832.gameobjects.Enemy;

import java.util.LinkedList;

/**
 * Created by chari on 8/5/2016.
 */
public class EnemyHandler {

    public LinkedList<Enemy> list;

    public EnemyHandler(){
        list = new LinkedList<Enemy>();
    }

    public void update(float delta){
        for(int i = 0; i < list.size(); i++){
            Enemy temp = list.get(i);
            temp.update(delta);
        }
    }

    public void addEnemy(Enemy enemy){
        list.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        list.remove(enemy);
    }


}
