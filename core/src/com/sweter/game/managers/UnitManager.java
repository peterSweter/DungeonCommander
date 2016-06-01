package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Path;
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
        for(Unit u : units){
            u.update(delta);
        }

    }

    public void schapeRender(ShapeRenderer sr){
      //  Pair<Integer, Integer> currentTile = mainCharacterPath.getStep(0);
       /* int x = 0;
        while(x < mainCharacterPath.getLength()-1){
            Pair<Integer, Integer> currentTile = mainCharacterPath.getStep(x);
            Pair<Integer, Integer> nextTile = mainCharacterPath.getStep(x+1);
           // mainCharacter.target.set(new Vector3((nextTile.getKey()*32+32/2), (nextTile.getValue()*32+32/2), 0));

            if(mainCharacter.getPosition().x >= nextTile.getKey()*32 && mainCharacter.getPosition().y >= nextTile.getValue()*32){
                x++;
            }

        }*/


        for(Unit u : units){
            u.sRender(sr);
        }
    }


    public Unit getActiveCharacter(){
        return activeUnit;
    }
}
