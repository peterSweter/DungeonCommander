package com.sweter.game.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Rectangle;

/**
 * Created by peter on 4/27/16.
 */
public interface Character {
    public void update(float delta);
    public void sRender(ShapeRenderer sr);
    public void render(SpriteBatch sb);
    public Rectangle getBounds();
    //public
}