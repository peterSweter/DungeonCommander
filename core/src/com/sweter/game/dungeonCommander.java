package com.sweter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sweter.game.screens.GameScreen;
import com.sweter.game.screens.MenuScreen;
//test 2
public class dungeonCommander extends Game {

	public static final  float GAME_WIDTH = 870;
	public static final float  GAME_HEIGHT = 540;

	public SpriteBatch batch;

	public ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose(){
		super.dispose();
	}
}
