package com.sweter.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

//TO DO user ui to change active unit.

public abstract class Unit implements Character {

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
    int attack_damage = 10;
    float attack_speed = 1; // per minute

    Rectangle attack_range;

    String type_tag ="unit";



    public Unit(int x,int y){   /// default constructor, primarly for constructing player bound units

        position = new Vector3(x,y,0);
        last_position  = new Vector3(x,y,0);

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

        attack_range.setPosition(position.x -attack_range.width/2, position.y-attack_range.height/2);
    }

    public void pathUpdate(){

        if(path != null && path.x < path.getLength()-1){

            Tile currentTile = path.getStep(path.x);
            Tile nextTile = path.getStep(path.x+1);
            target.set(new Vector3((nextTile.getX() * 4), (nextTile.getY() * 4), 0));

            if(target == null || !isTargeted){
                System.out.println("next coords: " + (nextTile.getX()*4) + " " + (nextTile.getY()*4) + " x: " + path.x +
                " " + path.lvl.blocked(this, nextTile.getX(), nextTile.getY()));
                isTargeted = true;
                path.x++;
            }
        }
    }

    @Override
    public void sRender(ShapeRenderer sr) {

        sr.setColor(Color.FIREBRICK);
        sr.circle(position.x, position.y, width);

    }
    @Override
    public void render(SpriteBatch sb, BitmapFont font){
        font.setColor(Color.LIME);
        font.draw(sb, type_tag, position.x -width*1.5f,position.y + width*2f);
        font.setColor(Color.WHITE);
        font.draw(sb, ""+health, position.x -width*0.8f,position.y +width*0.5f);

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

    public void drawAttackRange(ShapeRenderer sr){

        sr.setColor(Color.WHITE);
        Rectangle r = getAttackRange();

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

    public abstract void setStats();

}