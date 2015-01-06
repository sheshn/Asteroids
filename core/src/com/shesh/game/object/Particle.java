package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.shesh.game.Vector2;

public class Particle extends GameObject
{
    private float alpha;

    public Particle(Vector2 position)
    {
        super(position, calcVelocity(), 1);

        alpha = MathUtils.random(0.1f, 1.0f);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        alpha -= delta;

        if (alpha <= 0)
        {
            alpha = 0;
            setRemovable(true);
        }
    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        super.render(renderer);

        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(1, 1, 1, alpha);
        renderer.rect(0, 0, 3.5f, 3.5f);
        renderer.setColor(1, 1, 1, 1);

        renderer.end();
        renderer.begin();
    }

    private static Vector2 calcVelocity()
    {
        return new Vector2(MathUtils.random(0, 360)).mul(MathUtils.random(100.0f, 200.0f));
    }

}
