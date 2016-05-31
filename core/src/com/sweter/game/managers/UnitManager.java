package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sweter.game.entities.Unit;

import java.util.ArrayList;

/**
 * Created by peter on 5/31/16.
 */
public class UnitManager {

    ArrayList<Unit> units;
    ArrayList<Unit> alies;
    ArrayList<Unit> enemies;

    public Unit mainCharacter;
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
        for(Unit u : units){
            u.sRender(sr);
        }
    }


    public Unit getActiveCharacter(){
        return activeUnit;
    }
}
