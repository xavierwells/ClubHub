package com.exzhacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import static com.amazonaws.mobile.client.internal.oauth2.OAuth2Client.TAG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static FragmentManager fragmentManager;
    public static RequestQueue requestQueue;
    //variable for fbfs
    public FirebaseFirestore db;


    EditText editUsername;
    EditText editPassword;
    EditText editPasswordConf;
    Button buttonLogin;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        fragmentManager = getSupportFragmentManager();

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
            User dummy = new User();
            dummy.setEmail("dummmy@dummy.com");
            dummy.setFirstName("Xavier");
            dummy.addInterest("Computer");
            dummy.addInterest("Technology");
            dummy.setMajor("compy");
            dummy.setUserName(editUsername.getText().toString());
            dummy.setPassword(editPassword.getText().toString());
            getDataFromFirestore(dummy);


            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.putExtra("userID", userId);
            startActivity(intent);
            //SaveSharedPreference.setUserId(this.getContext(),userId);
            // TODO Login stuff, check against DB
        }
        else{
            if(editPassword!=editPasswordConf){
                Toast.makeText(getApplicationContext(), "Passwords do not match!, try again", Toast.LENGTH_LONG).show();
                return;
            }
            User dummy = new User();
            dummy.setEmail("dummmy@dummy.com");
            dummy.setFirstName("Xavier");
            dummy.addInterest("Computer");
            dummy.addInterest("Technology");
            dummy.setMajor("compy");
            dummy.setUserName(editUsername.getText().toString());
            dummy.setPassword(editPassword.getText().toString());
            addDataToFirestore(dummy);
        }
    }

    private void addDataToFirestore(User user){

        //creating collection reference
        CollectionReference dbTopic = db.collection("Topic");
        //User u = new User(user);

        dbTopic.add(user);
    }

    private void getDataFromFirestore(User user){

        db.collection("topic")
                .whereEqualTo("username", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("DATABASE", document.getId() + " => " + document.getData());
                                finish();
                            }
                        } else {
                            Log.e("DATABASE", "Error getting documents: ", task.getException());
                            finish();
                        }
                    }
                });

    }
}