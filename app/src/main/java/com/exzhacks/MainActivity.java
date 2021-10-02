package com.exzhacks;

import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exzhacks.ui.login.LoginFragment;
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
    private RequestQueue requestQueue;
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
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();

        // Here, we set up the nav controller WITHOUT binders, its an old model, but it checks out
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);


// old AWS cognito stuff, don't open unless you wanna be mad

/*

        // This section adds the auth plugin to our amplify package and initialized in the following block
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Log.i("AWS_Auth", "Initialized Cognito");
        } catch (AmplifyException e) {
            Log.e("AWS_Auth",e.toString());
        }

    //Storing a user to Firestore
    //private void addDataToFirestore user topic = (user) {
    private void addDataToFirestore(User user){

        //creating collection reference
        CollectionReference dbTopic = db.collection("Topic");
        //User u = new User(user);

        dbTopic.add(user);
    }

    private void jsonParse() {
        String url = "https://mavorgs.campuslabs.com/engage/api/discovery/search/organizations?orderBy[0]=UpperName%20asc&top=1000&filter=&query=&skip=0";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("value");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject employee = jsonArray.getJSONObject(i);
                        String id = employee.getString("Id");
                        String name = employee.getString("Name");
                        String sum = employee.getString("Summary");
                        Log.v("JSON", id + " " + name + " " + sum + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}