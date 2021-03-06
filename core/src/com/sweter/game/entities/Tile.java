package com.sweter.game.entities;

/**
 * Created by pawel on 01.06.16.
 */
public class Tile {
    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("map coords: ");
        sb.append(x);
        sb.append(" ");
        sb.append(y);
        sb.append(" pixel coors: " + 4*x + " " + 4*y + " x " + (4*(x+1)) + " " + (4*(y+1)));
        return sb.toString();
    }
}
