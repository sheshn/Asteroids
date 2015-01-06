package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shesh.game.Asteroids;
import com.shesh.game.Level;
import com.shesh.game.Vector2;

public abstract class GameObject
{
    protected Vector2 position;
    protected Vector2 velocity;

    protected float rotation;
    protected float radius;

    protected boolean removable;

    public GameObject(Vector2 position, Vector2 velocity, float radius)
    {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
    }

    public void update(float delta)
    {
        position = position.add(velocity.mul(delta));

        if (position.getX() < 0)
        {
            position.setX(Asteroids.WIDTH);
        }
        else if (position.getX() > Asteroids.WIDTH)
        {
            position.setX(0);
        }
        if (position.getY() < 0)
        {
            position.setY(Asteroids.HEIGHT);
        }
        else if (position.getY() > Asteroids.HEIGHT)
        {
            position.setY(0);
        }
    }

    public void render(ShapeRenderer renderer)
    {
        renderer.identity();
        renderer.translate(position.getX(), position.getY(), 0);
        renderer.rotate(0, 0, 1, (float) Math.toDegrees(rotation));
    }

    public void rotate(float amount)
    {
        rotation += amount;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getVelocity()
    {
        return position;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public float getRotation()
    {
        return rotation;
    }

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public boolean isRemovable()
    {
        return removable;
    }

    public void setRemovable(boolean removable)
    {
        this.removable = removable;
    }

    public boolean checkCollision(GameObject o)
    {
        float distance = o.radius + radius;

        return (position.getDistanceTo(o.position) < distance);
    }

    public void handleCollision(Level level, GameObject o)
    {

    }
}
