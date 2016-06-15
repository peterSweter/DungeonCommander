package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sweter.game.entities.AxeMan;
import com.sweter.game.entities.MainCharacter;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.SwordsMan;
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

    public Unit mainCharacter, axeMan, swordsMan;
    public Path mainCharacterPath;
    public int x = 0;
    Unit activeUnit;

    public  UnitManager(){
        units = new ArrayList<Unit>();
        alies = new ArrayList<Unit>();
        enemies = new ArrayList<Unit>();


        mainCharacter = new MainCharacter(320,200);
        swordsMan = new SwordsMan(370,200);
        axeMan= new AxeMan(320,250);

        activeUnit = mainCharacter;
        units.add(mainCharacter);
        units.add(axeMan);
        units.add(swordsMan);
    }

    public ArrayList<Unit> getUnits(){
        return units;
    }

    public void render(SpriteBatch sb, BitmapFont font){

        for(Unit u : units){
            u.render(sb, font);
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

    public void setActiveUnit(Unit  unit){
        this.activeUnit = unit;
    }
}
