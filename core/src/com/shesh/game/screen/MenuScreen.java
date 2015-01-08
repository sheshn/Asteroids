package com.shesh.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shesh.game.Asteroids;
import com.shesh.game.object.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements Screen
{
    private Asteroids game;
    private Stage stage;
    private ButtonListener listener;

    private List<Asteroid> asteroids;

    public MenuScreen(Asteroids game)
    {
        this.game = game;
        this.listener = new ButtonListener(game);
    }

    @Override
    public void show() {
        asteroids = new ArrayList<Asteroid>();

        for(int i = 0; i < 5; i++) {
            asteroids.add(new Asteroid());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        game.getRenderer().begin();
        game.getRenderer().setColor(1, 1, 1, 0.2f);

        for (Asteroid a : asteroids) {
            a.update(delta);
            a.render(game.getRenderer());
        }

        game.getRenderer().setColor(1, 1, 1, 1);
        game.getRenderer().end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage = new Stage(new StretchViewport(Asteroids.WIDTH, Asteroids.HEIGHT), game.getBatch());
        stage.clear();

        Gdx.input.setInputProcessor(stage);

        TextButton startButton = new TextButton("Start", Asteroids.skinUI);
        startButton.setName("Restart");
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

    static public class ButtonListener extends ClickListener {

        private Asteroids game;

        public ButtonListener(Asteroids game) {
            this.game = game;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            String name = event.getListenerActor().getName();

            if (name.equals("Start")) {
                game.restartGame(true);
            } else if (name.equals("Restart")) {
                game.restartGame(false);
            } else if (name.equals("Resume")) {
                game.setScreen(game.getGameScreen());
            } else if (name.equals("Menu")) {
                game.setGameScreen(new ExperimentalMenu(game));
                game.setScreen(game.getGameScreen());
            }
        }
    }
}