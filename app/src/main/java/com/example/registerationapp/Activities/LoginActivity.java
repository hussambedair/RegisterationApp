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

import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

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
        if (sPassword.length() <6) {
            password.setError(getString(R.string.invalid_password));
            return;
        }
        password.setError(null);

        // Now as the validation conditions are passed,
        // do the login using the API call

        showProgressBar();





    }

    public static boolean isValidEmail (CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
