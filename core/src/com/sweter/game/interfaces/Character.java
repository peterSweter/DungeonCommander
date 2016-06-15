package com.sweter.game.interfaces;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by peter on 4/27/16.
 */
public interface Character {
    public void update(float delta);
    public void sRender(ShapeRenderer sr);
    public void render(SpriteBatch sb, BitmapFont font);
    public Vector3 getPosition();
    public Rectangle getBounds();
    public void wallColision(Rectangle wall);
    public Rectangle getAttackRange();
    public void setStats();
    public void drawAttackRange(ShapeRenderer sr);
    public boolean hasDynamicTarget();
    public Vector3 dynamicTargetPosition();

    //public
//public void collisionray();
}
