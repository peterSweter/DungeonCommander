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
    float x,y;

    Vector3 last_position;
    float width = 20;
    public Vector3 target;
    boolean isTargeted = false;
    boolean isEnemy = false;
    int type;

    float speed = 50;


    public Unit(int x,int y){   /// default constructor, primarly for constructing player bound units
        this.x = x;
        this.y = y;

        last_position  = new Vector3(x,y,0);

    }

    //type 1 main_character
    public Unit(int x, int y,int type){ /// constructor for constructing enemy units
        this(x, y);
        this.type = type;
    }

    public void setTarget(Vector3 target){
        this.target = target;
        isTargeted = true;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {

       last_position.set(x,y,0);
        if(isTargeted){
            float newX = this.x;
            float newY = this.y;
            newX += Math.signum(target.x - this.x) * delta * speed;
            newY += Math.signum(target.y - this.y) * delta * speed;

            /// This part should be declared new method, prob. in character interface
            Circle thisBody = new Circle(newX, newY, this.width);

            this.x = newX;
            this.y = newY;
            if((Math.abs(target.x - this.x) < 0.5) && (Math.abs(target.y - this.y)< 0.5)){
                isTargeted = false;
            }
        }
    }

    @Override
    public void sRender(ShapeRenderer sr) {
        if(isEnemy)
            sr.setColor(Color.FIREBRICK);
        else
            sr.setColor(Color.FIREBRICK);
        sr.circle(x,y, width);
    }
    @Override
    public void render(SpriteBatch sb){

    }

    @Override
    public Vector3 getPosition() {
        return new Vector3(x, y, 0);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width, width);
    }

    @Override
    public void  wallColision(Rectangle wall){

        this.x = wall.getX() +wall.getWidth();
        System.out.println("collision");

    }

    public Vector3 getLast_position(){
        return last_position;
    }

}
