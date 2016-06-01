package com.sweter.game.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Created by pawel on 31.05.16.
 */
public class Path {
    public ArrayList steps = new ArrayList<Pair<Integer, Integer>>();
    public float finalTargetx;
    public float finalTargety;
    private Level lvl;
    public Path(Level lvl){
        this.lvl = lvl;
    }

    public int getLength(){
        return steps.size();
    }

    public Pair<Integer, Integer> getStep(int index){
        return (Pair <Integer, Integer>) steps.get(index);
    }

    public int getX(int index){
        return getStep(index).getKey();
    }
    public int getY(int index){
        return getStep(index).getValue();
    }

    public void appendStep(int x, int y){
        steps.add(new Pair<Integer, Integer>(x, y));
    }

    public void prependStep(int x, int y){
        steps.add(0, new Pair<Integer, Integer>(x, y));
    }

    public boolean contains(int x, int y){
        return steps.contains(new Pair<Integer, Integer>(x, y));
    }
}
