package com.example.soccerleagueproject.service;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.soccerleagueproject.model.MatchModel;

@Database(entities = {MatchModel.class},version = 1)
public abstract class FixtureAppDatabase extends RoomDatabase {

    public abstract  FixtureDao fixtureDao();
}
