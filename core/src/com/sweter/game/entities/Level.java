package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BooleanArray;
import com.sweter.game.interfaces.Character;
import com.sweter.game.interfaces.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by peter on 5/4/16.
 */
public class Level implements Room {
    public TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    MapObjects objects;
    private ArrayList<Rectangle> walls;
    public int tiledPx = 32;
    private TiledMapTileLayer tileWalls;
    public HashSet<Vector3> wallsSet;

    public Level(String level_name){
        tiledMap = new TmxMapLoader().load(level_name);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        walls = new ArrayList<Rectangle>();
        wallsSet = new HashSet<Vector3>();

        objects = tiledMap.getLayers().get("walls").getObjects();


        for(MapLayer ml : tiledMap.getLayers()){
            System.out.println("ml: " + ml.getName());
        }
        tileWalls = (TiledMapTileLayer)tiledMap.getLayers().get(2);
        for(int x = 0; x < tileWalls.getWidth(); x++){
            for(int y = 0; y < tileWalls.getHeight(); y++){
                if(tileWalls.getCell(x, y)!=null) {
                    System.out.println(x + " " + y + " : ");
                    System.out.println("position: " + x*32 + " " + y*32 );
                    //adding blocked cells to set
                    addToSet(x, y);
                }
            }
        }

        System.out.println("walls are");
        for(Vector3 x : wallsSet){
            System.out.println(x.x + " "+ x.y);
        }

        for(MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
               // System.out.println( "walls: "  + ((RectangleMapObject) object).getRectangle().getX() + " " +  ((RectangleMapObject) object).getRectangle().getY());
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

    private void addToSet(int x, int y){
        //adding blocked cells to set
        for(int i = 0; i <= 8; i++){
            for(int j = 0; j <= 8; j++){
                wallsSet.add(new Vector3(8*x + i, 8*y + j, 0));
            }
        }
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

    /// functionality necessary for pathfinding
    public int getWidthInTiles(){
        return tileWalls.getWidth();
    }
    public int getHeightInTiles(){
        return tileWalls.getHeight();
    }

    public boolean blocked(Character mv, int x, int y){
        /// this function gets x,y coords on 4PX (!!!) map tile, should convert it to 32 px map
        if(wallsSet.contains(new Vector3(x, y, 0))){
         //   System.out.println("this cell is blocked!: " + x + " " + y);

            return true;
        }
        return false;
    }

    public float getCost(Character mv, int sx, int sy, int tx, int ty){
        /// could be developed in future
        if(Math.abs(tx - sx) == 1 && Math.abs(ty-sy) == 1)
            return (float) 1.4;
        return 1;
    }
    /*
    public void pathFinderVisited(int x, int y){

    }
    */

}
