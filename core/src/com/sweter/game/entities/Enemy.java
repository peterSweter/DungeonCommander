package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 6/15/16.
 */
public class Enemy extends Unit {

    Rectangle vision_range;

    public Enemy(int x, int y) {
        super(x,y);
        setStats();
    }

    @Override
    public void setStats() {
        this.unitType = UnitTypes.ENEMY;
        this.type_tag = "enemy";
        this.health =50;
        this.attack_damage =4;
        this.speed =40;
        this.attack_speed =45;
        this.attack_range = new Rectangle(0,0,width*2.5f, width*2.5f);
        this.vision_range = new Rectangle(0,0, 32 *8, 32*8);

    }

    @Override
    public void sRender(ShapeRenderer sr) {

        sr.setColor(Color.BLACK);
        sr.circle(position.x, position.y, width);

    }

    @Override
    public void update(float delta){
        super.update(delta);

        vision_range.setPosition(position.x - vision_range.width / 2, position.y - vision_range.height / 2);
    }

    @Override
    public void drawAttackRange(ShapeRenderer sr){
        super.drawAttackRange(sr);
        Rectangle r = vision_range;
        sr.rect(r.getX(),r.getY(),r.getWidth(),r.getHeight());

    }

    public Rectangle getVision_range(){
        return vision_range;
    }
}
