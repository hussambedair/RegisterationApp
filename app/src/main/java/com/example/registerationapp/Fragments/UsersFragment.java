package com.example.registerationapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.registerationapp.API.APIManager;
import com.example.registerationapp.API.Models.User;
import com.example.registerationapp.API.Models.UsersResponse;
import com.example.registerationapp.Activities.HomeActivity;
import com.example.registerationapp.Adapters.UsersRecyclerAdapter;
import com.example.registerationapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment {

    RecyclerView usersRecyclerView;
    UsersRecyclerAdapter usersAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<User> users;


    public UsersFragment() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_users, container, false);

        usersRecyclerView = view.findViewById(R.id.users_recycler_view);
        usersAdapter = new UsersRecyclerAdapter(null);
        layoutManager = new LinearLayoutManager(getActivity());
        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(layoutManager);

        getUsers();

        return view;
    }

    private void getUsers() {

        APIManager.getAPIs()
                .getAllUsers()
                .enqueue(new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        if (response.isSuccessful()) {
                            usersAdapter.changeData(response.body().getmUsers());
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {

                    }
                });



    }

}
