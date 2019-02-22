package com.ali.autonomousflappybird.stuff;

import timber.log.Timber;

/**
 * Created by ali on 7/24/18.
 */

public class NoLogTree extends Timber.Tree
{
    @Override
    protected void log(int priority, String tag, String message, Throwable t)
    {
    }
}
