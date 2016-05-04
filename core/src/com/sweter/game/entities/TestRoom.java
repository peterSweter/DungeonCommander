package com.sweter.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sweter.game.interfaces.Room;

/**
 * Created by peter on 5/4/16.
 */
public class TestRoom implements Room {

    private TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    MapObjects objects;

    public TestRoom(){
        tiledMap = new TmxMapLoader().load("untitled.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        int objectLayerId = 1;
        objects = tiledMap.getLayers().get(objectLayerId).getObjects();
    }

    @Override
    public void renderTiled(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public MapObjects getObjects() {
        return objects;
    }
}
