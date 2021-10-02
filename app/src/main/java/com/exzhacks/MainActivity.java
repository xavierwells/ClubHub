package com.exzhacks;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.exzhacks.ui.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This fragment manager is really handy to have around if we need to do any weird transitions
        fragmentManager = getSupportFragmentManager();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here, we set up the nav controller WITHOUT binders, its an old model, but it checks out
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);



        //fragmentManager.beginTransaction().
        //        add(R.id.nav_host_fragment_activity_main, new LoginFragment()).commit();


/*

        // This section adds the auth plugin to our amplify package and initialized in the following block
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Log.i("AWS_Auth", "Initialized Cognito");
        } catch (AmplifyException e) {
            Log.e("AWS_Auth",e.toString());
        }

        // This try catch initializes the AWS stuff we need
        try {
            Amplify.configure(getApplicationContext());
            Log.i("AWS_Amplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("AWS_Amplify", "Could not initialize Amplify", error);
        }


        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
 */
    }
}