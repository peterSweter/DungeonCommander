package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sweter.game.entities.MainCharacter;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.Unit;
import com.sweter.game.entities.UnitTypes;


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


        mainCharacter = new MainCharacter(320,200);
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

    public void testingSchapeRender(ShapeRenderer sr){

        for(Unit u : units){
            u.drawAttackRange(sr);

        }
    }

    public Unit getActiveCharacter(){
        return activeUnit;
    }
}
