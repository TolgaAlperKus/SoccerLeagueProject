package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.adapter.FixtureFragmentCollectionAdapter;

public class FixturesActivity extends AppCompatActivity {

    private ViewPager fixtureActivityViewPager;
    private FixtureFragmentCollectionAdapter fixtureFragmentCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        fixtureActivityViewPager = findViewById(R.id.activity_fixtures_viewpager);
        fixtureFragmentCollectionAdapter = new FixtureFragmentCollectionAdapter(getSupportFragmentManager());
        fixtureActivityViewPager.setAdapter(fixtureFragmentCollectionAdapter);

    }
}