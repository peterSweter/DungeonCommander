package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.interfaces.Character;

import com.badlogic.gdx.math.Rectangle;


/**
 * Created by peter on 4/27/16.
 */
public class Unit implements Character {

    float x,y;
    Vector3 gV;
    float width = 20;
    Vector3 target;
    boolean isTargeted = false;
    float speed = 50;

    public Unit(int x,int y){
        this.x = x;
        this.y = y;
        gV =  new Vector3(x,y,0);

    }

    public void setTarget(Vector3 target){
        this.target = target;
        isTargeted = true;
    }
    @Override
    public void update(float delta, boolean blocked) {
        if(blocked) {
            isTargeted = false;
            x = gV.x;
            y = gV.y;
            return;
        }
        gV.set(x,y,0);

        if(isTargeted){
            this.x += Math.signum(target.x - this.x) * delta * speed;
            this.y += Math.signum(target.y - this.y) * delta * speed;
            if((Math.abs(target.x - this.x) < 0.5) && (Math.abs(target.y - this.y)< 0.5)){
                isTargeted = false;
            }
        }
    }

    @Override
    public void sRender(ShapeRenderer sr) {

            sr.setColor(Color.BLACK);
            sr.circle(x,y, width);

    }
    @Override
    public void render(SpriteBatch sb){

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width, width);

    }
}
