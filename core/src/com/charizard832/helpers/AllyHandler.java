package com.charizard832.helpers;

import com.charizard832.gameobjects.LegendFade;
import com.charizard832.gameobjects.Pehbble;
import com.charizard832.gameobjects.Sun;

import java.util.LinkedList;

/**
 * Created by chari on 8/7/2016.
 */
public class AllyHandler {

    public LinkedList<Pehbble> list;
    public LinkedList<Sun> sunList;

    public AllyHandler(){
        list = new LinkedList<Pehbble>();
        sunList = new LinkedList<Sun>();
    }

    public void update(float delta){
        for(int i = 0; i < list.size(); i++){
            Pehbble temp = list.get(i);
            temp.update(delta);
        }
        for(int i = 0; i < sunList.size(); i++){
            Sun temp = sunList.get(i);
            temp.update(delta);
        }
    }

    public void addPehbble(Pehbble pehbble){
        list.add(pehbble);
    }

    public void removePehbble(Pehbble pehbble){
        list.remove(pehbble);
    }

    public void addSun(Sun sun){
        sunList.add(sun);
    }

    public void removeSun(Sun sun){
        sunList.remove(sun);
    }


}
