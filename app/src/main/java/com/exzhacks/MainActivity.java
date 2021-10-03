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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import androidx.fragment.app.FragmentManager;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static RequestQueue requestQueue;
    //variable for fbfs
    public FirebaseFirestore db;
    public static ArrayList<Organization> mOrganizations;
    public static User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mOrganizations = new ArrayList<Organization>();

        curUser = new User();
        curUser.setEmail("xavierwells03@hotmail.com");
        curUser.setUserName("myUsername");
        curUser.setPassword("myPassword");
        curUser.setFirstName("dummy");
        curUser.setLastName("name");
        curUser.addInterest("Computer");
        curUser.addInterest("technology");
        curUser.addInterest("math");
        db = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();

        fragmentManager = getSupportFragmentManager();

        // Here, we set up the nav controller WITHOUT binders, its an old model, but it checks out
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

        //This is a good example of how to add a user to the DB
        /*
        User dummy = new User();
        dummy.setEmail("email@email.com");
        dummy.setFirstName("gfgd");
        dummy.setMajor("compy");
        dummy.setUserName("xavier");
        addDataToFirestore(dummy);
        */
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();




// old AWS cognito stuff, don't open unless you wanna be mad

/*

        // This section adds the auth plugin to our amplify package and initialized in the following block
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Log.i("AWS_Auth", "Initialized Cognito");
        } catch (AmplifyException e) {
            Log.e("AWS_Auth",e.toString());
        }
*/
        //Storing a user to Firestore
        //private void addDataToFirestore user topic = (user) {
    }
    public void addDataToFirestore(User user){

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
                        Organization temp = new Organization();
                        temp.setId(employee.getInt("Id"));
                        temp.setName(employee.getString("Name"));
                        temp.setSummary(employee.getString("Summary"));
                        mOrganizations.add(temp);
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