package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.sweter.game.interfaces.Room;

import java.util.ArrayList;

/**
 * Created by peter on 5/4/16.
 */
public class Level implements Room {

    private TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    MapObjects objects;
    private ArrayList<Rectangle> walls;

    public Level(String level_name){
        tiledMap = new TmxMapLoader().load(level_name);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        walls = new ArrayList<Rectangle>();

        objects = tiledMap.getLayers().get("walls").getObjects();

        for(MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                walls.add(rect);

            }
            else if (object instanceof PolygonMapObject) {
                Polygon polygon = ((PolygonMapObject) object).getPolygon();
                // do something with polygon...
            }
            else if (object instanceof PolylineMapObject) {
                Polyline chain = ((PolylineMapObject) object).getPolyline();
                // do something with chain...
            }
            else if (object instanceof CircleMapObject) {
                Circle circle = ((CircleMapObject) object).getCircle();
                // do something with circle...
            }
        }

    }

    public ArrayList<Rectangle> getWalls(){
        return walls;
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

    public void drawBound(ShapeRenderer sr){
        for(Rectangle r : walls) {
            sr.setColor(Color.FIREBRICK);
            sr.rect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
        }
    }

}
