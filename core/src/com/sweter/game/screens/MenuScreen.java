package com.sweter.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    public String info="";

    public OrthographicCamera camera;
    Viewport viewport;

    public MenuScreen(dungeonCommander game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT, camera);
        camera.setToOrtho(false, game.GAME_WIDTH,game.GAME_HEIGHT);
    }
    public MenuScreen(dungeonCommander game, String info){
        this(game);
        this.info = info;

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

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        game.batch.begin();
        game.batch.draw(menuScreen, game.GAME_WIDTH / 2 - tw, game.GAME_HEIGHT / 2 - th);
        update();
        if(callGame)
            game.setScreen(new GameScreen(game));
        game.font.setColor(Color.BLACK);
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "Instructions: \n Use 'q', 'w', 'e' keys to switch between units, and kill all enemies! ", 20, 100);
            game.font.getData().setScale(5f);
         game.font.setColor(Color.LIME);
            game.font.draw(game.batch, ""+info, 250, 500);
        game.font.getData().setScale(1f);




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
