package com.example.soccerleagueproject.model;

import java.util.ArrayList;

public class FixtureModel  {
    private ArrayList<MatchModel> matchModelArrayList;

    public FixtureModel(ArrayList<MatchModel> matchModelArrayList) {
        this.matchModelArrayList = matchModelArrayList;
    }
    public MatchModel getMatch(int index){
        return matchModelArrayList.get(index);
    }
    public void getAllMatches(){
        for(MatchModel matchModel : matchModelArrayList){
            System.out.println(matchModel);
        }
    }
}
