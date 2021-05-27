package com.example.soccerleagueproject.model;

public class MatchModel {
    private String match;

    public MatchModel(String match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return match;
    }
}
