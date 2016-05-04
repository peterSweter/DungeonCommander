package com.sweter.game.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

/**
 * Created by peter on 4/27/16.
 */
public interface Character {
    public void update(float delta, boolean blocked);
    public void sRender(ShapeRenderer sr);
    public void render(SpriteBatch sb);
<<<<<<< HEAD
    public com.badlogic.gdx.math.Rectangle getBounds();
=======
    public Vector3 getPosition();
    public Rectangle getBounds();
>>>>>>> 1301681214d1b36cf391ff54528c627e5c003c24
    //public
}
