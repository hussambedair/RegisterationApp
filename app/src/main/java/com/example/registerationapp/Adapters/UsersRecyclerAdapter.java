package com.example.registerationapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.registerationapp.API.Models.User;
import com.example.registerationapp.R;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {


    List <User> mUsers;

    public UsersRecyclerAdapter(List<User> mUsers) {
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if (mUsers == null) {
            return 0;
        }
        return mUsers.size();
    }


    public void changeData (List<User> users) {
        mUsers = users;
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        TextView school;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name_text_view);
            email = itemView.findViewById(R.id.item_email_text_view);
            school = itemView.findViewById(R.id.item_school_text_view);

        }
    }






}
