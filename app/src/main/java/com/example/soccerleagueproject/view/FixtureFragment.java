package com.example.soccerleagueproject.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.soccerleagueproject.R;

public class FixtureFragment extends Fragment {

    private TextView textView;

    public FixtureFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixture,container,false);
        textView = view.findViewById(R.id.fragment_fixture_week_textview);
        String message = getArguments().getString("message");
        textView.setText(message);

        return view;
    }
}