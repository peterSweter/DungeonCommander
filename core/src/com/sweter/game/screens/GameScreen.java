package com.sweter.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sweter.game.dungeonCommander;
import com.sweter.game.entities.Level;
import com.sweter.game.entities.Unit;
import com.sweter.game.managers.CollisionManager;
import com.sweter.game.managers.InputManager;
import com.sweter.game.managers.UnitManager;

/**
 * Created by peter on 4/27/16.
 */
public class GameScreen implements Screen {

    dungeonCommander game;

    public OrthographicCamera camera;
    Viewport viewport;
    public Texture img;

    private Level level_01;
    private UnitManager unitManager;
    private InputManager inputManager;
    private CollisionManager collisionManager;

    /// myinfo for getting pathfinder done
    private int column, row;

    public GameScreen(dungeonCommander game){

        this.game = game;

        camera = new OrthographicCamera();
        viewport = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT, camera);
        camera.setToOrtho(false, game.GAME_WIDTH,game.GAME_HEIGHT);


        level_01 = new Level("dungeonlvl1.tmx");
        unitManager = new UnitManager();
        inputManager = new InputManager(unitManager, camera, level_01);
        collisionManager = new CollisionManager(unitManager, level_01);


        column = (int)unitManager.mainCharacter.getPosition().x/16;
        column = (int)unitManager.mainCharacter.getPosition().y/16;
        System.out.println("maincharater starting postition: " +
                unitManager.mainCharacter.getPosition().x + " " + unitManager.mainCharacter.getPosition());


    }

    public void update(float delta){

        inputManager.update();
        unitManager.update(delta);
        collisionManager.update();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(unitManager.getActiveCharacter().getPosition().x, unitManager.getActiveCharacter().getPosition().y, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.shapeRenderer.setProjectionMatrix(camera.combined);



        level_01.renderTiled(camera);

        game.batch.begin();
            unitManager.render(game.batch);

        game.batch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            unitManager.schapeRender(game.shapeRenderer);

        game.shapeRenderer.end();

        // testing srenderer

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            //drawing wall bounds from level 01
            level_01.drawBound(game.shapeRenderer);
            unitManager.testingSchapeRender(game.shapeRenderer);

        game.shapeRenderer.end();



      //  System.out.println("debug tileLayer, height: " + (int)unitManager.mainCharacter.getPosition().x/32 + " " + (int)unitManager.mainCharacter.getPosition().y/32);

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
