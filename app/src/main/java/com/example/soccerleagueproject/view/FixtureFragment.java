package com.example.soccerleagueproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.InvalidationTracker;

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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FixtureFragment extends Fragment {

    private TextView textView;
    ListView fixtureListView;
    ArrayAdapter arrayAdapter;
    int teamCount;
    List<MatchModel> matchModelList;

    public FixtureFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        teamCount = MainActivity.fixtureAppDatabase.fixtureDao().teamCount();
        teamCount = teamCount/2;
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