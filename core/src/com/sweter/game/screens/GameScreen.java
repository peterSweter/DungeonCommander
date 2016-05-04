package com.sweter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sweter.game.dungeonCommander;
import com.sweter.game.entities.Unit;

/**
 * Created by peter on 4/27/16.
 */
public class GameScreen implements Screen {

    dungeonCommander game;

    public OrthographicCamera camera;
    Viewport viewport;
    public Texture img;
    private Unit testUnit;
    private Unit testEnemy;


    public GameScreen(dungeonCommander game){

        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT, camera);
        camera.setToOrtho(false, game.GAME_WIDTH,game.GAME_HEIGHT);


        img = new Texture("badlogic.jpg");

        testUnit = new Unit(50,50);
        testEnemy = new Unit((int) game.GAME_WIDTH-50, (int) game.GAME_HEIGHT-50, true);

    }

    public void update(float delta){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                Vector3 touch_point = new Vector3(x, y, 0);
                camera.unproject(touch_point);

                testUnit.setTarget(touch_point);
                testEnemy.setTarget(testUnit.getPosition());
                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {


                return true;
            }
        });

        testUnit.update(delta);
        testEnemy.setTarget(testUnit.getPosition());
        testEnemy.update(delta);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            testUnit.render(game.batch);
            testEnemy.render(game.batch);
        game.batch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            testUnit.sRender(game.shapeRenderer);
            testEnemy.sRender(game.shapeRenderer);
        game.shapeRenderer.end();


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
