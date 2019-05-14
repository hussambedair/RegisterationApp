package com.example.registerationapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.registerationapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email_edittext);
        password = findViewById(R.id.login_password_edittext);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(this);
        register = findViewById(R.id.register_text_view);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.register_text_view:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterationActivity.class);
                startActivity(registerIntent);
                break;

            case R.id.login_button:
                loginUser();
                break;
        }
    }

    private void loginUser() {



    }
}
