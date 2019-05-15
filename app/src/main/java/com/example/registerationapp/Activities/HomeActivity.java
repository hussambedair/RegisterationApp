package com.example.registerationapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.registerationapp.API.Models.User;
import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.Fragments.HomeFragment;
import com.example.registerationapp.Fragments.SettingsFragment;
import com.example.registerationapp.Fragments.UsersFragment;
import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

public class HomeActivity extends BaseActivity {


    TextView wellcomeText;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    return true;
                case R.id.navigation_users:
                    fragment = new UsersFragment();
                    return true;
                case R.id.navigation_settings:
                    fragment = new SettingsFragment();
                    return true;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    //.addToBackStack(null)
                    .commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        wellcomeText = findViewById(R.id.wellcome_text_view);
        // we want to display a wellcome message in this textview
        User user = SharedPreferencesManager.getInstance(this).getUser();
        wellcomeText.setText("Welcome Back " + user.getmName());
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
