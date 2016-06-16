package com.sweter.game.managers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Enemy;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.Path;
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
        this.pf = new AstarPathFinder(level, 200);
    }

    public void wallCollision() {

/*        for (Unit u : unitManager.getUnits()) {

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

        }*/
    }

    public void update(float delta){
        wallCollision();
        battleExecutor(delta);
        enemyAiexecutor();

    }

    public void battleExecutor(float delta){

        for(Unit e : unitManager.enemies){
            for(Unit u : unitManager.alies){

                if(e.getAttackRange().overlaps(u.getBounds())){

                    u.takeDemage(e.attack());
                    if(!u.isAlive()){
                        unitManager.alies.remove(u);
                        unitManager.units.remove(u);
                    }
                    break;
                }
            }
        }

        for(Unit u : unitManager.alies){
            for(Unit e : unitManager.enemies){
                if(u.getAttackRange().overlaps(e.getBounds())){
                    e.takeDemage(u.attack());
                    if(!e.isAlive()) {
                        unitManager.enemies.remove(e);
                        unitManager.units.remove(e);
                    }
                    break;
                }
            }
        }



    }

    public void enemyAiexecutor(){
        for(Enemy e :unitManager.enemies ){
            Unit target= null;
            float distance = 999999999;

            for(Unit u : unitManager.alies){
                if(e.getVision_range().overlaps(u.getBounds())){

                    if(((e.getPosition().x - u.getPosition().x)*(e.getPosition().x - u.getPosition().x) + (e.getPosition().y - u.getPosition().y)) < distance){
                        target = u;
                        distance = (e.getPosition().x - u.getPosition().x)*(e.getPosition().x - u.getPosition().x) + (e.getPosition().y - u.getPosition().y);
                    }
                }
            }

            if(target!=null){

                Path testPath = pf.findPath(e, e.getPosition(), target.getPosition());


                if(testPath != null) {
                    e.setTarget(new Vector3(testPath.getStep(1).getX(), testPath.getStep(1).getY(), 0));
                    testPath.x++;
                    e.setPath(testPath);
                }

                if(testPath != null) {
                    testPath.finalTargetx = target.getPosition().x;
                    testPath.finalTargety = target.getPosition().y;
                }

                e.setPath(testPath);

            }
        }
    }






}
