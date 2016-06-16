package com.sweter.game.entities;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 6/15/16.
 */
public class AxeMan extends Unit {
    public AxeMan(int x, int y) {
        super(x, y);
        this.unitType = UnitTypes.AXEMAN;
        setStats();
    }

    @Override
    public void setStats() {
        this.speed = 50;
        this.health = 220;
        this.attack_speed = 30; // per minute
        this.attack_damage = 10;
        this.width = 16;
        this.attack_range = new Rectangle(0,0,width*2.5f, width*2.5f);
        this.type_tag = "axeman";

    }
}
