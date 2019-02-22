package com.ali.autonomousflappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ali.autonomousflappybird.models.Bird;

import timber.log.Timber;

public class FlightSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private SurfaceHolder holder;
    private Thread thread;
    private Bird bird = new Bird();
    private boolean playing = false;


    private final int FRAME_COUNT = 30;
    private final int TIME_PER_FRAME = 1000 / FRAME_COUNT;

    private long frameStartTime;

    public FlightSurface(Context context)
    {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        thread = new Thread(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        drawScene();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }


    private void drawScene()
    {
        frameStartTime = System.currentTimeMillis();
        //draw background
        Canvas canvas = null;

        try
        {
            canvas = holder.lockCanvas();
            canvas.drawColor(getContext().getResources().getColor(R.color.one));

            if (playing)
            {
                canvas.drawCircle(bird.getX(), bird.getY(), bird.getRadius(), bird.getPaint());
            }

            holder.unlockCanvasAndPost(canvas);
        }
        catch (NullPointerException e)
        {
            Timber.d(e);
        }
    }

    @Override
    public void run()
    {
//        Timber.d("One frame passed");
        bird.setY(bird.getY() + 1);
        drawScene();

        if (playing)
        {
            long passedTime = System.currentTimeMillis() - frameStartTime;

            if (passedTime < TIME_PER_FRAME)
            {
                try
                {
                    thread.wait(TIME_PER_FRAME - passedTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            thread.start();
        }
    }


    public void start()
    {
        playing = true;
        thread.start();
    }

    public void stop()
    {

    }
}
