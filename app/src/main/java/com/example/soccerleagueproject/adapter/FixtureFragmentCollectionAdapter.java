package com.example.soccerleagueproject.adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.soccerleagueproject.service.FixtureAppDatabase;
import com.example.soccerleagueproject.view.FixtureFragment;
import com.example.soccerleagueproject.view.MainActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FixtureFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    public FixtureFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        FixtureFragment fixtureFragment = new FixtureFragment();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putString("message","Week : " + position);
        bundle.putInt("week",position);
        fixtureFragment.setArguments(bundle);
        return fixtureFragment;
    }

    @Override
    public int getCount() {
        return MainActivity.fixtureAppDatabase.fixtureDao().getMaxWeek();
    }
}
