package com.shesh.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.shesh.game.screen.GameScreen;

public class ButtonListener extends ClickListener{

    private Asteroids game;

    public ButtonListener(Asteroids game) {
        this.game = game;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        String name = event.getListenerActor().getName();

        if (name.equals("Start")) {
            game.setScreen(game.getGameScreen());
            game.getGameScreen().startGame(false);
        } else if (name.equals("Restart")) {
            game.setScreen(game.getGameScreen());
            game.getGameScreen().startGame(true);
        } else if (name.equals("Resume")) {
            game.setScreen(game.getGameScreen());
        } else if (name.equals("Menu")) {
            game.setGameScreen(new GameScreen(game));
            game.setScreen(game.getGameScreen());
        }
    }
}
