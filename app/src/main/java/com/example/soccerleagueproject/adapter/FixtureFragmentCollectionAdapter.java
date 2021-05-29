package com.example.soccerleagueproject.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.soccerleagueproject.view.FixtureFragment;
import com.example.soccerleagueproject.view.MainActivity;

public class FixtureFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    int count;
    public FixtureFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
        count = MainActivity.fixtureAppDatabase.fixtureDao().getMaxWeek();
    }

    @Override
    public Fragment getItem(int position) {
        FixtureFragment fixtureFragment = new FixtureFragment();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putString("message","WEEK " + position);
        bundle.putInt("week",position);
        fixtureFragment.setArguments(bundle);
        return fixtureFragment;
    }


    @Override
    public int getCount() {
        return count;
    }
}