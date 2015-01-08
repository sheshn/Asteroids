package com.shesh.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.shesh.game.screen.ExperimentalMenu;
import com.shesh.game.screen.GameScreen;
import com.shesh.game.screen.MenuScreen;
import com.shesh.game.screen.PauseScreen;

public class Asteroids extends Game
{
	public static float WIDTH = 800;
	public static float HEIGHT = 480;

	public static BitmapFont font;
	public static Skin skinUI;

	public static Input input;

	private OrthographicCamera camera;
	private ShapeRenderer renderer;
	private SpriteBatch batch;

	private MenuScreen menuScreen;
	private ExperimentalMenu gameScreen;
	private PauseScreen pauseScreen;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		renderer = new ShapeRenderer();
		batch = new SpriteBatch();

		camera.update();

		renderer.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);

		renderer.setAutoShapeType(true);

		font = new BitmapFont(Gdx.files.internal("fonts/hyperspace.fnt"), false);
		skinUI = new Skin(Gdx.files.internal("skins/uiskin.json"));
		input = new Input();

		menuScreen = new MenuScreen(this);
		gameScreen = new ExperimentalMenu(this);
		pauseScreen = new PauseScreen(this);

		setScreen(gameScreen);
	}

	public void restartGame(boolean fromStartingScreen)
	{
		//gameScreen = new ExperimentalMenu(this);
		setScreen(gameScreen);
		if (fromStartingScreen) {
			gameScreen.startGame();
		} else {
			gameScreen.restartGame();
		}
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public ShapeRenderer getRenderer() { return renderer; }

	public SpriteBatch getBatch() { return batch; }

	public MenuScreen getMenuScreen()
	{
		return menuScreen;
	}

	public ExperimentalMenu getGameScreen()
	{
		return gameScreen;
	}

	public void setGameScreen(ExperimentalMenu screen) { gameScreen = screen; }

	public PauseScreen getPauseScreen()
	{
		return pauseScreen;
	}
}
