package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shesh.game.Asteroids;
import com.shesh.game.Input;
import com.shesh.game.Level;
import com.shesh.game.Vector2;

import java.util.List;

public class Ship extends GameObject
{
    private static final Vector2 DEFAULT_POSITION = new Vector2(Asteroids.WIDTH / 2, Asteroids.HEIGHT / 2);
    private static final Vector2 DEFAULT_VELOCITY = new Vector2(0, 0);
    private static final float DEFAULT_ROTATION = (float) (Math.PI / 2);

    private static final float RADIUS = 10;

    private static final float ROTATION_SPEED = 10;
    private static final float SPEED = 8;
    private static final float MAX_SPEED = 400;
    private static final float FRICTION = 0.985f;

    private static final float MAX_SHOT_COOLDOWN = 0.4f;
    private static final float MAX_INVINCIBLE_TIME = 2;

    private List<Bullet> bullets;

    private float shotTime = 0;
    private float invincibleTime = 0;
    private float animation = 0;

    private boolean dead = false;
    private boolean thrust = false;

    public Ship(List<Bullet> bullets)
    {
        super(DEFAULT_POSITION, DEFAULT_VELOCITY, RADIUS);

        this.bullets = bullets;
        reset();
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        animation += delta;

        if (Asteroids.input.isKeyDown(Input.LEFT) != Asteroids.input.isKeyDown(Input.RIGHT))
            rotate(Asteroids.input.isKeyDown(Input.LEFT) ? ROTATION_SPEED * delta : -ROTATION_SPEED * delta);

        if (Asteroids.input.isKeyDown(Input.UP))
        {
            thrust = true;

            velocity = velocity.add(new Vector2(rotation).mul(SPEED));

            if (velocity.length() > MAX_SPEED)
            {
                velocity = velocity.normalize().mul(MAX_SPEED);
            }
        }
        else
        {
            thrust = false;
            velocity = velocity.mul(FRICTION);
        }

        if (Asteroids.input.isKeyDown(Input.SPACE) && canShoot())
        {
            shotTime = MAX_SHOT_COOLDOWN;
            bullets.add(new Bullet(this));
        }

        updateTimers(delta);
    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        super.render(renderer);

        if (!isInvincible() || animation % 0.4 < 0.2)
        {
            renderer.line(-10, -8, 10, 0);
            renderer.line(-10, 8, 10, 0);
            renderer.line(-6, -6, -6, 6);

            if (thrust && animation % 0.133 < 0.067)
            {
                renderer.line(-6, -6, -12, 0);
                renderer.line(-6, 6, -12, 0);
            }
        }
    }

    @Override
    public void handleCollision(Level level, GameObject o)
    {
        level.getExplosions().add(new Explosion(position));
        dead = true;
    }

    public void reset()
    {
        position = DEFAULT_POSITION;
        velocity = DEFAULT_VELOCITY;
        rotation = DEFAULT_ROTATION;

        dead = false;
        thrust = false;

        invincibleTime = MAX_INVINCIBLE_TIME;
    }

    private void updateTimers(float delta)
    {
        shotTime = shotTime > 0 ? shotTime - delta : 0;
        invincibleTime = invincibleTime > 0 ? invincibleTime - delta : 0;
    }

    public boolean canShoot()
    {
        return shotTime <= 0;
    }

    public boolean isInvincible()
    {
        return invincibleTime > 0;
    }

    public boolean isDead()
    {
        return dead;
    }
}
