package com.sweter.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.entities.AstarPathFinder;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.Path;
import com.sweter.game.entities.Tile;
import com.sweter.game.interfaces.PathFinder;

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
        pf = new AstarPathFinder(level, 70);
    }

    public void update(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x, y, 0);
                camera.unproject(touch_point);

                unitManager.getActiveCharacter().setTarget(touch_point);

                Path testPath = pf.findPath(unitManager.getActiveCharacter(),
                        (int)unitManager.getActiveCharacter().getPosition().x/32, (int)unitManager.getActiveCharacter().getPosition().y/32,
                        (int)touch_point.x/32, (int)touch_point.y/32);

                /*
                --- printing smth with path finding---
                if(testPath != null) {
                    System.out.println("testpath: " + testPath.getLength());
                    for (Tile par : testPath) {
                        System.out.println(par);
                    }
                }*/

                testPath.finalTargetx = touch_point.x;
                testPath.finalTargety = touch_point.y;

                unitManager.getActiveCharacter().setPath(testPath);
                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {


                return true;
            }
        });
    }
}
