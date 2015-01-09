package com.shesh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shesh.game.Asteroids;
import com.shesh.game.ButtonListener;
import com.shesh.game.Input;
import com.shesh.game.Level;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GameScreen implements Screen {

    private Asteroids game;
    private Stage stage;
    private ButtonListener listener;
    private Level level;

    private boolean gameStarted;
    private boolean showStage;

    public GameScreen(Asteroids game) {
        this.game = game;
        this.listener = new ButtonListener(game);
        this.gameStarted = false;
        this.showStage = true;
        this.level = new Level();
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

        manualUpdate(delta);
        game.getCamera().update();

        game.getRenderer().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().setProjectionMatrix(game.getCamera().combined);

        if (!gameStarted) {
            game.getRenderer().begin();
            game.getRenderer().setColor(1, 1, 1, 0.2f);

            level.render(game.getRenderer());

            game.getRenderer().setColor(1, 1, 1, 1);
            game.getRenderer().end();
        } else {
            if (Asteroids.input.isKeyPressed(Input.P))
                game.setScreen(game.getPauseScreen());

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

        if (showStage) {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage = new Stage(new StretchViewport(Asteroids.WIDTH, Asteroids.HEIGHT), game.getBatch());
        stage.clear();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(Asteroids.input);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

        TextButton startButton = new TextButton("Start", Asteroids.skinUI);
        startButton.setName("Start");
        startButton.setSize(100, 30);
        startButton.setPosition(Asteroids.WIDTH / 2 - startButton.getWidth() / 2, Asteroids.HEIGHT / 2 - startButton.getHeight() / 2);
        startButton.addListener(listener);

        Label title = new Label("Asteroids", Asteroids.skinUI);
        title.setPosition(0, Asteroids.HEIGHT / 2 + 100);
        title.setWidth(Asteroids.WIDTH);
        title.setFontScale(1.2f);
        title.setAlignment(Align.center);

        stage.addActor(startButton);
        stage.addActor(title);
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

    public void manualUpdate(float delta) {
        level.update(delta);
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

    public void startGame(boolean restart) {
        if (restart) {
            level = new Level();
        } else {
            stage.addAction(sequence(fadeOut(0.6f, Interpolation.fade), run(new Runnable() {
                public void run() {
                    showStage = false;
                }
            })));
        }

        gameStarted = true;
        level.startGame();
    }

    public Level getLevel()
    {
        return level;
    }
}
