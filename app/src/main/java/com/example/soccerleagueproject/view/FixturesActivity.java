package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.adapter.FixtureFragmentCollectionAdapter;

public class FixturesActivity extends AppCompatActivity {

    private ViewPager fixtureActivityViewPager;
    private FixtureFragmentCollectionAdapter fixtureFragmentCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_fixtures);
        fixtureActivityViewPager = findViewById(R.id.activity_fixtures_viewpager);
        fixtureFragmentCollectionAdapter = new FixtureFragmentCollectionAdapter(getSupportFragmentManager());
        fixtureActivityViewPager.setAdapter(fixtureFragmentCollectionAdapter);

    }
    public void setAnimation()
    {
        if(Build.VERSION.SDK_INT>20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }
}