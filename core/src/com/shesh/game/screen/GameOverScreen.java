package com.shesh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shesh.game.Asteroids;
import com.shesh.game.ButtonListener;

public class GameOverScreen implements Screen
{
    private Asteroids game;
    private Stage stage;
    private ButtonListener listener;

    public GameOverScreen(Asteroids game)
    {
        this.game = game;
        this.listener = new ButtonListener(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        game.getGameScreen().manualUpdate(delta);

        game.getRenderer().setColor(1, 1, 1, 0.2f);
        game.getGameScreen().manualRenderGame();
        game.getRenderer().setColor(1, 1, 1, 1);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage = new Stage(new StretchViewport(Asteroids.WIDTH, Asteroids.HEIGHT), game.getBatch());
        stage.clear();

        Gdx.input.setInputProcessor(stage);

        TextButton startButton = new TextButton("Restart", Asteroids.skinUI);
        startButton.setName("Restart");
        startButton.setSize(110, 30);
        startButton.setPosition(Asteroids.WIDTH / 2 - startButton.getWidth() / 2, Asteroids.HEIGHT / 2 - startButton.getHeight() / 2);
        startButton.addListener(listener);

        TextButton menuButton = new TextButton("Menu", Asteroids.skinUI);
        menuButton.setName("Menu");
        menuButton.setSize(110, 30);
        menuButton.setPosition(Asteroids.WIDTH / 2 - menuButton.getWidth() / 2, Asteroids.HEIGHT / 2 - menuButton.getHeight() / 2 - 50);
        menuButton.addListener(listener);

        Label title = new Label("Game Over", Asteroids.skinUI);
        title.setPosition(0, Asteroids.HEIGHT / 2 + 100);
        title.setWidth(Asteroids.WIDTH);
        title.setFontScale(1.2f);
        title.setAlignment(Align.center);

        Label score = new Label("Score: " + game.getGameScreen().getLevel().getScore(), Asteroids.skinUI);
        score.setPosition(0, Asteroids.HEIGHT / 2 + 50);
        score.setWidth(Asteroids.WIDTH);
        score.setAlignment(Align.center);

        stage.addActor(startButton);
        stage.addActor(menuButton);
        stage.addActor(title);
        stage.addActor(score);
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
