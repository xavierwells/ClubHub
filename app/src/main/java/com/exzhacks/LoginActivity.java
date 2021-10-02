package com.exzhacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUsername;
    EditText editPassword;
    EditText editPasswordConf;
    Button buttonLogin;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        editPasswordConf = findViewById(R.id.edit_password_confirm);
        buttonLogin = findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(this::onClick);
        buttonSignup = findViewById(R.id.signup_button);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == buttonLogin.getId()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.putExtra("userID", userId);
            startActivity(intent);
            //SaveSharedPreference.setUserId(this.getContext(),userId);
            finish();
            // TODO Login stuff, check against DB
        }
        else{
            // TODO Sign up implementation
        }
    }
}