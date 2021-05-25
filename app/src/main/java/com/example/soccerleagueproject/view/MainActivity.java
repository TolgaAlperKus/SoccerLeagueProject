package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.soccerleagueproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://raw.githubusercontent.com/openfootball/football.json/master/2020-21/tr.1.clubs.json TR SuperLig
        //https://raw.githubusercontent.com/openfootball/football.json/master/2020-21/en.1.clubs.json En PremierLeague

    }
}