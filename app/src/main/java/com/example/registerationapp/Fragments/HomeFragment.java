package com.example.registerationapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView email;
    TextView name;
    TextView school;


    public HomeFragment() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        email = view.findViewById(R.id.home_email_text_view);
        name = view.findViewById(R.id.home_name_text_view);
        school = view.findViewById(R.id.home_school_text_view);

        email.setText(SharedPreferencesManager.getInstance(getActivity()).getUser().getmEmail());
        name.setText(SharedPreferencesManager.getInstance(getActivity()).getUser().getmName());
        school.setText(SharedPreferencesManager.getInstance(getActivity()).getUser().getmSchool());




        return view;
    }

}
