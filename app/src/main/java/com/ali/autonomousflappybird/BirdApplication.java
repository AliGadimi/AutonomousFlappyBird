package com.ali.autonomousflappybird;

import android.app.Application;

import com.ali.autonomousflappybird.stuff.NoLogTree;

import timber.log.Timber;

public class BirdApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        else
        {
            Timber.plant(new NoLogTree());
        }
    }
}
