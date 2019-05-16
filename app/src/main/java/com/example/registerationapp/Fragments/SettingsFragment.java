package com.example.registerationapp.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.registerationapp.API.APIManager;
import com.example.registerationapp.API.Models.DefaultResponse;
import com.example.registerationapp.API.Models.LoginResponse;
import com.example.registerationapp.API.Models.User;
import com.example.registerationapp.Activities.LoginActivity;
import com.example.registerationapp.Activities.RegisterationActivity;
import com.example.registerationapp.Base.BaseFragment;
import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        // first, we'll display a dialog to the user to confirm deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are You Sure ?");
        builder.setMessage("This action is irreversable");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User currentUser = SharedPreferencesManager.getInstance(getActivity()).getUser();
                APIManager.getAPIs()
                        .deleteUser(currentUser.getmId())
                        .enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if (response.isSuccessful()) {
                                    SharedPreferencesManager.getInstance(getActivity()).logoutUser();
                                    Intent intent = new Intent(getActivity(), RegisterationActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                                Toast.makeText(getActivity(),response.body().getmMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void logoutUser() {
        SharedPreferencesManager.getInstance(getActivity()).logoutUser();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    private void changeUserPassword() {
        String sCurrentPassword = currentPassword.getText().toString();
        String sNewPassword = newPassword.getText().toString();

        //password validation
        //make sure that password is no less than 6 chars
        if (sCurrentPassword.length() <6) {
            currentPassword.setError(getString(R.string.invalid_password));
            return;
        }
        currentPassword.setError(null);

        //password validation
        //make sure that password is no less than 6 chars
        if (sNewPassword.length() <6) {
            newPassword.setError(getString(R.string.invalid_password));
            return;
        }
        newPassword.setError(null);

        User currentUser = SharedPreferencesManager.getInstance(getActivity()).getUser();

        APIManager.getAPIs()
                .updatePassword(sCurrentPassword, sNewPassword, currentUser.getmEmail())
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        Toast.makeText(getActivity(), response.body().getmMessage(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });





    }

    private void updateProfile() {
        String sEmail = email.getText().toString();
        final String sName = name.getText().toString();
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

        // get the current from the SharedPreferences
        final User currentUser = SharedPreferencesManager.getInstance(getActivity()).getUser();

        APIManager.getAPIs()
                .updateUser(currentUser.getmId(), sEmail,sName,sSchool)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Toast.makeText(getActivity(),response.body().getmMessage(),Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful()) {
                            SharedPreferencesManager.getInstance(getActivity()).saveUser(response.body().getmUser());

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });






    }

    public static boolean isValidEmail (CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
