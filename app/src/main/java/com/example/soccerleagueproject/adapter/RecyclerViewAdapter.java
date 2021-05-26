package com.example.soccerleagueproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccerleagueproject.R;
import com.example.soccerleagueproject.model.TeamModel;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<TeamModel> teamList;

    public RecyclerViewAdapter(ArrayList<TeamModel> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView teamNameTV;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind (TeamModel teamModel){
            teamNameTV = itemView.findViewById(R.id.row_layout_name_textview);
            teamNameTV.setText(teamModel.teamName);
        }
    }
}
