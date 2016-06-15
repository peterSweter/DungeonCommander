package com.sweter.game.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.util.Pair;

/**
 * Created by pawel on 31.05.16.
 */
public class Path implements Iterable<Tile>{
    public ArrayList <Tile> steps = new ArrayList<Tile>();
    public float finalTargetx;
    public float finalTargety;
    private Level lvl;
    public int x;

    public Path(Level lvl){
        this.lvl = lvl;
        this.x=0;
    }

    public int getLength(){
        return steps.size();
    }

    public Tile getStep(int index){
        return steps.get(index);
    }

    public int getX(int index){
        return getStep(index).getX();
    }
    public int getY(int index){
        return getStep(index).getY();
    }

    public void appendStep(int x, int y){
        steps.add(new Tile(x, y));
    }

    public void prependStep(int x, int y){
        steps.add(0, new Tile(x, y));
    }

    public boolean contains(int x, int y){
        return steps.contains(new Tile(x, y));
    }

    @Override
    public Iterator<Tile> iterator() {
        return new PathIterator();
    }

    private class PathIterator implements Iterator<Tile>{
        int position = 0;
        @Override
        public boolean hasNext() {
            if(position < getLength())
                return true;
            return false;
        }

        @Override
        public Tile next() {
            position++;
            return steps.get(position-1);
        }
    }
}
