package com.ali.autonomousflappybird.game_view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ali.autonomousflappybird.R;
import com.ali.autonomousflappybird.models.Bird;

public class FlightSurface extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder holder;
    private FrameControllerThread thread;
    private Bird bird = new Bird();
    private boolean playing = false;
    private int acceleration = 100;
    private int initialVelocity = 0;
    private long jumpStartTime;


    public FlightSurface(Context context)
    {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new FrameControllerThread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        try
        {
            thread.setPlaying(false);
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawColor(getContext().getResources().getColor(R.color.one));
        canvas.drawCircle(bird.getX(), bird.getY(), bird.getRadius(), bird.getPaint());
    }

    public void update()
    {
        long currTime = System.currentTimeMillis();
        float t = jumpStartTime == 0 ? jumpStartTime : (float) (currTime - jumpStartTime) / 1000;
//        Timber.d("start: %d, curr: %d, t: %f", jumpStartTime, currTime, t);
        int v = (int) (initialVelocity + (acceleration * t));
        int y = bird.getY() + (int) (v * t);


        if (y > getHeight() + bird.getRadius())
        {
            y = 100;
            jumpStartTime = System.currentTimeMillis();
        }

        bird.setY(y);

//        Timber.d("vel: %d, y: %d, t: %f", initialVelocity, y, t);
    }


    private float clickX, clickY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {


        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                clickX = event.getX();
                clickY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();

                double dist = Math.sqrt(Math.pow((y - clickY), 2) + Math.pow((x - clickX), 2));
                if (dist < 100)
                {
                    onClick();
                }

                break;
        }

        return super.dispatchTouchEvent(event);
    }

    private void onClick()
    {
        jumpStartTime = System.currentTimeMillis();
        initialVelocity = -10;
    }

    public void start()
    {
        jumpStartTime = System.currentTimeMillis();
        if (thread != null)
        {
            thread.setPlaying(true);
            thread.start();
        }
    }

    public void stop()
    {

    }
}
