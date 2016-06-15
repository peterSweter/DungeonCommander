package com.sweter.game.entities;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 6/15/16.
 */
public class SwordsMan extends Unit {
    public SwordsMan(int x, int y) {
        super(x, y);
        this.unitType = UnitTypes.SWORDSMAN;
        setStats();
    }

    @Override
    public void setStats() {
        this.speed = 60;
        this.health = 100;
        this.attack_speed = 15; // per minute
        this.width = 16;
        this.attack_damage = 5;
        this.attack_range = new Rectangle(0,0,width*2.5f, width*2.5f);
        this.type_tag = "swordsman";
    }
}
