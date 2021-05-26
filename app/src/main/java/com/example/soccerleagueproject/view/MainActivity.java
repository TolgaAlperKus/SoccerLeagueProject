package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.adapter.RecyclerViewAdapter;
import com.example.soccerleagueproject.model.TeamModel;
import com.example.soccerleagueproject.service.SoccerApi;
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
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.activity_main_recyclerview);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();


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

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(teamModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

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
}