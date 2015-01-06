package com.shesh.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StatusBar
{
    private Level level;

    public StatusBar(Level level)
    {
        this.level = level;
    }

    public void render(ShapeRenderer renderer)
    {
        int x = 40;

        for (int i = 0; i < level.getLives(); i++)
        {
            renderer.identity();
            renderer.translate(x, Asteroids.HEIGHT - 78, 0);
            renderer.rotate(0, 0, 1, (float) Math.toDegrees(Math.PI / 2));

            renderer.line(-10, -8, 10, 0);
            renderer.line(-10, 8, 10, 0);
            renderer.line(-6, -6, -6, 6);

            x += 16;
        }
    }

    public void renderText(SpriteBatch batch) {
        Asteroids.font.draw(batch, "" + level.getScore(), 35, Asteroids.HEIGHT - 35);
    }
}
