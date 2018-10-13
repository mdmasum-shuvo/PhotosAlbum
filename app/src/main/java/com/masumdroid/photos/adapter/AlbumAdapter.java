package com.masumdroid.photos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.masumdroid.photos.R;
import com.masumdroid.photos.model.Album;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.CustomViewHolder> {
    private ArrayList<Album> albumList;
    private Context context;

    public AlbumAdapter(ArrayList<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_album, null);
        CustomViewHolder customViewHolder=new CustomViewHolder(view,context,albumList);
        return customViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder,final int position) {
        holder.txtTitle.setText(albumList.get(position).getTitle());
        Glide.with(context)
                .load(albumList.get(position).getUrl())
                .placeholder(R.color.color_text)
                .into(holder.imgAlbum);

        Glide.with(context)
                .load(albumList.get(position).getThumbnailUrl())
                .into(holder.imgProfile);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ArrayList<Album> albumList;
        TextView txtTitle;
        ImageView imgAlbum;
        CircleImageView imgProfile;
        RelativeLayout mLayout;
        public CustomViewHolder(final View itemView, Context context, ArrayList<Album> albumList) {
            super(itemView);
            this.context = context;
            this.albumList = albumList;
            txtTitle=itemView.findViewById(R.id.tv_title);
            imgAlbum=itemView.findViewById(R.id.img_album);
            imgProfile=itemView.findViewById(R.id.img_profile);


        }

    }



}
