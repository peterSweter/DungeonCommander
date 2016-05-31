package com.sweter.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by peter on 5/31/16.
 */
public class InputManager {

    private UnitManager unitManager;
    private Camera camera;

    public InputManager(UnitManager unitManager, Camera camera){
        this.unitManager = unitManager;
        this.camera = camera;
    }

    public void update(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x, y, 0);
                camera.unproject(touch_point);

                unitManager.getActiveCharacter().setTarget(touch_point);


                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {


                return true;
            }
        });
    }
}
