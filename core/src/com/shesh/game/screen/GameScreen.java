package com.shesh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.shesh.game.Asteroids;
import com.shesh.game.Input;
import com.shesh.game.Level;

public class GameScreen implements Screen
{
    private Asteroids game;

    private OrthographicCamera camera;
    private Level level;

    public GameScreen(Asteroids game)
    {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Asteroids.WIDTH, Asteroids.HEIGHT);

        this.level = new Level();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(Asteroids.input);
    }

    @Override
    public void render(float delta) {
        if (Asteroids.input.isKeyPressed(Input.P))
            game.setScreen(game.getPauseScreen());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        manualUpdate(delta);
        camera.update();

        game.getRenderer().setProjectionMatrix(camera.combined);
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getRenderer().begin();
        level.render(game.getRenderer());
        game.getRenderer().end();

        game.getBatch().begin();
        level.getStatusBar().renderText(game.getBatch());
        game.getBatch().end();

        if (level.isGameOver())
            game.setScreen(new GameOverScreen(game));

        Asteroids.input.update();
    }

    public void manualRender() {
        manualRenderGame();
        manualRenderText();
    }

    public void manualRenderGame() {
        game.getRenderer().begin();
        level.render(game.getRenderer());
        game.getRenderer().end();
    }

    public void manualRenderText() {
        game.getBatch().begin();
        level.getStatusBar().renderText(game.getBatch());
        game.getBatch().end();
    }

    public void manualUpdate(float delta) {
        level.update(delta);
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

    public Level getLevel()
    {
        return level;
    }
}
