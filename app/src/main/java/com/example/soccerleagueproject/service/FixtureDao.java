package com.example.soccerleagueproject.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.soccerleagueproject.model.MatchModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FixtureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable addMatch(final MatchModel matchModel);

    @Query("Select * from matches Where week = :weekNum Limit(Select Count(Distinct home)/2 from matches) ")
    public List<MatchModel> getWeek (int weekNum);

    @Query("Select Max(week) from matches")
    public int getMaxWeek();

    @Query("DELETE FROM matches")
    public Completable deleteAll();
}
