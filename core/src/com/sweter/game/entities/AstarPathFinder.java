package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.dungeonCommander;
import com.sweter.game.interfaces.Character;
import com.sweter.game.interfaces.PathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.transformation.SortedList;


/**
 * Created by pawel on 31.05.16.
 */
public class AstarPathFinder implements PathFinder {
    private ArrayList<Node> closed = new ArrayList<Node>();
    private ArrayList<Node> open = new ArrayList<Node>();
    private Level map;
    private int maxSearchDistance;

    private Node[][] nodes;

    public AstarPathFinder(Level map, int maxSearchDistance){
        this.map = map;
        this.maxSearchDistance = maxSearchDistance;

        nodes = new Node[8*map.getWidthInTiles()][8*map.getHeightInTiles()];
        System.out.println("nodes size is: " + 8*map.getHeightInTiles() + " " + 4*map.getWidthInTiles());
        System.out.println("map size is: " + map.getHeightInTiles() + " " + map.getWidthInTiles());


        for(int x = 0; x<8*map.getWidthInTiles(); x++){
            for(int y = 0; y<8*map.getHeightInTiles(); y++){
                nodes[x][y] = new Node(x, y);
            }
        }
    }

    public Path findPath(Character toMove, Vector3 s, Vector3 t){
        /// if destination is blocked we can't find any path
        int sx = (int) (s.x/4);
        int sy = (int) (s.y/4);
        int tx = (int) (t.x/4);
        int ty = (int) (t.y/4);

        clear();

        System.out.println("input parameters are: (" + sx + ", " + sy + ") and (" + tx + ", " + ty + ")");

        if(map.blocked(toMove, tx, ty)) {
            System.out.println("target is blocked!");
            return null;
        }

        /// init
        nodes[sx][sy].cost = 0;
        nodes[sx][sy].depth = 0;
        closed.clear();
        open.clear();
        open.add(nodes[sx][sy]);
        nodes[tx][ty].parent=null;

        int maxDepth = 0;
        while((maxDepth < maxSearchDistance) && (open.size() != 0)){


            Node current = getSmallestInOpen();
            if(current == nodes[tx][ty]){
                break;
            }

            removeFromOpen(current);
            addToClosed(current);

            /// search through all neighbours of current node

            for(int x=-1; x<2; x++){
                for(int y=-1; y<2; y++){
                    if(x == 0 && y == 0)
                        continue;

                    /// neighbour location
                    int xp = x + current.x;
                    int yp = y + current.y;


                    if(isValidLocation(toMove, sx, sy, xp, yp)){

                        float nextStepCost = current.cost + getMovementCost(toMove, current.x, current.y, xp, yp);
                        Node neighbour = nodes[xp][yp];
                        ///map.pathFinderVisited(xp, yp);

                        if(nextStepCost < neighbour.cost){
                            if(inOpenList(neighbour)){
                                removeFromOpen(neighbour);
                            }
                            if(inClosedList(neighbour)){
                                removeFromClosed(neighbour);
                            }
                        }

                        if(!inOpenList(neighbour) && !inClosedList(neighbour)){
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = getHeuristicCost(toMove, xp, yp, tx, ty);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            addToOpen(neighbour);
                        }

                    }
                }
            }
        }

        if(nodes[tx][ty].parent == null)
            return null;

        Path path = new Path(map);
        Node target = nodes[tx][ty];
        while(target != nodes[sx][sy]){
            path.prependStep(target.x, target.y);
            target = target.parent;
        }
        path.prependStep(sx, sy);

        return path;
    }

    private void clear(){
        for(int x = 0; x<8*map.getWidthInTiles(); x++){
            for(int y = 0; y<8*map.getHeightInTiles(); y++){
                nodes[x][y].cost = 0;
                nodes[x][y].parent = null;
                nodes[x][y].heuristic = 0;
                nodes[x][y].depth = 0;

            }
        }
    }

    public boolean inOpenList(Node x){
        return open.contains(x);
    }

    public boolean inClosedList(Node x){
        return closed.contains(x);
    }

    public void addToClosed(Node x){
        closed.add(x);
    }

    public void addToOpen(Node x){
        open.add(x);
    }

    public void removeFromOpen(Node x){
        open.remove(x);
    }

    public void removeFromClosed(Node x){
        closed.remove(x);
    }

    /// for debugging
    public void drawBound(ShapeRenderer sr) {
        for(Node n : open){
            sr.setColor(Color.GREEN);
            sr.rect(n.getX(),n.getY(),32,32);

        }
        for(Node r : closed){
            sr.setColor(Color.GRAY);
            sr.rect(r.getX(),r.getY(),32,32);
        }

    }

    public Node getSmallestInOpen(){
        Node x = null;
        for(Node n : open){
            if(x == null){
                x = n;
            }else if(n.heuristic+n.cost < x.heuristic + x.cost){
                x = n;
            }
        }
        return x;
    }

    public float getHeuristicCost(Character mv, int x, int y, int tx, int ty){

        float dx = 4*tx - 4*x;
        float dy = 4*ty - 4*y;
        float result = (float) (Math.sqrt(dx*dx + dy*dy));
        return result;
    }

    private boolean isValidLocation(Character mv, int sx, int sy, int x, int y){
        boolean invalid = (x<0) || (y<0) || (4*x>=32*map.getWidthInTiles()) || (4*y>=32*map.getHeightInTiles());
        if(!invalid && sx!=x || sy!=y){
            invalid = map.blocked(mv, x, y);
            //System.out.println("Checking if neighbour is valid: " + invalid + " x: " + x + " " + y);
        }
        return !invalid;
    }

    public float getMovementCost(Character mv, int sx, int sy, int tx, int ty){
        return map.getCost(mv, sx, sy, tx, ty);
    }


    static class Node implements Comparable{
        private int x;
        private int y;
        private float cost;
        private Node parent;
        private float heuristic;
        private int depth;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int setParent(Node parent){
            depth = parent.depth+1;
            this.parent = parent;

            return depth;
        }

        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;

            float f = heuristic + cost;
            float of = other.heuristic + other.cost;

            if(f < of){
                return -1;
            }else if (f > of){
                return 1;
            }else
                return 0;
        }
    }


}
