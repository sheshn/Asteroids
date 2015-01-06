package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.shesh.game.Vector2;

import java.util.ArrayList;
import java.util.List;

public class AsteroidExplosion extends Explosion {

    private List<Particle> particles;

    public AsteroidExplosion(Vector2 position)
    {
        super(position, new Vector2(0, 0), 1);

        particles = new ArrayList<Particle>();

        int numParticles = MathUtils.random(8, 15);

        for (int i = 0; i < numParticles; i++)
        {
            particles.add(new Particle(position));
        }
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        for (int i = 0; i < particles.size(); i++)
        {
            Particle p = particles.get(i);
            p.update(delta);

            if (p.isRemovable())
                particles.remove(p);
        }

        if (particles.isEmpty())
            setRemovable(true);

    }

    @Override
    public void render(ShapeRenderer renderer)
    {
        super.render(renderer);

        for (Particle p : particles)
        {
            p.render(renderer);
        }
    }
}
