package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.Tile;
import com.sweter.game.entities.Unit;
import com.sweter.game.interfaces.PathFinder;

import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Created by peter on 5/31/16.
 */
public class UnitManager {

    ArrayList<Unit> units;
    ArrayList<Unit> alies;
    ArrayList<Unit> enemies;

    public Unit mainCharacter;
    public Path mainCharacterPath;
    public int x = 0;
    Unit activeUnit;

    public  UnitManager(){
        units = new ArrayList<Unit>();
        alies = new ArrayList<Unit>();
        enemies = new ArrayList<Unit>();


        mainCharacter = new Unit(320,200,1);
        activeUnit = mainCharacter;
        units.add(mainCharacter);
    }

    public ArrayList<Unit> getUnits(){
        return units;
    }

    public void render(SpriteBatch sb){

        for(Unit u : units){
            u.render(sb);
        }

    }

    public void update(float delta){
        if(mainCharacterPath != null && x < mainCharacterPath.getLength()-1){
            Tile currentTile = mainCharacterPath.getStep(x);
            Tile nextTile = mainCharacterPath.getStep(x+1);
            if(mainCharacterPath != null && x == mainCharacterPath.getLength()-2){
                mainCharacter.target.set(new Vector3(mainCharacterPath.finalTargetx, mainCharacterPath.finalTargety, 0));
            }else {
                mainCharacter.target.set(new Vector3((nextTile.getX() * 32 + 16), (nextTile.getY() * 32 + 16), 0));
            }
            if(mainCharacter.getPosition().x >= nextTile.getX()*32 && mainCharacter.getPosition().y >= nextTile.getY()*32){
                System.out.println("next coords: " + (nextTile.getX()*32+16) + " " + (nextTile.getY()*32 + 16) + " x: " + x);
                x++;
            }
        }

        for(Unit u : units){
            u.update(delta);
        }

    }

    public void schapeRender(ShapeRenderer sr){


        for(Unit u : units){
            u.sRender(sr);
        }
    }


    public Unit getActiveCharacter(){
        return activeUnit;
    }
}
