package com.example.soccerleagueproject.service;

import com.example.soccerleagueproject.model.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerApi {
    //TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/superlig.json TR SuperLig
    //TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/premierleague.json
    @GET("TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/superlig.json")
    Call<List<TeamModel>> getData();
}
