package com.shesh.game;

public class Vector2
{
    private float x, y;

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2(float angle)
    {
        this.x = (float) Math.cos(angle);
        this.y = (float) Math.sin(angle);
    }

    public Vector2 add(Vector2 v)
    {
        return add(v.x, v.y);
    }

    public Vector2 add(float v)
    {
        return add(v, v);
    }

    public Vector2 add(float x, float y)
    {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 sub(Vector2 v)
    {
        return sub(v.x, v.y);
    }

    public Vector2 sub(float v)
    {
        return sub(v, v);
    }

    public Vector2 sub(float x, float y)
    {
        return new Vector2(this.x - x, this.y - y);
    }

    public Vector2 mul(Vector2 v)
    {
        return mul(v.x, v.y);
    }

    public Vector2 mul(float v)
    {
        return mul(v, v);
    }

    public Vector2 mul(float x, float y)
    {
        return new Vector2(this.x * x, this.y * y);
    }

    public Vector2 div(Vector2 v)
    {
        return div(v.x, v.y);
    }

    public Vector2 div(float v)
    {
        return div(v, v);
    }

    public Vector2 div(float x, float y)
    {
        return new Vector2(this.x / x, this.y / y);
    }

    public float length()
    {
        return (float) Math.sqrt(this.dot(this));
    }

    public float dot(Vector2 v)
    {
        return x * v.x + y * v.y;
    }

    public Vector2 normalize()
    {
        float length = length();

        return new Vector2(x / length, y / length);
    }

    public Vector2 rotate(float angle)
    {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        return new Vector2((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    public float getDistanceTo(Vector2 r)
    {
        float dx = this.x - r.x;
        float dy = this.y - r.y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString()
    {
        return "(" + x + " " + y + ")";
    }

    public void set(Vector2 v)
    {
        set(v.x, v.y);
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public boolean equals(Vector2 v)
    {
        return x == v.x && y == v.y;
    }
}