package com.exzhacks;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import androidx.fragment.app.FragmentManager;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
public static FragmentManager fragmentManager;
//variable for fbfs
private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        fragmentManager = getSupportFragmentManager();
        User dummy = new User();
        dummy.setEmail("email@email.com");
        dummy.setFirstName("gfgd");
        dummy.setMajor("compy");
        dummy.setUserName("xavier");
        addDataToFirestore(dummy);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

    }

    //Storing a user to Firestore
    //private void addDataToFirestore user topic = (user) {
    private void addDataToFirestore(User user){

        //creating collection reference
        CollectionReference dbTopic = db.collection("Topic");
        //User u = new User(user);

        dbTopic.add(user);
    }
}