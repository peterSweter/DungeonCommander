package com.sweter.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.Tile;
import com.sweter.game.entities.Unit;
import com.sweter.game.interfaces.Character;
import com.sweter.game.interfaces.PathFinder;

import java.util.ArrayList;

import javafx.util.Pair;

/**
 * Created by peter on 5/31/16.
 */
public class InputManager {

    private UnitManager unitManager;
    private Camera camera;
    private Level level;
    private PathFinder pf;

    public InputManager(UnitManager unitManager, Camera camera, Level level){
        this.unitManager = unitManager;
        this.camera = camera;
        this.level = level;
        pf = new AstarPathFinder(level, 200);
    }

    public void update(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x, y, 0);
                camera.unproject(touch_point);

                /// checking if target is unit, it returns reference to targeted unit or null if target is not unit
                Unit touchIsUnit = checkTouch(touch_point);

                if(touchIsUnit != null)
                    unitManager.getActiveCharacter().setDynamicPath(touchIsUnit);

                Path testPath = pf.findPath(unitManager.getActiveCharacter(), unitManager.getActiveCharacter().getPosition(), touch_point);


                if(testPath != null) {
                    unitManager.getActiveCharacter().setTarget(new Vector3(testPath.getStep(1).getX(), testPath.getStep(1).getY(), 0));
                    testPath.x++;
                    unitManager.getActiveCharacter().setPath(testPath);
                }
                    /*
                --- printing smth with path finding---
                if(testPath != null) {
                    System.out.println("testpath: " + testPath.getLength());
                    for (Tile par : testPath) {
                        System.out.println(par);
                    }
                }*/
                /*
                //--- testing path --- :v :v :v
                if(testPath != null) {
                    Character curr = unitManager.getActiveCharacter();
                    for (int i = 0; i < testPath.getLength(); i++) {
                        System.out.println("path is: " + testPath.getStep(i) + "and on path it's: " +
                                level.blocked(curr, testPath.getStep(i).getX(), testPath.getStep(i).getY()));
                    }
                }*/
                if(testPath != null) {
                    testPath.finalTargetx = touch_point.x;
                    testPath.finalTargety = touch_point.y;
                }

                //unitManager.getActiveCharacter().setPath(testPath);
                return true;
            }

            private Unit checkTouch(Vector3 x){
                for(Unit u : unitManager.units){
                    Vector3 targetPosition = u.getPosition();

                    float a = x.x - targetPosition.x;
                    float b = x.y - targetPosition.y;
                    float radius = 16f;
                    a = a*a;
                    b = b*b;
                    radius = radius*radius;
                    if(a+b < radius){
                        System.out.println("intersects");
                        return u;
                    }
                }
                return null;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {


                return true;
            }

            @Override public boolean keyDown (int keycode) {
                switch (keycode){
                    case Input.Keys.Q:
                        unitManager.setActiveUnit(unitManager.mainCharacter);
                        break;
                    case Input.Keys.W:
                        unitManager.setActiveUnit(unitManager.axeMan);
                        break;
                    case Input.Keys.E:
                        unitManager.setActiveUnit(unitManager.swordsMan);
                        break;
                }

                return false;
            }
        });
    }
}
