package com.example.registerationapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registerationapp.Base.BaseFragment;
import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

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
        logout.setOnClickListener(this);
        deleteProfile = view.findViewById(R.id.settings_delete_profile_button);
        deleteProfile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.settings_save_button:
                updateProfile();
                break;

            case R.id.settings_change_password_button:
                changeUserPassword();
                break;

            case  R.id.settings_logout_button:
                logoutUser();
                break;

            case R.id.settings_delete_profile_button:
                deleteUserProfile();
                break;



        }
    }

    private void deleteUserProfile() {
        // logic of profile deletion here
    }

    private void logoutUser() {
        SharedPreferencesManager.getInstance(getActivity()).logoutUser();
    }

    private void changeUserPassword() {

        String sCurrentPassword = currentPassword.getText().toString();
        String sNewPassword = newPassword.getText().toString();

        //password validation
        //make sure that password is no less than 6 chars
        if (sCurrentPassword.length() <6) {
            //sCurrentPassword.setError(getString(R.string.invalid_password));
            return;
        }
        //sCurrentPassword.setError(null);

        //make sure that password is no less than 6 chars
        if (sNewPassword.length() <6) {
            //sNewPassword.setError(getString(R.string.invalid_password));
            return;
        }
        //sNewPassword.setError(null);


        // check if the current password is correct
        // change the password with the new password


        Toast.makeText(getActivity(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();



    }

    private void updateProfile() {
        String sEmail = email.getText().toString();
        String sName = name.getText().toString();
        String sSchool = school.getText().toString();

        //Name validation
        //make sure that Name is not empty
        if (sName.trim().length() == 0) {
            name.setError(getString(R.string.enter_name));
            return;
        }
        name.setError(null);

        //School validation
        //make sure that School name is not empty
        if (sSchool.trim().length() == 0) {
            school.setError(getString(R.string.enter_school));
            return;
        }
        school.setError(null);

        //email validation
        //make sure email format is right
        if (!isValidEmail(sEmail)) {
            email.setError(getString(R.string.invalied_email));
            return;

        }
        email.setError(null);

        SharedPreferencesManager.getInstance(getActivity()).getUser().setmEmail(sEmail);
        SharedPreferencesManager.getInstance(getActivity()).getUser().setmName(sName);
        SharedPreferencesManager.getInstance(getActivity()).getUser().setmSchool(sSchool);

        Toast.makeText(getActivity(),"Profile Updated Successfully",Toast.LENGTH_SHORT).show();




    }

    public static boolean isValidEmail (CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
