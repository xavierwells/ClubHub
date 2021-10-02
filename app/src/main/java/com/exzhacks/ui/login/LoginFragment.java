package com.exzhacks.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.exzhacks.R;
import com.exzhacks.ui.actions.ActionFragment;

import static com.amazonaws.services.cognitoidentityprovider.model.VerifySoftwareTokenResponseType.SUCCESS;
import static com.amplifyframework.auth.result.AuthSessionResult.Type.FAILURE;
import static com.exzhacks.MainActivity.fragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // declare all variables from layout
    EditText editUsername;
    EditText editPassword;
    EditText editEmail;
    Button buttonSignup;
    Button buttonLogin;

    String m_Text = "";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        // assign layout items
        buttonSignup = v.findViewById(R.id.signup_button);
        buttonLogin = v.findViewById(R.id.login_button);
        editEmail = v.findViewById(R.id.email);
        editUsername = v.findViewById(R.id.username);
        editPassword = v.findViewById(R.id.password);
/*
        // Assign Signup button action
        // 1: sign up user w/email
        // 2: assign email a username and password
        // 3: Open dialog to request confirmation from email
        // 4: Confirm and complete signup
        // 5: Run login action
        buttonSignup.setOnClickListener(view -> {

            // step 1
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), editEmail.getText().toString())
                    .build();

            // step 2
            Amplify.Auth.signUp(editUsername.getText().toString(), editPassword.getText().toString(), options,
                    result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                    error -> Log.e("AuthQuickStart", "Sign up failed", error)
            );

            // step 3:
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("enter email confirmation");
            // Set up the input
            final EditText input = new EditText(getContext());
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);
            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // step 4:
                    m_Text = input.getText().toString();
                    Amplify.Auth.confirmSignUp(
                            editUsername.getText().toString(),
                            m_Text,
                            result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                            error -> Log.e("AuthQuickstart", error.toString())
                    );
                    //step 5:
                    loginAction(editUsername.getText().toString(),editPassword.getText().toString());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        });

        buttonLogin.setOnClickListener(view -> {
            loginAction(editUsername.getText().toString(), editPassword.getText().toString());
        });
*/
        // Inflate the layout for this fragment
        return v;
    }

    private void loginAction(String username, String password){
        Amplify.Auth.signIn(
                username,
                password,
                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );

        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            break;
                        case FAILURE:
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );

        fragmentManager.beginTransaction().
                add(R.id.nav_host_fragment_activity_main, new ActionFragment()).commit();

    }
}