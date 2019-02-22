package com.ali.autonomousflappybird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ali.autonomousflappybird.game_view.FlightSurface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayActivity extends AppCompatActivity
{
    @BindView(R.id.flight_holder) FrameLayout flightHolder;
    @BindView(R.id.score_tv) TextView scoreTv;
    @BindView(R.id.play_pack) LinearLayout playPack;

    private FlightSurface flightSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);

        flightSurface = new FlightSurface(this);
        flightHolder.addView(flightSurface);
    }


    @OnClick(R.id.play_btn)
    public void playBtn()
    {
        scoreTv.setVisibility(View.GONE);
        playPack.setVisibility(View.GONE);
        flightSurface.start();
    }
}
