package com.ali.autonomousflappybird;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.self_holder)
    public void selfHolder()
    {
        startActivity(new Intent(this, PlayActivity.class));
    }

    @OnClick(R.id.auto_holder)
    public void autoHolder()
    {

    }
}
