package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shesh.game.Level;
import com.shesh.game.Vector2;

public class Bullet extends GameObject
{
    private static final float SPEED = 550;
    private static final float MAX_LIFE = 0.8f;

    private float lifeSpan;

    public Bullet(GameObject parent)
    {
        super(parent.getPosition(), new Vector2(parent.getRotation()).mul(SPEED), 2);

        lifeSpan = MAX_LIFE;
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        lifeSpan -= delta;

        if (lifeSpan < 0)
            setRemovable(true);
    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        super.render(renderer);

        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.circle(0, 0, 1);

        renderer.end();
        renderer.begin();
    }

    @Override
    public void handleCollision(Level level, GameObject o)
    {
        setRemovable(true);
    }
}
