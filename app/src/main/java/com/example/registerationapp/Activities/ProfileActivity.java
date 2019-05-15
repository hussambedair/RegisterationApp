package com.example.registerationapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.registerationapp.API.Models.User;
import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

public class ProfileActivity extends BaseActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.myTextView);
        // we want to display a message in this textview
        User user = SharedPreferencesManager.getInstance(this).getUser();
        textView.setText("Welcome Back " + user.getmName());


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPreferencesManager.getInstance(activity).isLoggedIn()) {
            Intent i = new Intent(this, RegisterationActivity.class);
            startActivity(i);
            finish();
        }

    }
}
