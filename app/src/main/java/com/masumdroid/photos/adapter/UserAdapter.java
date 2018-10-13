package com.masumdroid.photos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.masumdroid.photos.R;
import com.masumdroid.photos.listener.OnItemClickListener;
import com.masumdroid.photos.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {
    private ArrayList<User> userList;
    private Context context;
    private OnItemClickListener mListener;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item, null);

        CustomViewHolder customViewHolder = new CustomViewHolder(view, context, userList);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.txtTitle.setText(userList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ArrayList<User> userList;
        TextView txtTitle;
        RelativeLayout mLayout;

        public CustomViewHolder(final View itemView, Context context, ArrayList<User> userList) {
            super(itemView);
            this.context = context;
            this.userList = userList;
            txtTitle = itemView.findViewById(R.id.tv_title);
            mLayout = itemView.findViewById(R.id.m_layout);

            txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });


            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClickListener(v, getLayoutPosition());
                }
            });

        }

    }


    public void setItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }


}
