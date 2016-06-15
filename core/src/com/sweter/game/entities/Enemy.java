package com.sweter.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 6/15/16.
 */
public class Enemy extends Unit {

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
        this.attack_range = new Rectangle(0,0,width*2.5f, width*2.5f);

    }

    @Override
    public void sRender(ShapeRenderer sr) {

        sr.setColor(Color.BLACK);
        sr.circle(position.x, position.y, width);

    }
}
