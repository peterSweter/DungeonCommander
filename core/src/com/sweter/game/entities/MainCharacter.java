package com.sweter.game.entities;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 6/15/16.
 */
public class MainCharacter extends Unit {

    public MainCharacter(int x, int y) {
        super(x, y);
        this.unitType = UnitTypes.MAIN_CHARACTER;
        setStats();

    }

    public void setStats(){
        this.speed = 80;
        this.health = 200;
        this.attack_speed = 20; // per minute
        this.width = 16;
        this.attack_damage = 10;
        this.attack_range = new Rectangle(0,0,width*2.5f, width*2.5f);
        this.type_tag ="haracter";

    }



}
