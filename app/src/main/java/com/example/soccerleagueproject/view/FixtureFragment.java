package com.example.soccerleagueproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.model.MatchModel;
import com.example.soccerleagueproject.model.TeamModel;

import java.util.List;

public class FixtureFragment extends Fragment {

    private TextView textView;
    ListView fixtureListView;
    ArrayAdapter arrayAdapter;

    public FixtureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int teamCount = MainActivity.fixtureAppDatabase.fixtureDao().teamCount();
        teamCount = teamCount/2;
        List<MatchModel> matchModelList;
        View view = inflater.inflate(R.layout.fragment_fixture,container,false);
        textView = view.findViewById(R.id.fragment_fixture_week_textview);
        String message = getArguments().getString("message");
        int week = getArguments().getInt("week");
        matchModelList = MainActivity.fixtureAppDatabase.fixtureDao().getWeek(week,teamCount);
        textView.setText(message);
        fixtureListView = view.findViewById(R.id.fragment_fixture_fixturelist);
        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_layout,matchModelList);
        fixtureListView.setAdapter(arrayAdapter);


        return view;
    }
}