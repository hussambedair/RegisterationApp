package com.example.registerationapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.R;

public class RegisterationActivity extends BaseActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    EditText name;
    EditText school;
    TextView login;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        email = findViewById(R.id.register_email_edittext);
        password = findViewById(R.id.register_password_edittext);
        name = findViewById(R.id.user_name_edittext);
        school = findViewById(R.id.school_edittext);
        login = findViewById(R.id.login_text_view);
        login.setOnClickListener(this);
        register = findViewById(R.id.register_button);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_text_view:
                Intent loginIntent = new Intent(RegisterationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
                
            case  R.id.register_button:
                registerUser();
                break;
        }
    }

    private void registerUser() {

        // this is the data that user entered
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
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

        //password validation
        //make sure that password is no less than 6 chars
        if (sPassword.length() <6) {
            password.setError(getString(R.string.invalid_password));
            return;
        }
        password.setError(null);

        // Now as the validation conditions are passed,
        // do the registeration using the API call


        showProgressBar();





    }

    public static boolean isValidEmail (CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}