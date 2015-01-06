package com.shesh.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.shesh.game.screen.GameScreen;
import com.shesh.game.screen.MenuScreen;
import com.shesh.game.screen.PauseScreen;

public class Asteroids extends Game
{
	public static float WIDTH = 800;
	public static float HEIGHT = 600;

	public static BitmapFont font;
	public static Skin skinUI;

	public static Input input;

	private ShapeRenderer renderer;
	private SpriteBatch batch;

	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private PauseScreen pauseScreen;

	@Override
	public void create() {
		renderer = new ShapeRenderer();
		batch = new SpriteBatch();

		renderer.setAutoShapeType(true);

		font = new BitmapFont(Gdx.files.internal("fonts/hyperspace.fnt"), false);
		skinUI = new Skin(Gdx.files.internal("skins/uiskin.json"));
		input = new Input();

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		pauseScreen = new PauseScreen(this);

		setScreen(menuScreen);
	}

	public void restartGame()
	{
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	public ShapeRenderer getRenderer() { return renderer; }

	public SpriteBatch getBatch() { return batch; }

	public MenuScreen getMenuScreen()
	{
		return menuScreen;
	}

	public GameScreen getGameScreen()
	{
		return gameScreen;
	}

	public PauseScreen getPauseScreen()
	{
		return pauseScreen;
	}
}
