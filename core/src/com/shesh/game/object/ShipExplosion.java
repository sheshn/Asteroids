package com.shesh.game.object;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.shesh.game.Vector2;

public class ShipExplosion extends Explosion {
    
    private static final float SHIP_EXPLOSION_SPEED = 70;

    private float alpha;
    private float pos;

    public ShipExplosion(Ship ship)
    {
        super(ship.getPosition(), new Vector2(0, 0), 0);

        this.rotation = ship.getRotation();
        this.alpha = 1;
        this.pos = 0;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        alpha -= delta / 2;
        pos += delta;

        if (alpha <= 0)
        {
            alpha = 0;
            setRemovable(true);
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {
        super.render(renderer);

        Matrix4 transform = renderer.getTransformMatrix();
        Matrix4 m1 = transform.cpy().translate(0, pos * -SHIP_EXPLOSION_SPEED, 0);
        Matrix4 m2 = transform.cpy().translate(0, pos * SHIP_EXPLOSION_SPEED, 0);
        Matrix4 m3 = transform.cpy().translate(pos * -SHIP_EXPLOSION_SPEED, 0, 0);

        renderer.setColor(1, 1, 1, alpha);

        renderer.setTransformMatrix(m1);
        renderer.line(-10, -8, 10, 0);

        renderer.setTransformMatrix(m2);
        renderer.line(-10, 8, 10, 0);

        renderer.setTransformMatrix(m3);
        renderer.line(-6, -6, -6, 6);

        renderer.setColor(1, 1, 1, 1);
    }
}
