package com.sweter.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.interfaces.Character;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 4/27/16.
 */

public class Unit implements Character {

    Vector3 last_position;
    Vector3 position;

    public Vector3 target;
    boolean isTargeted = false;
    boolean isEnemy = false;
    UnitTypes unitType;

    Path path;

    // unit stats

    float speed = 75;
    float width = 16;
    int health = 100;
    int damage = 10;
    float attack_speed = 1; // one per second ??

    Rectangle attack_range;



    public Unit(int x,int y){   /// default constructor, primarly for constructing player bound units

        position = new Vector3(x,y,0);
        last_position  = new Vector3(x,y,0);

    }

    //type 1 main_character
    public Unit(int x, int y,UnitTypes unitType){ /// constructor for constructing enemy units
        this(x, y);
        this.unitType = unitType;

    }

    public void setTarget(Vector3 target){
        this.target = target;
        isTargeted = true;
    }

    public void setPosition(float x, float y){
        position.set(x, y ,0);
    }

    @Override
    public void update(float delta) {

        last_position.set(position);

        pathUpdate();

        if(isTargeted){
            float newX = position.x;
            float newY = position.y;
            newX += Math.signum(target.x - position.x) * delta * speed;
            newY += Math.signum(target.y - position.y) * delta * speed;

            /// This part should be declared new method, prob. in character interface
            Circle thisBody = new Circle(newX, newY, this.width);

            position.set(newX, newY, 0);

            if((Math.abs(target.x - position.x) < 4) && (Math.abs(target.y - position.y)< 4)){
                isTargeted = false;
            }
        }
    }

    public void pathUpdate(){
        if(path != null && path.x < path.getLength()-1){
            Tile currentTile = path.getStep(path.x);
            Tile nextTile = path.getStep(path.x+1);
            if(path != null && path.x == path.getLength()-2){
               target.set(new Vector3(path.finalTargetx, path.finalTargety, 0));
            }else {
                target.set(new Vector3((nextTile.getX() * 32 + 16), (nextTile.getY() * 32 + 16), 0));
            }
            if(getPosition().x >= nextTile.getX()*32 && getPosition().y >= nextTile.getY()*32){
                System.out.println("next coords: " + (nextTile.getX()*32+16) + " " + (nextTile.getY()*32 + 16) + " x: " + path.x);
                path.x++;
            }
        }
    }

    @Override
    public void sRender(ShapeRenderer sr) {
        if(isEnemy)
            sr.setColor(Color.FIREBRICK);
        else
            sr.setColor(Color.FIREBRICK);
        sr.circle(position.x, position.y, width);

        //drawBounds(sr);
    }
    @Override
    public void render(SpriteBatch sb){

    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    @Override
    public Rectangle getBounds() {
        //drawing bounds for testing purposes
        return new Rectangle(position.x-width,position.y-width,width*2, width*2);

    }

    public void drawBounds(ShapeRenderer sr){
        sr.setColor(Color.WHITE);
        Rectangle r = getBounds();
        sr.rect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
    }

    @Override
    public void  wallColision(Rectangle wall){

        position.x = wall.getX() +wall.getWidth();
        System.out.println("collision");

    }

    public Vector3 getLast_position(){
        return last_position;
    }

    public void setPath(Path path){
        this.path = path;
    }

    public Rectangle getAttackRange(){
        return attack_range;
    }

}
