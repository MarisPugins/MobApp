package com.example.galadarbs.ThirdHomeWork;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galadarbs.R;
import com.example.galadarbs.models.Person;

import java.util.ArrayList;

public class ThirdHomeWork_MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Person> list = new ArrayList<>();
    private ItemClickListener listener;



    public ThirdHomeWork_MusicListAdapter(ArrayList<Person> list, ItemClickListener itemClickListener){

        this.list = list;
        this.listener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.third_homework_person_list,
                parent,
                false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //if(holder.getClass() == ItemViewHolder.class) {
        if(holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).name.setText(list.get(position).getName());
            ((ItemViewHolder) holder).phone.setText(list.get(position).getPhoneList().getHome());
            ((ItemViewHolder) holder).song.setText(list.get(position).getSongPlaylist().get(0));
            if (position % 4 == 0) {
                ((ItemViewHolder) holder).icon.setImageResource(R.drawable.ic_baseline_wallpaper_24);

            } else if (position % 4 == 1){
                ((ItemViewHolder) holder).icon.setImageResource(R.drawable.icon_lucky_wheel);

            }
            else if(position%4==2) {
                ((ItemViewHolder) holder).icon.setImageResource(R.drawable.ic_baseline_home_24);

            }
            else if(position%4==3) {
                ((ItemViewHolder) holder).icon.setImageResource(R.drawable.ic_baseline_emoji_people_24);

            }
        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, phone, song;
        ImageView icon, topIcon;
        ItemClickListener itemClickListener;

        public ItemViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            phone = itemView.findViewById(R.id.tv_phone);
            song = itemView.findViewById(R.id.tv_favorite_song);
            icon = itemView.findViewById(R.id.iv_icons);

            this.itemClickListener=itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());

        }
    }
    public interface ItemClickListener{
        void onItemClick(int position);


    }
}
