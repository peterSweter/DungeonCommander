package com.sweter.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sweter.game.dungeonCommander;

/**
 * Created by pawel on 27.04.16.
 */
public class MenuScreen implements Screen {
    private dungeonCommander game;
    private Texture menuScreen;
    private Sprite mysprite;
    private int tw;
    private int th;
    private boolean callGame = false;
    public MenuScreen(dungeonCommander game){
        this.game = game;
    }
    public void update(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                //System.out.println(x + " " + y);
                if(x > 250 && x < 640 && y > 170 && y < 260){
                    callGame = true;
                }
                return true;
            }

        });


    }

    @Override
    public void show() {
        menuScreen = new Texture("menuscreen.jpg");
        mysprite = new Sprite(menuScreen);
        tw = menuScreen.getWidth()/2;
        th = menuScreen.getHeight()/2;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(255/255f, 255/255f, 255/255f, 1);
        game.batch.begin();
        game.batch.draw(menuScreen,  game.GAME_WIDTH/2 - tw, game.GAME_HEIGHT/2 - th);
        update();
        if(callGame)
            game.setScreen(new GameScreen(game));
        //mysprite.setOriginCenter();
        //mysprite.draw();
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
