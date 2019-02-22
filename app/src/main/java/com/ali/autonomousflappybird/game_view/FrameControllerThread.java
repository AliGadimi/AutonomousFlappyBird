package com.ali.autonomousflappybird.game_view;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import timber.log.Timber;

public class FrameControllerThread extends Thread
{
    private FlightSurface flightSurface;
    private SurfaceHolder holder;
    private boolean playing = false;


    private final int MAX_FRAME = 60;
    private final int TIME_PER_FRAME = 1000 / MAX_FRAME;

    private long frameStartTime;


    public FrameControllerThread(FlightSurface flightSurface)
    {
        this.flightSurface = flightSurface;
        this.holder = flightSurface.getHolder();
        updateView();
    }

    @Override
    public void run()
    {
        while (playing)
        {
            frameStartTime = System.currentTimeMillis();

            updateView();

            long frameTime = System.currentTimeMillis() - frameStartTime;
            long remainingTime = TIME_PER_FRAME - frameTime;
            if (remainingTime > 0)
            {
                try
                {
                    this.sleep(remainingTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateView()
    {
        Canvas canvas = null;
        try
        {
            canvas = holder.lockCanvas();
            synchronized (holder)
            {
                flightSurface.update();
                flightSurface.draw(canvas);
            }
        }
        catch (NullPointerException e)
        {
            Timber.d(e);
        }
        finally
        {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setPlaying(boolean playing)
    {
        this.playing = playing;
    }
}
