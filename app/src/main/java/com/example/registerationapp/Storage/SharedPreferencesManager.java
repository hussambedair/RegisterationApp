package com.example.registerationapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.registerationapp.API.Models.User;

public class SharedPreferencesManager {

    // We'll also use the singeleton pattern as we did in retrofit

    private static SharedPreferencesManager sharedPreferencesManagerInstance;
    private Context context;

    private SharedPreferencesManager(Context context) {
        this.context = context;
    }

    private static SharedPreferencesManager getInstance(Context context) {

        if (sharedPreferencesManagerInstance == null) { // create
            sharedPreferencesManagerInstance = new SharedPreferencesManager(context);
        }

        return sharedPreferencesManagerInstance;

    }

    // create a method that will store the User inside SharedPreferences
    public void saveUser(User user) {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("my SharedPref", context.MODE_PRIVATE);
        // only this application will access this SharedPreferences

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Now, we put the information of the user inside the SharedPrefrence
        editor.putInt("id", user.getmId());
        editor.putString("email", user.getmEmail());
        editor.putString("name", user.getmName());
        editor.putString("school", user.getmSchool());
        // apply the changes
        editor.apply();

    }


    // create a method to check if the user is logged in or not
    //if the user information is already saved in sharedPreferences
    //Then we'll assume that the user is already logged in

    public boolean isLoggedIn () {
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("my SharedPref", context.MODE_PRIVATE);

        if (sharedPreferences.getInt("id", -1) != -1) { // if user id doesn't equal -1
            // the user is logged in
            return true;
        }
        return false;

    }

    // create a method to return the User





}
