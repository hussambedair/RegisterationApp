package com.example.registerationapp.Activities;

import android.content.Intent;
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
import com.example.registerationapp.Base.BaseActivity;
import com.example.registerationapp.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


        APIManager.getAPIs()
                .createUser(sEmail,sPassword,sName,sSchool)
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        hideProgressBar();

                        if (response.isSuccessful()) {
                            DefaultResponse defaultResponse = response.body();
                            Toast.makeText(activity, defaultResponse.getmMessage(),Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(activity, "User Already Exists",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        hideProgressBar();
                        Toast.makeText(activity, t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });






        /*APIManager.getAPIs()
                .createUser(sEmail,sPassword,sName,sSchool)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = null;
                        hideProgressBar();
                        if (response.isSuccessful()) {
                            try {
                                 s = response.body().string(); // this is the response that we got from the server,  and we turn it into a String
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        } else {
                            try {
                                 s = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        if (s != null) {
                            try { //we will parse the json manually based on the json response that we have
                                // "error" : ----- , "message" : "------"
                                JSONObject jsonObject = new JSONObject(s);
                                Toast.makeText(activity,jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        hideProgressBar();
                        Toast.makeText(activity, t.getMessage(),Toast.LENGTH_SHORT).show();


                    }
                });*/








    }

    public static boolean isValidEmail (CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
