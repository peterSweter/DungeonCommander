package com.sweter.game.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.AxeMan;
import com.sweter.game.entities.Enemy;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.MainCharacter;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.SwordsMan;
import com.sweter.game.entities.Unit;
import com.sweter.game.entities.UnitTypes;
import com.sweter.game.interfaces.*;


import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Created by peter on 5/31/16.
 */

    // todo reading position of units from tiledmaxp file   //// ypypypypyp we must do that xD saves a lot of trouble
    // todo combat manager and  ?? from collision manager ??

public class UnitManager {

    ArrayList<Unit> units;
    ArrayList<Unit> alies;
    ArrayList<Enemy> enemies;


    public Unit mainCharacter, axeMan, swordsMan;
    public Path mainCharacterPath;
    public int x = 0;
    Unit activeUnit;

    Level level;

    public  UnitManager(Level level){



        this.level = level;

        units = new ArrayList<Unit>();
        alies = new ArrayList<Unit>();
        enemies = new ArrayList<Enemy>();


        mainCharacter = new MainCharacter(320,200);
        swordsMan = new SwordsMan(370,200);
        axeMan= new AxeMan(320,250);


        activeUnit = mainCharacter;

        alies.add(mainCharacter);
        alies.add(axeMan);
        alies.add(swordsMan);


        loadUnits();

        units.addAll(alies);
        units.addAll(enemies);
    }

    public ArrayList<Unit> getUnits(){
        return units;
    }

    public void render(SpriteBatch sb, BitmapFont font){

        for(Unit u : units){
            u.render(sb, font);
        }

    }

    public int update(float delta){

        for(Unit u : units){
            u.update(delta);
        }
        if(alies.isEmpty()){
            return -1;
        }

        if(enemies.isEmpty()){
            return 1;
        }

        return 0;
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
        if(!unit.isAlive())return;
        this.activeUnit = unit;
    }

    public void loadUnits(){
        MapObjects objects = level.tiledMap.getLayers().get("Units").getObjects();
        for(MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
               // System.out.println("TEEEEEEEEST:"+object.getName());
                // System.out.println( "walls: "  + ((RectangleMapObject) object).getRectangle().getX() + " " +  ((RectangleMapObject) object).getRectangle().getY());

                switch(object.getName().charAt(0)){
                    case 'm':
                        mainCharacter.setPosition(((RectangleMapObject) object).getRectangle().getX(),((RectangleMapObject) object).getRectangle().getY());
                        break;
                    case 'a':
                        axeMan.setPosition(((RectangleMapObject) object).getRectangle().getX(),((RectangleMapObject) object).getRectangle().getY());
                        break;
                    case 's':
                        swordsMan.setPosition(((RectangleMapObject) object).getRectangle().getX(),((RectangleMapObject) object).getRectangle().getY());
                        break;
                    case 'e':
                        enemies.add(new Enemy((int)((RectangleMapObject) object).getRectangle().getX(),(int)((RectangleMapObject) object).getRectangle().getY()));
                        break;


                }


            }

        }
    }


}
