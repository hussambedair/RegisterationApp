package com.example.registerationapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registerationapp.API.APIManager;
import com.example.registerationapp.API.Models.DefaultResponse;
import com.example.registerationapp.API.Models.LoginResponse;
import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.R;
import com.example.registerationapp.Storage.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    Button login;
    TextView register;

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

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

    // check if the user is already logged in, so we send him directly to the ProfileActivity
    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPreferencesManager.getInstance(activity).isLoggedIn()) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }

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

        // this is the data that user entered
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();

        //email validation
        //make sure email format is right
        if (!isValidEmail(sEmail)) {
            email.setError(getString(R.string.invalied_email));
            return;

        }
        email.setError(null);

        //password validation
        //make sure that password is no less than 6 chars
        if (sPassword.length() < 6) {
            password.setError(getString(R.string.invalid_password));
            return;
        }
        password.setError(null);

        // Now as the validation conditions are passed,
        // do the login using the API call

        showProgressBar();

        APIManager.getAPIs()
                .loginUser(sEmail, sPassword)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        hideProgressBar();

                        LoginResponse loginResponse = response.body();

                        if (!loginResponse.ismError()) {
                            //proceed with the login

                            // if everything is fine and the login is successful,
                            // we'll store the User in SharedPreference or SQLite Database (Here I will use SharedPreferences)
                            // and then we'll open the HomeActivity

                            SharedPreferencesManager.getInstance(activity).saveUser(loginResponse.getmUser());

                            Toast.makeText(activity, loginResponse.getmMessage(), Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();


                        } else {
                            Toast.makeText(activity, loginResponse.getmMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });


    }
}
