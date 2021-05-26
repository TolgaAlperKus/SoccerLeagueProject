package com.example.soccerleagueproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.soccerleagueproject.R;
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
    ListView teamNamesListView;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
        teamNamesListView = findViewById(R.id.activity_main_listview);


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
                    arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,teamModels);
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
        int teamSize = teamModels.size();
        // Kaç round sonrası lig tamamlanacak
        int roundCount=(teamSize-1)*2;
        // Bir round'da ne kadar maç oynanır
        int matchCountPerRound=teamSize/2;

        for (int i = 0; i < roundCount; i++) {

            System.out.println((i+1)+".nci Hafta:");

            for(int j=0;j<matchCountPerRound;j++){

                int firstIndex=j;
                int secondIndex=(teamSize-1)-j;

                System.out.println(teamModels.get(firstIndex)
                        +"-"+teamModels.get(secondIndex));

            }

            // İlk eleman sabit olacak şekilde elamanları kaydırıyoruz
            ArrayList<TeamModel> newList=new ArrayList<TeamModel>();

            // İlk eleman sabit
            newList.add(teamModels.get(0));

            // Son eleman ikinci eleman yapıyoruz.
            newList.add(teamModels.get(teamModels.size()-1));

            for(int k=1;k<teamModels.size()-1;k++){
                newList.add(teamModels.get(k));
            }

            // Keydırılan liste yeni liste oluyor.
            teamModels=newList;

        }

        System.out.println("Toplam Hafta Sayısı : "+roundCount);
        System.out.println("Bir Haftadaki Maç Sayısı:  "+matchCountPerRound);

    }
}
