package com.ali.autonomousflappybird.models;

import android.graphics.Color;
import android.graphics.Paint;

public class Bird
{
    private float radius = 50;
    private int x = 100;
    private int y = 100;
    private Paint paint;
    private int color = Color.parseColor("#ED5485");
    private int speed = 5;


    public Bird()
    {
        paint = new Paint();
        paint.setColor(color);
    }


    public Paint getPaint()
    {
        return paint;
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }


    @Override
    public String toString()
    {
        return "Bird{" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }
}
