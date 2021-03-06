package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.model.MatchModel;
import com.example.soccerleagueproject.model.TeamModel;
import com.example.soccerleagueproject.service.FixtureAppDatabase;
import com.example.soccerleagueproject.service.SoccerApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<TeamModel> teamModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    ListView teamNamesListView;
    ArrayAdapter arrayAdapter;
    FloatingActionButton floatingActionButton;
    public static FixtureAppDatabase fixtureAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton =findViewById(R.id.activity_main_draw_fixtures_floating_button);
        teamNamesListView = findViewById(R.id.activity_main_listview);
        showui(true);
        fixtureAppDatabase = Room.inMemoryDatabaseBuilder(getApplicationContext(),FixtureAppDatabase.class).allowMainThreadQueries().build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        showui(true);
    }

    private void loadData(){
        SoccerApi soccerApi = retrofit.create(SoccerApi.class);
        Call<List<TeamModel>> call = soccerApi.getData();
        call.enqueue(new Callback<List<TeamModel>>() {
            @Override
            public void onResponse(Call<List<TeamModel>> call, Response<List<TeamModel>> response) {
                if(response.isSuccessful()){
                    List<TeamModel> responseTeamNameList = response.body();
                    teamModels = new ArrayList<>(responseTeamNameList);
                    arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.custom_layout,teamModels);
                    teamNamesListView.setAdapter(arrayAdapter);

                    for (TeamModel teamModel : teamModels){
                        System.out.println(teamModel.teamName);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<TeamModel>> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }

    public void drawFixtureOnClick (View view){
        showui(false);
        int teamSize = teamModels.size();
        int roundCount=(teamSize-1)*2;
        int matchCountPerRound=teamSize/2;

        for (int i = 0; i < roundCount; i++) {
            for(int j=0;j<matchCountPerRound;j++){
                int firstIndex=j;
                int secondIndex=(teamSize-1)-j;
                MatchModel match = new MatchModel();
                match.setHomeTeam(teamModels.get(firstIndex).teamName);
                match.setAwayTeam(teamModels.get(secondIndex).teamName);
                match.setWeek(i+1);
                MainActivity.fixtureAppDatabase.fixtureDao().addMatch(match);
            }
            ArrayList<TeamModel> newList=new ArrayList<TeamModel>();
            newList.add(teamModels.get(0));
            newList.add(teamModels.get(teamModels.size()-1));
            for(int k=1;k<teamModels.size()-1;k++){
                newList.add(teamModels.get(k));
            }
            teamModels=newList;
        }
        Intent intent = new Intent(MainActivity.this,FixturesActivity.class);
        if(Build.VERSION.SDK_INT>20)
        {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(intent,options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    public void showui(boolean show){
        if(show){
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            teamNamesListView.setVisibility(View.VISIBLE);
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        else{
            teamNamesListView.setVisibility(View.GONE);
            floatingActionButton.setVisibility(View.GONE);
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }
    }
}


