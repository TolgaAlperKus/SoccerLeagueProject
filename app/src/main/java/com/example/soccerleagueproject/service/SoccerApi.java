package com.example.soccerleagueproject.service;

import com.example.soccerleagueproject.model.TeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerApi {
    //denemek icin ornek takim listeleri
    //21 takim = TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/superlig.json
    //20 takim = TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/premierleague.json
    @GET("TolgaAlperKus/SoccerLeagueProject/main/DummyRestAPI/premierleague.json")
    Call<List<TeamModel>> getData();
}
