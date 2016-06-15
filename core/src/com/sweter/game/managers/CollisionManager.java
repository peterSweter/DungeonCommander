package com.sweter.game.managers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.Unit;
import com.sweter.game.interfaces.PathFinder;

import java.util.ArrayList;

/**
 * Created by peter on 5/31/16.
 */
public class CollisionManager {

    UnitManager unitManager;
    Level level;

    PathFinder pf;


    public  CollisionManager(UnitManager unitManager, Level level){

        this.unitManager = unitManager;
        this.level = level;
      //  this.pf = new AstarPathFinder(level, 70);
    }

    public void wallCollision() {

        for (Unit u : unitManager.getUnits()) {

            for(Rectangle r :level.getWalls()){

                if (Intersector.overlaps(r, u.getBounds())){
                   // System.out.println("collision");

                    Vector3 currentPosition = new Vector3(u.getPosition());

                     u.setPosition(u.getLast_position().x, currentPosition.y);

                    if (!Intersector.overlaps(r, u.getBounds())) {
                        continue;
                    }

                    u.setPosition(currentPosition.x, u.getLast_position().y);
                    if (!Intersector.overlaps(r, u.getBounds())) {
                        continue;
                    }

                    u.setPosition(u.getLast_position().x, u.getLast_position().y);



                }

            }

        }
    }

    public void update(){
        wallCollision();

    }




}
