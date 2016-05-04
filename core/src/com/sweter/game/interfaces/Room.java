package com.sweter.game.interfaces;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;

/**
 * Created by peter on 4/28/16.
 */
public interface Room {
    public void renderTiled(OrthographicCamera camera);
    public MapObjects getObjects();
}
