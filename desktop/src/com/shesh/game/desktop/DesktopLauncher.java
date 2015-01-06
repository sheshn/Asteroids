package com.shesh.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shesh.game.Asteroids;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Asteroids";
		config.width = 800;
		config.height = 600;
		config.resizable = true;
		new LwjglApplication(new Asteroids(), config);
	}
}
