package com.example.galadarbs.ThirdHomeWork;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galadarbs.R;
import com.example.galadarbs.models.SongList;

import java.util.ArrayList;

public class ThirdHomeWork_SongAdapter extends RecyclerView.Adapter<ThirdHomeWork_SongAdapter.ViewHolder> {


        Context context;
        ArrayList<SongList> songList;
        ArrayList sName;

        public static class ViewHolder extends RecyclerView.ViewHolder{

            ImageView songImages;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                songImages = itemView.findViewById(R.id.iv_songs);
            }
        }

    public ThirdHomeWork_SongAdapter(Context context, ArrayList<SongList> songList, ArrayList sName) {
        this.context = context;
        this.songList = songList;
        this.sName = sName;
    }


        @NonNull
        @Override
        public ThirdHomeWork_SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.third_homework_rv_songs, parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ThirdHomeWork_SongAdapter.ViewHolder holder, int position) {

            for (int i=0; i<songList.size();i++){
                    if (sName.get(position).toString().equals(songList.get(i).getName())){
                        holder.songImages.setImageResource(songList.get(i).getPicture());
                    }
            }

        }

        @Override
        public int getItemCount() {
            return sName.size();
        }
    }

