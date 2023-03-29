package com.example.galadarbs.ThirdHomeWork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galadarbs.R;
import com.example.galadarbs.models.ArtistList;

import java.util.ArrayList;

public class ThirdHomeWork_ArtistAdapter extends RecyclerView.Adapter<ThirdHomeWork_ArtistAdapter.ViewHolder> {

    Context context;
    ArrayList<ArtistList> artistList;
    ArrayList aName;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView artistImages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistImages = itemView.findViewById(R.id.iv_artists);
        }
    }

    public ThirdHomeWork_ArtistAdapter(Context context, ArrayList<ArtistList> artistList, ArrayList aName) {
        this.context = context;
        this.artistList = artistList;
        this.aName = aName;
    }

    @NonNull
    @Override
    public ThirdHomeWork_ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.third_homework_rv_artist, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThirdHomeWork_ArtistAdapter.ViewHolder holder, int position) {

        for (int i=0; i<artistList.size();i++){
                    if (aName.get(position).toString().equals(artistList.get(i).getName())){
                        holder.artistImages.setImageResource(artistList.get(i).getPicture());
                    }

            }

    }

    @Override
    public int getItemCount() {
        return aName.size();
    }

}
