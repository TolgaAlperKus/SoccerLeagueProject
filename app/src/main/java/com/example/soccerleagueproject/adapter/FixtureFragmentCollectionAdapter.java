package com.example.soccerleagueproject.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.soccerleagueproject.model.MatchModel;
import com.example.soccerleagueproject.view.FixtureFragment;
import com.example.soccerleagueproject.view.MainActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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