package com.example.soccerleagueproject.service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.soccerleagueproject.model.MatchModel;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

@Dao
public interface FixtureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addMatch(final MatchModel matchModel);

    @Query("Select * from matches Where week = :weekNum Limit(:matchNum) ")
    public List<MatchModel> getWeek (int weekNum, int matchNum);

    @Query("Select Count (Distinct home)from matches")
    public int teamCount();

    @Query("Select Max(week) from matches")
    public int getMaxWeek();

}
