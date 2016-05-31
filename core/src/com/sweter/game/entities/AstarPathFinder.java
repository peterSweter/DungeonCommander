package com.sweter.game.entities;

import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.sweter.game.interfaces.Character;
import com.sweter.game.interfaces.PathFinder;

import java.util.ArrayList;

import javafx.collections.transformation.SortedList;

/**
 * Created by pawel on 31.05.16.
 */
public class AstarPathFinder implements PathFinder {
  //  private ArrayList closed = new ArrayList();
  //  private SortedList open = new SortedList();

    private Level map;
    private int maxSearchDistance;

    private Node[][] nodes;

    public AstarPathFinder(Level map, int maxSearchDistance){
        this.map = map;
        this.maxSearchDistance = maxSearchDistance;

        nodes = new Node[map.tileLayer.getWidth()][map.tileLayer.getHeight()];
        for(int x = 0; x<map.tileLayer.getWidth(); x++){
            for(int y = 0; y<map.tileLayer.getHeight(); y++){
                nodes[x][y] = new Node();
            }
        }
    }

    public Path findPath(Character toMove, int sx, int sy, int tx, int ty){
        return null;
    }
}
