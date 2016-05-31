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
    Vector3 gV;
    float width = 20;
    public Vector3 target;
    boolean isTargeted = false;
    boolean isEnemy = false;
    float speed = 50;
    private static Set<Unit> UnitHolder = new HashSet<Unit>();

    public Unit(int x,int y){   /// default constructor, primarly for constructing player bound units
        this.x = x;
        this.y = y;
        gV = new Vector3(x, y, 0);
        UnitHolder.add(this);
    }

    public Unit(int x, int y, boolean isEnemy){ /// constructor for constructing enemy units
        this(x, y);
        this.isEnemy = isEnemy;
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
            float newX = this.x;
            float newY = this.y;
            newX += Math.signum(target.x - this.x) * delta * speed;
            newY += Math.signum(target.y - this.y) * delta * speed;

            /// This part should be declared new method, prob. in character interface
            Circle thisBody = new Circle(newX, newY, this.width);
            for(Unit u : UnitHolder){
                if(u == this) continue;
                Circle uBody = new Circle(u.x, u.y, u.width);
                System.out.println(uBody);
                if(thisBody.overlaps(uBody)) {
                    System.out.println("Overlapping");
                    return;
                }
            }

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
            sr.setColor(Color.BLACK);
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
}
