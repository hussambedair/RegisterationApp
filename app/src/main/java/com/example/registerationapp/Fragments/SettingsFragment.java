package com.example.registerationapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.registerationapp.Base.BaseFragment;
import com.example.registerationapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment implements View.OnClickListener {

    EditText email;
    EditText name;
    EditText school;
    Button save;

    EditText currentPassword;
    EditText newPassword;
    Button changePassword;

    Button logout;
    Button deleteProfile;


    public SettingsFragment() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        email = view.findViewById(R.id.settings_email);
        name = view.findViewById(R.id.settings_name);
        school = view.findViewById(R.id.settings_school);

        save = view.findViewById(R.id.settings_save_button);
        save.setOnClickListener(this);

        currentPassword = view.findViewById(R.id.settings_current_password);
        newPassword = view.findViewById(R.id.settings_new_password);

        changePassword = view.findViewById(R.id.settings_change_password_button);
        changePassword.setOnClickListener(this);

        logout = view.findViewById(R.id.settings_logout_button);
        deleteProfile = view.findViewById(R.id.settings_delete_profile_button);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

        }
    }
}
