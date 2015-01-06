package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.shesh.game.Asteroids;
import com.shesh.game.Level;
import com.shesh.game.Vector2;

public class Asteroid extends GameObject
{
    private static final int SCORE_LARGE = 20;
    private static final int SCORE_MEDIUM = 50;
    private static final int SCORE_SMALL = 100;

    private static final int SIZE_LARGE = 40;
    private static final int SIZE_MEDIUM = 20;
    private static final int SIZE_SMALL = 12;

    private float[] points;

    public Asteroid()
    {
        this(calcPosition(), calcVelocity(), SIZE_LARGE);
    }

    public Asteroid(Asteroid parent, int size)
    {
        this(parent.position, calcVelocity(), size);
    }

    public Asteroid(Vector2 position, Vector2 velocity, int size)
    {
        super(position, velocity, size);

        generateAsteroid();
    }

    private void generateAsteroid()
    {
        int numPoints;

        if (radius == SIZE_SMALL)
            numPoints = 8;
        else if (radius == SIZE_MEDIUM)
            numPoints = 10;
        else
            numPoints = 12;

        points = new float[numPoints * 2];

        float angle = 0;
        for (int i = 0; i < numPoints; i+=2)
        {
            points[i] = (int) (Math.cos(angle) * MathUtils.random(radius / 2.0f, radius));
            points[i + 1] = (int) (Math.sin(angle) * MathUtils.random(radius / 2.0f, radius));
            angle += 2 * Math.PI / numPoints;
        }
    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        super.render(renderer);

        renderer.polygon(points);
    }

    @Override
    public void handleCollision(Level level, GameObject o)
    {
        if (radius != SIZE_SMALL)
        {
            for (int i = 0; i < 2; i++)
            {
                if (radius == SIZE_LARGE)
                    level.getAsteroids().add(new Asteroid(this, SIZE_MEDIUM));
                else
                    level.getAsteroids().add(new Asteroid(this, SIZE_SMALL));

            }
        }

        if (o.getClass() != Ship.class)
        {
            switch ((int)radius) {
                case SIZE_LARGE:
                    level.addScore(SCORE_LARGE);
                    break;
                case SIZE_MEDIUM:
                    level.addScore(SCORE_MEDIUM);
                    break;
                default:
                    level.addScore(SCORE_SMALL);
                    break;
            }
        }

        level.getExplosions().add(new Explosion(position));
        setRemovable(true);
    }

    private static Vector2 calcPosition()
    {
        int width = (int) Asteroids.WIDTH + 100;
        int height = (int) Asteroids.HEIGHT + 100;

        int random = MathUtils.random(1, 4);

        if (random == 1)
            return new Vector2(MathUtils.random(width, width + 10), MathUtils.random(0, height));
        else if (random == 2)
            return new Vector2(MathUtils.random(0, 10) - 50, MathUtils.random(0, height));
        else if (random == 3)
            return new Vector2(MathUtils.random(0, width), MathUtils.random(height, height + 10));
        else
            return new Vector2(MathUtils.random(0, width), MathUtils.random(0, 10) - 50);
    }

    private static Vector2 calcVelocity()
    {
        return new Vector2(MathUtils.random(0, 360)).mul(MathUtils.random(100.0f, 250.0f));
    }

}
